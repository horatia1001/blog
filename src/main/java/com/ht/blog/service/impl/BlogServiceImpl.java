package com.ht.blog.service.impl;

import com.ht.blog.dao.BlogMapper;
import com.ht.blog.pojo.Blog;
import com.ht.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Blog> getBlogByTypeId(Long id) {
        return blogMapper.getBlogByTypeId(id);
    }
}
