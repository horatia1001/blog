package com.ht.blog.pojo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 博客类
 */
public class Blog {

    private Long id;         // 博客id
    private String title;    // 博客标题
    private String content;  // 博客内容
    private String indexPicture;  // 首图url
    private String flag;          // 标记：原创、转载、翻译
    private Integer viewTimes;    // 博客的访问次数

    private boolean showAppreciation;    // 是否开启赞赏功能
    private boolean showCopyright;       // 是否显示转载信息
    private boolean showComment;         // 是否开启评论功能
    private boolean isPublished;         // 博客是否已经发布，未发布的保存为草稿
    private boolean isRecommend;         // 是否推荐

    private Date createTime;      // 博客发布时间
    private Date updateTime;      // 博客编辑时间

    private Long typeId;          // 分类id
    private String tagIds;        // 标签id
    private Long userId;          // 发表此博客的用户（管理员）
    private String description;   // 博客简介

    private Type type;      // 博客分类
    private User user;      // 发布博客的用户
    private List<Tag> tags = new ArrayList<>();           // 标签列表
    private List<Comment> comments = new ArrayList<>();   // 评论列表

    public Blog(){ }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIndexPicture() {
        return indexPicture;
    }

    public void setIndexPicture(String indexPicture) {
        this.indexPicture = indexPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(Integer viewTimes) {
        this.viewTimes = viewTimes;
    }

    public boolean isShowAppreciation() {
        return showAppreciation;
    }

    public void setShowAppreciation(boolean showAppreciation) {
        this.showAppreciation = showAppreciation;
    }

    public boolean isShowCopyright() {
        return showCopyright;
    }

    public void setShowCopyright(boolean showCopyright) {
        this.showCopyright = showCopyright;
    }

    public boolean isShowComment() {
        return showComment;
    }

    public void setShowComment(boolean showComment) {
        this.showComment = showComment;
    }

    public boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(boolean published) {
        isPublished = published;
    }

    public boolean getIsRecommend() { return isRecommend; }

    public void setIsRecommend(boolean recommend) { isRecommend = recommend; }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", indexPicture='" + indexPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", viewTimes=" + viewTimes +
                ", showAppreciation=" + showAppreciation +
                ", showCopyright=" + showCopyright +
                ", showComment=" + showComment +
                ", isPublished=" + isPublished +
                ", isRecommend=" + isRecommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", typeId=" + typeId +
                ", tagIds='" + tagIds + '\'' +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                '}';
    }
}
