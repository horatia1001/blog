package com.ht.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ht.blog.pojo.Blog;
import com.ht.blog.pojo.Tag;
import com.ht.blog.pojo.Type;
import com.ht.blog.pojo.User;
import com.ht.blog.service.BlogService;
import com.ht.blog.service.TagService;
import com.ht.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    /**
     * 跳转到编辑博客页面进行 添加
     * @return
     */
    @GetMapping("/blog/toAdd")
    public String toAdd(Model model){
        // typeList 对象中传递了分类列表，用于分类选择下拉框
        List<Type> typeList = typeService.listType();
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("typeList",typeList);
        model.addAttribute("tagList",tagList);
        // 用于添加或编辑博客，不然没有一个Blog对象去保存form表单提交的值，就会报错
        model.addAttribute("blog", new Blog());
        return "admin/blog-edit";
    }

    /**
     * 跳转到编辑博客页面进行 更新
     * @return
     */
    @GetMapping("/blog/toUpdate")
    public String toUpdate(Model model, @RequestParam Long id){
//        System.out.println("id = " + id);
        // typeList 对象中传递了分类列表，用于分类选择下拉框
        List<Type> typeList = typeService.listType();
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("typeList",typeList);
        model.addAttribute("tagList",tagList);
        // 用于添加或编辑博客，不然没有一个Blog对象去保存form表单提交的值，就会报错
        model.addAttribute("blog", blogService.getBlogById(id));

        return "admin/blog-edit";
    }

    /**
     * 添加博客
     * @return
     */
    @PostMapping("/blog/add")
    public String addBlog(Blog blog, HttpSession session, RedirectAttributes attributes){
        // 在这里设置Blog对象的各个字段值
        // 设置blog的user字段值
        blog.setUser((User) session.getAttribute("user"));
        blog.setUserId(blog.getUser().getId());
        // 设置blog的type字段值，需要获取blog对应type的id
        blog.setType(typeService.getTypeById(blog.getType().getId()));
        blog.setTypeId(blog.getType().getId());
        // 设置发布时间
        Date date = new Date();
        blog.setCreateTime(date);
        blog.setUpdateTime(date);

        int count = blogService.addBlog(blog);
        if(count > 0) {
            attributes.addFlashAttribute("message","发布成功!");
        }else {
            attributes.addFlashAttribute("message","发布失败!请重新发布。");
        }
        return "redirect:/admin/blog/list";
    }

    /**
     * 删除博客
     * @return
     */
    @GetMapping("/blog/delete")
    public String deleteBlog(@RequestParam Long id){
        blogService.deleteBlog(id);
        return "redirect:/admin/blog/list";
    }

    /**
     * 更新博客
     * @return
     */
    @PostMapping("/blog/update")
    public String updateBlog(Blog blog,
                             HttpSession session,
                             RedirectAttributes attributes){
        // 在这里设置Blog对象的各个字段值
        // 设置blog的user字段值
        blog.setUser((User) session.getAttribute("user"));
        blog.setUserId(blog.getUser().getId());
        // 设置blog的type字段值，需要获取blog对应type的id
        blog.setType(typeService.getTypeById(blog.getType().getId()));
        blog.setTypeId(blog.getType().getId());
        // 设置更新时间
        blog.setUpdateTime(new Date());

        int count = blogService.updateBlog(blog);
        if(count > 0) {
            attributes.addFlashAttribute("message","修改成功!");
        }else {
            attributes.addFlashAttribute("message","修改失败!请重新修改并发布。");
        }
        return "redirect:/admin/blog/list";
    }

    /**
     * 显示博客列表
     * @param model
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/blog/list")
    public String listBlog(Model model,
                           @RequestParam(value = "pageNum", defaultValue = "1")  int pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){

        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogList = blogService.listBlogAdmin();
        // PageInfo 对象中封装了关于分页结构的各种信息
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
//        System.out.println(pageInfo.getList());
        // 将 PageInfo 传给前端进行渲染
        model.addAttribute("pageInfo", pageInfo);
        // typeList 对象中传递了分类列表，用于分类选择下拉框
        List<Type> typeList = typeService.listType();
        model.addAttribute("typeList",typeList);

        return "admin/blog-list";
    }

    @PostMapping("/blog/search")
    public String searchBlogAdmin(Blog blog,
                                  Model model,
                                  @RequestParam(value = "pageNum", defaultValue = "1")  int pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){

        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogList = blogService.searchBlogAdmin(blog);
        // PageInfo 对象中封装了关于分页结构的各种信息
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        // 将 PageInfo 传给前端进行渲染
        model.addAttribute("pageInfo", pageInfo);
        // typeList 对象中传递了分类列表，用于分类选择下拉框
        List<Type> typeList = typeService.listType();
        model.addAttribute("typeList",typeList);

        return "admin/blog-list";
    };




}
