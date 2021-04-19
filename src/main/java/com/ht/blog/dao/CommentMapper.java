package com.ht.blog.dao;

import com.ht.blog.pojo.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {

    List<Comment> listCommentByBlogId(Long id);

    int addComment(Comment comment);

    Comment getCommentById(Long id);

    //查询父级评论
    List<Comment> findByParentIdNull(@Param("ParentId") Long ParentId);
    //查询一级回复
    List<Comment> findByParentIdNotNull(@Param("id") Long id);
    //查询二级以及所有子集回复
    List<Comment> findByReplayId(@Param("childId") Long childId);

}
