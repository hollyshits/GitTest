package com.starry.entity;

import java.util.Date;
import java.util.List;

public class Saying {
    private Integer id;
    private String sayingContent;
    private String author;
    private String avatar;
    private String likes;
    private Date createTime;
    private String title;
    private List<FirstLevelComment> flcs;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSayingContent(String sayingContent) {
        this.sayingContent = sayingContent;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setFlcs(List<FirstLevelComment> flcs) {
        this.flcs = flcs;
    }

    public Integer getId() {
        return id;
    }

    public String getSayingContent() {
        return sayingContent;
    }

    public String getAuthor() {
        return author;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getLikes() {
        return likes;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public List<FirstLevelComment> getFlcs() {
        return flcs;
    }

    @Override
    public String toString() {
        return "Saying{" +
                "id=" + id +
                ", sayingContent='" + sayingContent + '\'' +
                ", author='" + author + '\'' +
                ", avatar='" + avatar + '\'' +
                ", likes='" + likes + '\'' +
                ", createTime=" + createTime +
                ", flcs=" + flcs +
                '}';
    }
}
