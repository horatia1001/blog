package com.ht.blog.service.impl;

import com.ht.blog.dao.BlogMapper;
import com.ht.blog.pojo.Blog;
import com.ht.blog.service.BlogService;
import com.ht.blog.util.Markdown2HTMLUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Transactional
    @Override
    public int addBlog(Blog blog) {
        return blogMapper.addBlog(blog);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogMapper.deleteBlog(id);
    }

    @Transactional
    @Override
    public int updateBlog(Blog blog) {
        return blogMapper.updateBlog(blog);
    }

    @Override
    public List<Blog> listBlogAdmin() {
        return blogMapper.listBlogAdmin();
    }

    @Override
    public List<Blog> searchBlogAdmin(Blog blog) {
        return blogMapper.searchBlogAdmin(blog);
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogMapper.getBlogById(id);
    }

    @Override
    public List<Blog> listBlogIndex() {
        return blogMapper.listBlogIndex();
    }

    @Override
    public List<Blog> searchBlogFront(String keyWord) {
        return blogMapper.searchBlogFront(keyWord);
    }

    @Override
    public List<Blog> getBlogByTypeId(Long id) {
        return blogMapper.getBlogByTypeId(id);
    }

    @Override
    public List<Blog> getBlogByTagId(Long id) {
        return blogMapper.getBlogByTagId(id);
    }

    @Override
    public Map<String,List<Blog>> getBlogByYear(String year) {

        // 1.查询出所有年份
        List<String> yearList = blogMapper.getYears();        // mybatis中没有set类型的返回值，可以自行去重，或者取出来用set去重
        HashSet<String> yearSet = new HashSet<>(yearList);    // 去重

        // 2.封装map(年份, 该年份的所有博客集合)
        Map<String, List<Blog>> yearBlogMap = new HashMap<>();
        for(String y : yearSet){
            yearBlogMap.put(y, blogMapper.getBlogByYear(y));
        }

        return yearBlogMap;
    }


    @Override
    public Blog getBlogDetailById(Long id) {
        // 获取博客详情
        Blog blog = blogMapper.getBlogDetailById(id);
        Blog newBlog = new Blog();
        BeanUtils.copyProperties(blog, newBlog);
        // 使用插件将md格式的博客转为HTML
        String markdownText = newBlog.getContent();
        String htmlText = Markdown2HTMLUtils.markdownToHtmlExtensions(markdownText);
        newBlog.setContent(htmlText);
        return newBlog;
    }
}
