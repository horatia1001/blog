package com.ht.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @After 和 @AfterReturning 的执行顺序是不定的
 * 当在同一个方面中定义的两条通知都需要在同一个连接点上运行时，其顺序是未定义的(因为无法通过反射为javac编译的类检索声明顺序)。
 *
 */
@Aspect
@Component    // 将LogAspect对象加入spring进行管理
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut(value="execution(* com.ht.blog.controller.*.*(..))")
    public void log(){ }

    /**
     * 在执行切入点方法之前，获取请求的ip,url,请求方法,请求参数。
     */
    @Before("log()")
    public void logBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 通过 ServletRequestAttributes 获取 request 对象
        HttpServletRequest request = attributes.getRequest();
        String ip = request.getRemoteAddr();
        String url = request.getRequestURL().toString();
        String method = joinPoint.getSignature().getDeclaringTypeName()  + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        // 将获取的数据封装为RequestLog对象
        RequestLog requestLog = new RequestLog(ip, url, method, args);
        logger.info("--------------Request: {}--------------",requestLog);

    }

    @After("log()")
    public void logAfter(){
        logger.info("--------------logAfter--------------");

    }

    /**
     * 在执行切入点方法之后，获取返回的结果。
     */
    @AfterReturning(pointcut = "log()", returning = "result")
    public void logAfterReturning(Object result){
        logger.info("--------------Result: {}--------------", result);
    }

    public class RequestLog{
        String ip;
        String url;
        String method;
        Object[] args;

        public RequestLog(String ip, String url, String method, Object[] args) {
            this.ip = ip;
            this.url = url;
            this.method = method;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + method + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

}
