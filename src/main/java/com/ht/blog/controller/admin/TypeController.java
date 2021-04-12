package com.ht.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ht.blog.pojo.Type;
import com.ht.blog.pojo.User;
import com.ht.blog.service.TypeService;
import com.ht.blog.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 这里需要先跳转到编辑页面
     * @param model
     * @return
     */
    @GetMapping("/type/toAdd")
    public String toAdd(Model model){
        model.addAttribute("type", new Type());
        return "admin/type-edit";
    }

    /**
     * 这个实现需要再次回顾，因为一开始是不会的。
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/type/toUpdate")
    public String toUpdate(Model model, @RequestParam Long id){
        model.addAttribute("type", typeService.getTypeById(id));
        return "admin/type-edit";
    }

    /**
     * 添加博客分类
     * @return
     */
    @PostMapping("/type/add")
    public String addType(Type type, Model model, RedirectAttributes attributes){
        // TODO 输入数据校验
        // 查看分类是否已经存在
        if(typeService.getTypeByName(type.getName()) != null){
            model.addAttribute("message","该分类已存在，请重新添加！");
            return "admin/type-edit";
        }

        // 添加分类
        int count = typeService.addType(type);
        if(count > 0) {
            attributes.addFlashAttribute("message","添加成功");
        }else {
            attributes.addFlashAttribute("message","添加失败");
        }
        return "redirect:/admin/type/list";
    }

    /**
     * 删除一个分类
     * @param id
     */
    @GetMapping("/type/delete")
    public String deleteType(@RequestParam Long id,
                             @RequestParam int pageNum,
                             RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addAttribute("pageNum",pageNum);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/type/list";
    }

    /**
     * 编辑博客分类
     * @return
     */
    @PostMapping("/type/update")
    public String updateType(Type type,
                             Model model,
                             RedirectAttributes attributes){
        // TODO 输入数据校验
        // 查看分类是否存在，若存在且不重复，则修改
        if(typeService.getTypeByName(type.getName()) != null){
            model.addAttribute("message","该分类已存在，请重新编辑！");
            return "admin/type-edit";
        }

        // 编辑分类
        int count = typeService.updateType(type);
        if(count > 0) {
            attributes.addFlashAttribute("message","编辑成功！");
        }else {
            attributes.addFlashAttribute("message","编辑失败！");
        }
        return "redirect:/admin/type/list";
    }


    /**
     * 博客分类列表分页显示
     * @param model    要保存数据的model
     * @param pageNum  当前页码
     * @param pageSize 每一页显示的记录数
     * @return
     */
    @GetMapping("/type/list")
    public String listType(Model model,
                           @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        // 在你需要进行分页的 MyBatis 查询方法前调用 PageHelper.startPage 即可，紧跟在这个方法后的第一个MyBatis查询方法会被进行分页。
        PageHelper.startPage(pageNum, pageSize);
        List<Type> typeList = typeService.listType();
        // PageInfo 对象中封装了关于分页结构的各种信息
        PageInfo<Type> pageInfo = new PageInfo<>(typeList);
        // 将 PageInfo 传给前端进行渲染
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("typeList",typeList);
        return "admin/type-list";
    }






}
