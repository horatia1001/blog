package com.ht.blog.service;

import com.ht.blog.pojo.User;

public interface UserService {
    /**
     * 检查后台管理用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    User loginCheck(String username, String password);



}
