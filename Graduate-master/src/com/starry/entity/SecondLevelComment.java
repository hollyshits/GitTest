package com.starry.entity;

import java.util.Date;

public class SecondLevelComment {
    private Integer id;
    private Integer sayingId;
    private Integer flcId;
    private String replier;
    private String toCommenter;
    private String replyContent;
    private Date replyTime;
    private FirstLevelComment flc;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSayingId(Integer sayingId) {
        this.sayingId = sayingId;
    }

    public void setFlcId(Integer flcId) {
        this.flcId = flcId;
    }

    public void setReplier(String replier) {
        this.replier = replier;
    }

    public void setToCommenter(String toCommenter) {
        this.toCommenter = toCommenter;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public void setFlc(FirstLevelComment flc) {
        this.flc = flc;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSayingId() {
        return sayingId;
    }

    public Integer getFlcId() {
        return flcId;
    }

    public String getReplier() {
        return replier;
    }

    public String getToCommenter() {
        return toCommenter;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public FirstLevelComment getFlc() {
        return flc;
    }

    @Override
    public String toString() {
        return "SecondLevelComment{" +
                "id=" + id +
                ", sayingId=" + sayingId +
                ", flcId=" + flcId +
                ", replier='" + replier + '\'' +
                ", toCommenter='" + toCommenter + '\'' +
                ", replyContent='" + replyContent + '\'' +
                ", replyTime=" + replyTime +
                ", flc=" + flc +
                '}';
    }
}
