package com.ht.blog.controller;

import com.ht.blog.pojo.Comment;
import com.ht.blog.service.BlogService;
import com.ht.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/blog/comment")
public class CommentController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/list/{blogId}")
    public String listComments(@PathVariable Long blogId, Model model){
        List<Comment> commentList = commentService.listComment();
        model.addAttribute("commentList",commentList);
        model.addAttribute("blog", blogService.getBlogDetailById(blogId));
        return "detail :: commentList";
    }

    @PostMapping("/add/{blogId}")
    public String addComment(@PathVariable Long blogId, Comment comment, RedirectAttributes attributes){
        // 绑定博客
        Long id = comment.getBlog().getId();
        comment.setBlogId(id);
        comment.setBlog(blogService.getBlogById(id));

        // 添加评论
        int count = commentService.addComment(comment);
        if(count > 0) {
            attributes.addFlashAttribute("message","发布成功!");
        }else {
            attributes.addFlashAttribute("message","发布失败!请重新发布。");
        }

        return "redirect:/blog/detail?id=" + blogId;
    }

}
