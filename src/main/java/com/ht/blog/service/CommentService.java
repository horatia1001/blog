package com.ht.blog.service;

import com.ht.blog.pojo.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listCommentByBlogId(Long id);

    int addComment(Comment comment);




    List<Comment> listComment();

}
