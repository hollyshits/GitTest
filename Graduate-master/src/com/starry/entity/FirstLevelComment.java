package com.starry.entity;

import java.util.Date;
import java.util.List;

public class FirstLevelComment {
    private Integer id;
    private Integer sayingId;
    private String commenter;
    private String avatar;
    private String commentContent;
    private Date commentTime;
    private Saying saying;
    private List<SecondLevelComment> slcs;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSayingId(Integer sayingId) {
        this.sayingId = sayingId;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public void setSaying(Saying saying) {
        this.saying = saying;
    }

    public void setSlcs(List<SecondLevelComment> slcs) {
        this.slcs = slcs;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSayingId() {
        return sayingId;
    }

    public String getCommenter() {
        return commenter;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public Saying getSaying() {
        return saying;
    }

    public List<SecondLevelComment> getSlcs() {
        return slcs;
    }

    @Override
    public String toString() {
        return "FirstLevelComment{" +
                "id=" + id +
                ", sayingId=" + sayingId +
                ", commenter='" + commenter + '\'' +
                ", avatar='" + avatar + '\'' +
                ", commentContent='" + commentContent + '\'' +
                ", commentTime=" + commentTime +
                ", saying=" + saying +
                ", slcs=" + slcs +
                '}';
    }
}
