package com.starry.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.starry.dao.ArticleCategoryRefMapper;
import com.starry.dao.ArticleMapper;
import com.starry.dao.ArticleTagRefMapper;
import com.starry.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 文章Servie实现
 *
 * @author 言曌
 * @date 2017/8/24
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapperdao;

    @Autowired(required = false)
    private ArticleCategoryRefMapper articleCategoryRefMapper;

    @Autowired(required = false)
    private ArticleTagRefMapper articleTagRefMapper;

    @Override
    public Integer countArticle(Integer status) {
        Integer count = 0;
        try {
            count = articleMapperdao.countArticle(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Integer countArticleComment() {
        Integer count = 0;
        try {
            count = articleMapperdao.countArticleComment();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return count;
    }
    @Override
    public Integer countArticleView() {
        Integer count = 0;
        try {
            count = articleMapperdao.countArticleView();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Integer countArticleByCategoryId(Integer categoryId) {
        Integer count = 0;
        try {
            count = articleCategoryRefMapper.countArticleByCategoryId(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Integer countArticleByTagId(Integer tagId) {
        return articleTagRefMapper.countArticleByTagId(tagId);

    }

    @Override
    public List<Article> listArticle(HashMap<String, Object> criteria) {
        return articleMapperdao.findAll(criteria);
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArticleDetail(Article article) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        article.setArticleCreateTime(Timestamp.valueOf(sdf.format(new Date())));
        articleMapperdao.update(article);

        if (article.getTagList() != null) {
            //删除标签和文章关联
            articleTagRefMapper.deleteByArticleId(article.getArticleId());
            //添加标签和文章关联
            for (int i = 0; i < article.getTagList().size(); i++) {
                ArticleTagRef articleTagRef = new ArticleTagRef(article.getArticleId(), article.getTagList().get(i).getTagId());
                articleTagRefMapper.insert(articleTagRef);
            }
        }

        //*if (article.getCategoryList() != null) {
            //添加分类和文章关联
            articleCategoryRefMapper.deleteByArticleId(article.getArticleId());
            //删除分类和文章关联
            for (int i = 0; i < article.getCategoryList().size(); i++) {
                ArticleCategoryRef articleCategoryRef = new ArticleCategoryRef(article.getArticleId(), article.getCategoryList().get(i).getCategoryId());
                articleCategoryRefMapper.insert(articleCategoryRef);
            }
        }

    @Override
    public void updateArticle(Article article) {
        articleMapperdao.update(article);
    }

    @Override
    public void deleteArticleBatch(List<Integer> ids) {
        articleMapperdao.deleteBatch(ids);
    }

    @Override
    public void deleteArticle(Integer id) {
        articleMapperdao.deleteById(id);
    }


    @Override
    public PageInfo<Article> pageArticle(Integer pageIndex,
                                         Integer pageSize,
                                         HashMap<String, Object> criteria) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Article> articleList = articleMapperdao.findAll(criteria);
        for (int i = 0; i < articleList.size(); i++) {
            //封装CategoryList
            List<Category> categoryList = articleCategoryRefMapper.listCategoryByArticleId(articleList.get(i).getArticleId());
            if (categoryList == null || categoryList.size() == 0) {
                categoryList = new ArrayList<>();
                categoryList.add(Category.Default());
            }
            articleList.get(i).setCategoryList(categoryList);
//            //封装TagList
//            List<Tag> tagList = articleTagRefMapper.listTagByArticleId(articleList.get(i).getArticleId());
//            articleList.get(i).setTagList(tagList);
        }
        return new PageInfo<>(articleList);
    }

    @Override
    public Article getArticleByStatusAndId( Integer id) {
        System.out.println(id);
        Article article = articleMapperdao.getArticleByStatusAndId(id);
        /*if (article != null) {
            List<Category> categoryList = articleCategoryRefMapper.listCategoryByArticleId(article.getArticleId());
            List<Tag> tagList = articleTagRefMapper.listTagByArticleId(article.getArticleId());
            article.setCategoryList(categoryList);
            article.setTagList(tagList);
        }*/
        return article;
    }


    @Override
    public List<Article> listArticleByViewCount(Integer limit) {
        return articleMapperdao.listArticleByViewCount(limit);
    }

    @Override
    public Article getAfterArticle(Integer id) {
        return articleMapperdao.getAfterArticle(id);
    }

    @Override
    public Article getPreArticle(Integer id) {
        return articleMapperdao.getPreArticle(id);
    }

    @Override
    public List<Article> listRandomArticle(Integer limit) {
        return articleMapperdao.listRandomArticle(limit);
    }

    @Override
    public List<Article> listArticleByCommentCount(Integer limit) {
        return articleMapperdao.listArticleByCommentCount(limit);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertArticle(Article article) {
        //添加文章
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        article.setArticleCreateTime(Timestamp.valueOf(sdf.format(new Date())));
        article.setArticleUpdateTime(Timestamp.valueOf(sdf.format(new Date())));
        /*article.setArticleIsComment(ArticleCommentStatus.ALLOW.getValue());*/
        article.setArticleViewCount(0);
        article.setArticleLikeCount(0);
        article.setArticleCommentCount(0);;
        article.setArticleOrder(1);
        articleMapperdao.insert(article);
        //添加分类和文章关联
        for (int i = 0; i < article.getCategoryList().size(); i++) {
            ArticleCategoryRef articleCategoryRef = new ArticleCategoryRef(article.getArticleId(), article.getCategoryList().get(i).getCategoryId());
            articleCategoryRefMapper.insert(articleCategoryRef);
        }
        //添加标签和文章关联
        for (int i = 0; i < article.getTagList().size(); i++) {
            ArticleTagRef articleTagRef = new ArticleTagRef(article.getArticleId(), article.getTagList().get(i).getTagId());
            articleTagRefMapper.insert(articleTagRef);
        }
    }


    @Override
    public void updateCommentCount(Integer articleId) {
        articleMapperdao.updateCommentCount(articleId);
    }

    @Override
    public Article getLastUpdateArticle() {
        return articleMapperdao.getLastUpdateArticle();
    }

    @Override
    public List<Article> listArticleByCategoryId(Integer cateId, Integer limit) {
        return articleMapperdao.findArticleByCategoryId(cateId, limit);
    }

    @Override
    public List<Article> listArticleByCategoryIds(List<Integer> cateIds, Integer limit) {
        if (cateIds == null || cateIds.size() == 0) {
            return null;
        }
        return articleMapperdao.findArticleByCategoryIds(cateIds, limit);
    }

    @Override
    public List<Integer> listCategoryIdByArticleId(Integer articleId) {
        return articleCategoryRefMapper.selectCategoryIdByArticleId(articleId);
    }

    @Override
    public List<Article> listAllNotWithContent() {
        return articleMapperdao.listAllNotWithContent();
    }


}
