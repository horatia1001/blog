package com.ht.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ht.blog.pojo.Type;
import com.ht.blog.pojo.User;
import com.ht.blog.service.TypeService;
import com.ht.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 添加博客分类
     * @return
     */
    @PostMapping("/type/add")
    public String addType(Type type){
        // 校验输入

        typeService.addType(type);
        return "admin/type-edit";
    }

    /**
     * 删除一个分类
     * @param id
     */
    @GetMapping("/type/delete")
    public String deleteType(@RequestParam Long id){
        typeService.deleteType(id);
        return "redirect:typeList";
    }

    /**
     * 编辑博客分类
     * @return
     */
    @GetMapping("/type/update")
    public String updateType(Type type){
        typeService.updateType(type);
        return "admin/type-edit";
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
