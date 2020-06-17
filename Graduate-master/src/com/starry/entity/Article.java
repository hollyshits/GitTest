package com.starry.entity;

import javax.persistence.Entity;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author liuyanzhao
 */
@Entity
public class Article implements Serializable{

    private static final long serialVersionUID = 5207865247400761539L;

    private Integer articleId;

    private Integer articleUserId;

    private String articleTitle;

    private Integer articleViewCount;

    private Integer articleCommentCount;

    private Integer articleLikeCount;

    private Timestamp articleCreateTime;

    private Timestamp articleUpdateTime;

    private Integer articleIsComment;

    private Integer articleStatus;

    private Integer articleOrder;

    private String docname;

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }
    public Article(Integer articleId, Integer articleUserId, String articleTitle,String articleContent, Integer articleViewCount, Integer articleCommentCount, Integer articleLikeCount,Integer articleIsComment,Integer articleStatus,Integer articleOrder, Timestamp articleCreateTime, Timestamp articleUpdateTime,   String articleSummary,String docname) {
        this.articleId = articleId;
        this.articleUserId = articleUserId;
        this.articleTitle = articleTitle;
        this.articleViewCount = articleViewCount;
        this.articleCommentCount = articleCommentCount;
        this.articleLikeCount = articleLikeCount;
        this.articleCreateTime = articleCreateTime;
        this.articleCreateTime = articleUpdateTime;
        this.articleIsComment = articleIsComment;
        this.articleStatus = articleStatus;
        this.articleOrder = articleOrder;
        this.articleContent = articleContent;
        this.articleSummary = articleSummary;
        this.docname=docname;
    }
    public Article(Integer articleId, Integer articleUserId, String articleTitle, String articleContent, Integer articleViewCount, Integer articleCommentCount, Integer articleLikeCount, Integer articleIsComment, Integer articleStatus, Integer articleOrder, Timestamp articleCreateTime, Timestamp articleUpdateTime, String articleSummary) {
        this.articleId = articleId;
        this.articleUserId = articleUserId;
        this.articleTitle = articleTitle;
        this.articleViewCount = articleViewCount;
        this.articleCommentCount = articleCommentCount;
        this.articleLikeCount = articleLikeCount;
        this.articleCreateTime = articleCreateTime;
        this.articleCreateTime = articleUpdateTime;
        this.articleIsComment = articleIsComment;
        this.articleStatus = articleStatus;
        this.articleOrder = articleOrder;
        this.articleContent = articleContent;
        this.articleSummary = articleSummary;
    }

    private String articleContent;

    private String articleSummary;

    //private User user;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getArticleUserId() {
        return articleUserId;
    }

    public void setArticleUserId(Integer articleUserId) {
        this.articleUserId = articleUserId;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", articleUserId=" + articleUserId +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleViewCount=" + articleViewCount +
                ", articleCommentCount=" + articleCommentCount +
                ", articleLikeCount=" + articleLikeCount +
                ", articleCreateTime=" + articleCreateTime +
                ", articleUpdateTime=" + articleUpdateTime +
                ", articleIsComment=" + articleIsComment +
                ", articleStatus=" + articleStatus +
                ", articleOrder=" + articleOrder +
                ", articleContent='" + articleContent + '\'' +
                ", articleSummary='" + articleSummary + '\'' +
                ", user="  +
                '}';
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public Integer getArticleViewCount() {
        return articleViewCount;
    }

    public void setArticleViewCount(Integer articleViewCount) {
        this.articleViewCount = articleViewCount;
    }

    public Integer getArticleCommentCount() {
        return articleCommentCount;
    }

    public void setArticleCommentCount(Integer articleCommentCount) {
        this.articleCommentCount = articleCommentCount;
    }

    public Integer getArticleLikeCount() {
        return articleLikeCount;
    }

    public void setArticleLikeCount(Integer articleLikeCount) {
        this.articleLikeCount = articleLikeCount;
    }

    public Date getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(Timestamp articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    public Date getArticleUpdateTime() {
        return articleUpdateTime;
    }

    public void setArticleUpdateTime(Timestamp articleUpdateTime) {
        this.articleUpdateTime = articleUpdateTime;
    }

    public Integer getArticleIsComment() {
        return articleIsComment;
    }

    public void setArticleIsComment(Integer articleIsComment) {
        this.articleIsComment = articleIsComment;
    }

    public Integer getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
    }

    public Integer getArticleOrder() {
        return articleOrder;
    }

    public void setArticleOrder(Integer articleOrder) {
        this.articleOrder = articleOrder;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleSummary() {
        return articleSummary;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary;
    }

    /*public User getUser() {
        return user;
    }*/

    /*public void setUser(User user) {
        this.user = user;
    }*/

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    private List<Tag> tagList;

    private List<Category> categoryList;

}