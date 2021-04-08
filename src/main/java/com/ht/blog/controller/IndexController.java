package com.ht.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class IndexController {

//    @GetMapping("/")
//    public String index(){
//        int i = 1/0;       // 测试异常
//        String blog = null;
//        if (blog == null) {
//            throw  new NotFoundException("博客不存在");
//        }
//        return "index";
//    }

//    @GetMapping("/tags")
//    public String tags(){
//        return "tags";
//    }
//
//    @GetMapping("/aboutme")
//    public String aboutme(){
//        return "aboutme";
//    }
//
//    @GetMapping("/blog")
//    public String blog(){
//        return "blog";
//    }

    @GetMapping("/type")
    public String manager() {
        return "type-list";
    }
    @GetMapping("/blogmanager")
    public String blogmanager() {
        return "blog-list";
    }
}

