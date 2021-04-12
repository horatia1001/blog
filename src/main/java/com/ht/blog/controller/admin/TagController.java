package com.ht.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ht.blog.pojo.Tag;

import com.ht.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 这里需要先跳转到编辑页面
     * @param model
     * @return
     */
    @GetMapping("/tag/toAdd")
    public String toAdd(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tag-edit";
    }

    /**
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/tag/toUpdate")
    public String toUpdate(Model model, @RequestParam Long id){
        model.addAttribute("tag", tagService.getTagById(id));
        return "admin/tag-edit";
    }

    /**
     * 添加博客标签
     * @return
     */
    @PostMapping("/tag/add")
    public String addTag(Tag tag, Model model, RedirectAttributes attributes){
        // TODO 输入数据校验
        // 查看分类是否已经存在
        if(tagService.getTagByName(tag.getName()) != null){
            model.addAttribute("message","该分类已存在，请重新添加！");
            return "admin/tag-edit";
        }

        // 添加分类
        int count = tagService.addTag(tag);
        if(count > 0) {
            attributes.addFlashAttribute("message","添加成功");
        }else {
            attributes.addFlashAttribute("message","添加失败");
        }
        return "redirect:/admin/tag/list";
    }

    /**
     * 删除一个分类
     * @param id
     */
    @GetMapping("/tag/delete")
    public String deleteTag(@RequestParam Long id,
                             @RequestParam int pageNum,
                             RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addAttribute("pageNum",pageNum);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tag/list";
    }

    /**
     * 编辑博客分类
     * @return
     */
    @PostMapping("/tag/update")
    public String updateTag(Tag tag,
                             Model model,
                             RedirectAttributes attributes){
        // TODO 输入数据校验
        // 查看分类是否存在，若存在且不重复，则修改
        if(tagService.getTagByName(tag.getName()) != null){
            model.addAttribute("message","该分类已存在，请重新编辑！");
            return "admin/tag-edit";
        }

        // 编辑分类
        int count = tagService.updateTag(tag);
        if(count > 0) {
            attributes.addFlashAttribute("message","编辑成功！");
        }else {
            attributes.addFlashAttribute("message","编辑失败！");
        }
        return "redirect:/admin/tag/list";
    }


    /**
     * 博客分类列表分页显示
     * @param model    要保存数据的model
     * @param pageNum  当前页码
     * @param pageSize 每一页显示的记录数
     * @return
     */
    @GetMapping("/tag/list")
    public String listTag(Model model,
                           @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        // 在你需要进行分页的 MyBatis 查询方法前调用 PageHelper.startPage 即可，紧跟在这个方法后的第一个MyBatis查询方法会被进行分页。
        PageHelper.startPage(pageNum, pageSize);
        List<Tag> tagList = tagService.listTag();
        // PageInfo 对象中封装了关于分页结构的各种信息
        PageInfo<Tag> pageInfo = new PageInfo<>(tagList);
        // 将 PageInfo 传给前端进行渲染
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("tagList",tagList);
        return "admin/tag-list";
    }






}
