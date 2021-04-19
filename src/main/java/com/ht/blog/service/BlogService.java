package com.ht.blog.service;

import com.ht.blog.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {
    /**
     * 发布博客
     * @param blog
     * @return
     */
    int addBlog(Blog blog);

    /**
     * 删除博客
     * @param id
     */
    void deleteBlog(Long id);

    /**
     * 更新博客
     * @param blog
     * @return
     */
    int updateBlog(Blog blog);

    /**
     * 查询博客列表 分页
     * @return
     */
    List<Blog> listBlogAdmin();

    /**
     * 博客管理页面搜索指定博客
     * @return
     */
    List<Blog> searchBlogAdmin(Blog blog);

    /**
     * 根据id 查询博客 分页
     * @param id
     * @return
     */
    Blog getBlogById(Long id);

    //----------------------------------------
    /**
     * 首页博客列表展示
     * @return
     */
    List<Blog> listBlogIndex();

    /**
     * 首页搜索博客展示
     * @return
     */
    List<Blog> searchBlogFront(String keyWord);

    /**
     * 分类页面显示博客
     * @param id
     * @return
     */
    List<Blog> getBlogByTypeId(Long id);

    /**
     * 标签页面显示博客
     * @param id
     * @return
     */
    List<Blog> getBlogByTagId(Long id);

    /**
     * 根据年份查询博客
     * @param year
     * @return
     */
    Map<String,List<Blog>> getBlogByYear(String year);

    /**
     * 根据博客id查询博客详情
     * @param id
     * @return
     */
    Blog getBlogDetailById(Long id);
}
