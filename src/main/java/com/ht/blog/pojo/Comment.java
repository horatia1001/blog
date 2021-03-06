package com.ht.blog.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Comment {

    private Long id;                // 评论id
    private String nickname;        // 发表评论用户的昵称
    private String email;           // 发表评论用户的邮箱
    private String avatar;          // 发表评论用户的头像
    private String content;         // 发表的评论内容
    private Date createTime;        // 发表评论的时间
    private Blog blog;              // 评论对应的那篇博客
    private Comment parentComment;  // 一条评论可以回复一条评论
    private Long blogId;            // 评论对应的博客id
    private Long parentCommentId;   // 父评论id
    private List<Comment> replyComments = new ArrayList<>();     // 一条评论对应的回复集合，可以有>=0条回复

    private String parentCommentNickname;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getParentCommentNickname() {
        return parentCommentNickname;
    }
    public void setParentCommentNickname(String parentCommentNickname) {
        this.parentCommentNickname = parentCommentNickname;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
