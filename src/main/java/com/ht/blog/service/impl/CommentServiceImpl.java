package com.ht.blog.service.impl;

import com.ht.blog.dao.CommentMapper;
import com.ht.blog.pojo.Comment;
import com.ht.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> listCommentByBlogId(Long id) {
        return commentMapper.listCommentByBlogId(id);
    }

    @Override
    public int addComment(Comment comment) {
        // 设置用户头像
        String avatarURL = "http://localhost:8080/static/images/avatar_visitor.png";
        comment.setAvatar(avatarURL);
        // 设置发布时间
        Date date = new Date();
        comment.setCreateTime(date);
        // 设置父评论
        Long id = comment.getParentComment().getId();
        comment.setParentCommentId(id);
        if(id == -1){                              // 顶级评论
            comment.setParentComment(null);
        }else {                                    // 回复评论
            comment.setParentComment(commentMapper.getCommentById(id));
        }
        return commentMapper.addComment(comment);
    }







    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    /**
     * @Description: 查询评论
     * @Param:
     * @Return: 评论消息
     */
    @Override
    public List<Comment> listComment() {
        //查询出父节点
        List<Comment> comments = commentMapper.findByParentIdNull(Long.parseLong("-1"));
        for(Comment comment : comments){
            Long id = comment.getId();
            String parentNickname1 = comment.getNickname();
            List<Comment> childComments = commentMapper.findByParentIdNotNull(id);
            //查询出子评论
            combineChildren(childComments, parentNickname1);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return comments;
    }

    /**
     * @Description: 查询出子评论
     * @Param: childComments：所有子评论
     * @Param: parentNickname1：父评论的姓名
     * @Return:
     */
    private void combineChildren(List<Comment> childComments, String parentNickname1) {
        //判断是否有一级子回复
        if(childComments.size() > 0){
            //循环找出子评论的id
            for(Comment childComment : childComments){
                String parentNickname = childComment.getNickname();
                childComment.setParentCommentNickname(parentNickname1);
                tempReplys.add(childComment);
                Long childId = childComment.getId();
                //查询二级以及所有子集回复
                recursively(childId, parentNickname);
            }
        }
    }

    /**
     * @Description: 循环迭代找出子集回复
     * @Param: childId：子评论的id
     * @Param: parentNickname1：子评论的姓名
     * @Return:
     */
    private void recursively(Long childId, String parentNickname1) {
        //根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentMapper.findByReplayId(childId);

        if(replayComments.size() > 0){
            for(Comment replayComment : replayComments){
                String parentNickname = replayComment.getNickname();
                replayComment.setParentCommentNickname(parentNickname1);
                Long replayId = replayComment.getId();
                tempReplys.add(replayComment);
                //循环迭代找出子集回复
                recursively(replayId,parentNickname);
            }
        }
    }


}
