package com.starry.dao;

import com.starry.entity.Article;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Repository("articleMapperdao")
public class ArticleMapperImpl extends SqlSessionDaoSupport implements ArticleMapper {
    @Resource
    public void setSuperSessionFactory(SqlSessionFactory sessionFactory){
        this.setSqlSessionFactory(sessionFactory);
    }

    @Override
    public Integer deleteById(Integer articleId) {
        return null;
    }

    @Override
    public Integer insert(Article article) {
        return null;
    }

    @Override
    public Integer update(Article article) {
        System.out.println(1023);
        return this.getSqlSession().update("update",article);
    }

    @Override
    public List<Article> findAll(HashMap<String, Object> criteria) {
        return null;
    }

    @Override
    public List<Article> listAllNotWithContent() {
        return null;
    }

    @Override
    public Integer countArticle(Integer status) {
        return null;
    }

    @Override
    public Integer countArticleComment() {
        return null;
    }

    @Override
    public Integer countArticleView() {
        return null;
    }

    @Override
    public List<Article> listArticle() {
        return null;
    }

    @Override
    public Article getArticleByStatusAndId(Integer id) {
        Article article=this.getSqlSession().selectOne("getArticleByStatusAndId",id);
        return article;
    }

    @Override
    public List<Article> pageArticle(Integer status, Integer pageIndex, Integer pageSize) {
        return null;
    }

    @Override
    public List<Article> listArticleByViewCount(Integer limit) {
        return null;
    }

    @Override
    public Article getAfterArticle(Integer id) {
        return null;
    }

    @Override
    public Article getPreArticle(Integer id) {
        return null;
    }

    @Override
    public List<Article> listRandomArticle(Integer limit) {
        return null;
    }

    @Override
    public List<Article> listArticleByCommentCount(Integer limit) {
        return null;
    }

    @Override
    public void updateCommentCount(Integer articleId) {

    }

    @Override
    public Article getLastUpdateArticle() {
        return null;
    }

    @Override
    public Integer countArticleByUser(Integer id) {
        return null;
    }

    @Override
    public List<Article> findArticleByCategoryId(Integer categoryId, Integer limit) {
        return null;
    }

    @Override
    public List<Article> findArticleByCategoryIds(List<Integer> categoryIds, Integer limit) {
        return null;
    }

    @Override
    public List<Article> listArticleByLimit(Integer limit) {
        return null;
    }

    @Override
    public Integer deleteBatch(List<Integer> ids) {
        return null;
    }
}
