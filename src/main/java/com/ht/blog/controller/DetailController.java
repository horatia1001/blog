package com.ht.blog.controller;

import com.ht.blog.pojo.Blog;
import com.ht.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/blog")
public class DetailController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/detail")
    public String showDetail(Model model,
                             @RequestParam(value = "id") Long id){
        Blog blog = blogService.getBlogDetailById(id);
        model.addAttribute("blog",blog);
        return "detail";
    }
}
