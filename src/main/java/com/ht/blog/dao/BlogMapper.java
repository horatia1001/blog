package com.ht.blog.dao;

import com.ht.blog.pojo.Blog;
import com.ht.blog.pojo.Type;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogMapper {

    // 后台管理
    int addBlog(Blog blog);

    void deleteBlog(Long id);

    int updateBlog(Blog blog);

    List<Blog> listBlogAdmin();

    List<Blog> searchBlogAdmin(Blog blog);

    Blog getBlogById(Long id);

    // 前台展示
    List<Blog> listBlogIndex();

    List<Blog> searchBlogFront(String keyWord);

    List<Blog> getBlogByTypeId(Long id);

    List<Blog> getBlogByTagId(Long id);

    List<String> getYears();

    List<Blog> getBlogByYear(String year);

    Blog getBlogDetailById(Long id);
}
