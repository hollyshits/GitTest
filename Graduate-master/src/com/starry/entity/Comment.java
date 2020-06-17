package com.starry.entity;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 文章评论
 * @author liuyanzhao
 */
public class Comment implements Serializable{

    public Comment(){}

    private static final long serialVersionUID = -1038897351672911219L;
    private Integer commentId;

    private Integer commentPid;

    private String commentPname;

    private Integer commentArticleId;

    private String commentAuthorName;

    private String commentAuthorEmail;

    private String commentAuthorUrl;

    private String commentAuthorAvatar;

    private String commentContent;

    private String commentAgent;

    private String commentIp;

    private Date commentCreateTime;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommentPid() {
        return commentPid;
    }

    public void setCommentPid(Integer commentPid) {
        this.commentPid = commentPid;
    }

    public String getCommentPname() {
        return commentPname;
    }

    public void setCommentPname(String commentPname) {
        this.commentPname = commentPname;
    }

    public Integer getCommentArticleId() {
        return commentArticleId;
    }

    public void setCommentArticleId(Integer commentArticleId) {
        this.commentArticleId = commentArticleId;
    }

    public String getCommentAuthorName() {
        return commentAuthorName;
    }

    public void setCommentAuthorName(String commentAuthorName) {
        this.commentAuthorName = commentAuthorName;
    }

    public String getCommentAuthorEmail() {
        return commentAuthorEmail;
    }

    public void setCommentAuthorEmail(String commentAuthorEmail) {
        this.commentAuthorEmail = commentAuthorEmail;
    }

    public String getCommentAuthorUrl() {
        return commentAuthorUrl;
    }

    public void setCommentAuthorUrl(String commentAuthorUrl) {
        this.commentAuthorUrl = commentAuthorUrl;
    }

    public String getCommentAuthorAvatar() {
        return commentAuthorAvatar;
    }

    public void setCommentAuthorAvatar(String commentAuthorAvatar) {
        this.commentAuthorAvatar = commentAuthorAvatar;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Comment(String commentContent,Integer commentPid, String commentPname,Integer commentRole,String commentAuthorName, String commentAuthorEmail,String commentAuthorUrl, Integer commentArticleId) {
        this.commentPid = commentPid;
        this.commentPname = commentPname;
        this.commentArticleId = commentArticleId;
        this.commentAuthorName = commentAuthorName;
        this.commentAuthorEmail = commentAuthorEmail;
        this.commentAuthorUrl = commentAuthorUrl;
        this.commentContent = commentContent;
        this.commentRole = commentRole;
    }

    public String getCommentAgent() {
        return commentAgent;
    }

    public void setCommentAgent(String commentAgent) {
        this.commentAgent = commentAgent;
    }

    public String getCommentIp() {
        return commentIp;
    }

    public void setCommentIp(String commentIp) {
        this.commentIp = commentIp;
    }

    public Date getCommentCreateTime() {
        return commentCreateTime;
    }

    public void setCommentCreateTime(Date commentCreateTime) {
        this.commentCreateTime = commentCreateTime;
    }

    public Integer getCommentRole() {
        return commentRole;
    }

    public void setCommentRole(Integer commentRole) {
        this.commentRole = commentRole;
    }

    /**
     * 角色(管理员1，访客0)
     */
    private Integer commentRole;

    /**
     * 非数据库字段
     */
    private Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Comment(Integer commentId, Integer commentPid, String commentPname, Integer commentArticleId, String commentAuthorName, String commentAuthorEmail, String commentAuthorUrl, String commentAuthorAvatar, String commentContent, String commentAgent, String commentIp, Timestamp commentCreateTime, Integer commentRole) {
        this.commentId = commentId;
        this.commentPid = commentPid;
        this.commentPname = commentPname;
        this.commentArticleId = commentArticleId;
        this.commentAuthorName = commentAuthorName;
        this.commentAuthorEmail = commentAuthorEmail;
        this.commentAuthorUrl = commentAuthorUrl;
        this.commentAuthorAvatar = commentAuthorAvatar;
        this.commentContent = commentContent;
        this.commentAgent = commentAgent;
        this.commentIp = commentIp;
        this.commentCreateTime = commentCreateTime;
        this.commentRole = commentRole;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentPid=" + commentPid +
                ", commentPname='" + commentPname + '\'' +
                ", commentArticleId=" + commentArticleId +
                ", commentAuthorName='" + commentAuthorName + '\'' +
                ", commentAuthorEmail='" + commentAuthorEmail + '\'' +
                ", commentAuthorUrl='" + commentAuthorUrl + '\'' +
                ", commentAuthorAvatar='" + commentAuthorAvatar + '\'' +
                ", commentContent='" + commentContent + '\'' +
                ", commentAgent='" + commentAgent + '\'' +
                ", commentIp='" + commentIp + '\'' +
                ", commentCreateTime=" + commentCreateTime +
                ", commentRole=" + commentRole +
                ", article=" + article +
                '}';
    }
}