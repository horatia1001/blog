package com.ht.blog.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 博客标签类
 */
public class Tag {
    /**
     * 标签id
     */
    private Long id;

    /**
     * 标签名
     */
    private String name;

    /**
     * 标签Tag和博客Blog是一对多的关系
     */
    private List<Blog> blogs = new ArrayList<>();

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
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
