package com.ht.blog.controller.admin;

import com.ht.blog.pojo.User;
import com.ht.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){

        User user = userService.loginCheck(username, password);
        if (user == null){
            // 返回错误信息并重定向到登录页面（这里不能使用Model和Session，因为redirect无法添加信息到model或session）
            // 若使用model.addAttribute("message","hh");  message=hh会在redirect返回的url中体现。
            attributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/admin";
        }else {
            // 登录成功：出于安全考虑，密码置为空，这样返回除了密码以外的用户信息。
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/success";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session){
        // 删除session中存储的用户信息
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
