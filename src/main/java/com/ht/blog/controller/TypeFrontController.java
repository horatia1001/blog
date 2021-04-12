package com.ht.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ht.blog.pojo.Blog;
import com.ht.blog.pojo.Type;
import com.ht.blog.service.BlogService;
import com.ht.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/blog/type")
public class TypeFrontController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;

//    @GetMapping("/list")
//    public String listTypeFront(Model model,
//                                @RequestParam(value = "pageNum", defaultValue = "1")  int pageNum,
//                                @RequestParam(value = "pageSize", defaultValue = "8") int pageSize){
//        PageHelper.startPage(pageNum, pageSize);
//        List<Blog> blogList = blogService.listBlogIndex();
//        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
//        model.addAttribute("pageInfo", pageInfo);
//
//        List<Type> typeList = typeService.listType();
//        model.addAttribute("typeList",typeList);
//
//        return "type";
//    }

    @GetMapping("/getBlog")
    public String getBlogByType(Model model,
                                @RequestParam(value = "pageNum", defaultValue = "1")  int pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "8") int pageSize,
                                @RequestParam(value = "id") Long id){
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogList = blogService.getBlogByTypeId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("pageInfo", pageInfo);

        List<Type> typeList = typeService.listType();
        model.addAttribute("typeList",typeList);
        model.addAttribute("activeTypeId", id);     // 为了选中分类突出显示

        return "type";

    }


}
