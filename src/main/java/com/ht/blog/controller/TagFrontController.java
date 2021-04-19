package com.ht.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ht.blog.pojo.Blog;
import com.ht.blog.pojo.Tag;
import com.ht.blog.pojo.Type;
import com.ht.blog.service.BlogService;
import com.ht.blog.service.TagService;
import com.ht.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/blog/tag")
public class TagFrontController {

    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/getBlog")
    public String getBlogByType(Model model,
                                @RequestParam(value = "pageNum", defaultValue = "1")  int pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "50") int pageSize,
                                @RequestParam(value = "id") Long id){
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogList = blogService.getBlogByTagId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("pageInfo", pageInfo);

        List<Tag> tagList = tagService.listTag();
        model.addAttribute("tagList",tagList);
        model.addAttribute("activeTagId", id);     // 为了选中分类突出显示

        return "tag";

    }


}
