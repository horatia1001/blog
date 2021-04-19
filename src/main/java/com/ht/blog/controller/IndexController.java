package com.ht.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ht.blog.pojo.Blog;
import com.ht.blog.pojo.Tag;
import com.ht.blog.pojo.Type;
import com.ht.blog.service.BlogService;
import com.ht.blog.service.TagService;
import com.ht.blog.service.TypeService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/blog")
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(value = "pageNum", defaultValue = "1")  int pageNum,
                        @RequestParam(value = "pageSize", defaultValue = "8") int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogList = blogService.listBlogIndex();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("pageInfo", pageInfo);

        List<Type> typeList = typeService.listType();
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("typeList",typeList);
        model.addAttribute("tagList",tagList);

        return "index";
    }

    @PostMapping("/search")
    public String search(Model model,
                         @RequestParam(value = "keyWord", defaultValue = "") String keyWord,
                         @RequestParam(value = "pageNum", defaultValue = "1")  int pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "50") int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogList = blogService.searchBlogFront(keyWord);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("pageInfo", pageInfo);

        return "search-result";
    }

    @GetMapping("/type")
    public String toType(Model model,
                         @RequestParam(value = "pageNum", defaultValue = "1")  int pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "8") int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogList = blogService.listBlogIndex();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("pageInfo", pageInfo);

        List<Type> typeList = typeService.listType();
        model.addAttribute("typeList",typeList);
        return "type";
    }

    @GetMapping("/tag")
    public String toTag(Model model,
                        @RequestParam(value = "pageNum", defaultValue = "1")  int pageNum,
                        @RequestParam(value = "pageSize", defaultValue = "50") int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogList = blogService.listBlogIndex();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("pageInfo", pageInfo);
        for(Blog blog: blogList){
            System.out.println(blog.getTags());
        }

        List<Tag> tagList = tagService.listTag();
        model.addAttribute("tagList",tagList);

        return "tag";
    }

    @GetMapping("/archive")
    public String toArchive(Model model, String year){
        Map<String, List<Blog>> yearBlogMap = blogService.getBlogByYear(year);      // 查询每个年份以及对应博客
        model.addAttribute("yearBlogMap",yearBlogMap);
        model.addAttribute("blogCount", blogService.listBlogIndex().size());    // 查询总博客条数
        return "archive";
    }

    @GetMapping("/aboutme")
    public String toAboutme(){
        return "aboutme";
    }



}

