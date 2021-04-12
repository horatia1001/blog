package com.ht.blog.dao;

import com.ht.blog.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public interface UserMapper {

    /**
     * 检查后台管理用户登录：通过用户名和密码查找用户
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
