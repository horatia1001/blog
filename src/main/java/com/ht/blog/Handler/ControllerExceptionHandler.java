package com.ht.blog.Handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理：统一拦截异常
 * @ExceptionHandler(value=Exception.class) 只要是Exception就可以捕获
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        logger.error("RequestURL:{}, Exception:{}", request.getRequestURL(), e);

        // 处理 资源找不到异常404
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }

        ModelAndView mv = new ModelAndView();
        // 将错误信息加入mv对象中，返回给用户
        mv.addObject("requestURL",request.getRequestURL());     //getRequestURL返回StringBuffer对象
        mv.addObject("exception",e);
        // 设置返回的view
        mv.setViewName("error/error");
        return mv;

    }
}
