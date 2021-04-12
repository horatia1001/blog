package com.ht.blog.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 博客分类类
 */
public class Type {

    private Long id;        // 分类的id
    private String name;    // 分类名称
    private List<Blog> blogs = new ArrayList<>();   // 分类Type和博客Blog是一对多的关系
//    private Integer blogCount = blogs.size();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
