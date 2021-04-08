package com.ht.blog.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Comment {

    /**
     * 评论id
     */
    private Integer id;

    /**
     * 发表评论用户的昵称
     */
    private String nickname;

    /**
     * 发表评论用户的邮箱
     */
    private String email;

    /**
     * 发表评论用户的头像
     */
    private String avatar;

    /**
     * 发表的评论内容
     */
    private String content;

    /**
     * 发表评论的时间
     */
    private Date createTime;

    /**
     * 评论对应的那篇博客
     */
    private Blog blog;

    /**
     * 一条评论可以有 >=0 条回复
     */
    private List<Comment> replyComments = new ArrayList<>();

    /**
     * 一条评论可以回复一条评论
     */
    private Comment parentComment;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
