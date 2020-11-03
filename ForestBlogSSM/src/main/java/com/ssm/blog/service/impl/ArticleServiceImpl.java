package com.ssm.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.blog.entity.*;
import com.ssm.blog.enums.ArticleCommentStatus;
import com.ssm.blog.mapper.ArticleCategoryRefMapper;
import com.ssm.blog.mapper.ArticleMapper;
import com.ssm.blog.mapper.ArticleTagRefMapper;
import com.ssm.blog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author: xuzhi
 * @date: 2020/11/3 17:22
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleCategoryRefMapper articleCategoryRefMapper;

    @Autowired
    private ArticleTagRefMapper articleTagRefMapper;


    @Override
    public Integer countArticle(Integer status) {
        Integer count=0;
        try {
           count= articleMapper.countArticle(status);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("count article by status error,status:{},cause:{}",status,e);
        }
        return count;
    }

    @Override
    public Integer countArticleComment() {
        Integer count=0;
        try {
           count= articleMapper.countArticleComment();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("count article comment error,cause:{}",e);
        }
        return count;
    }

    @Override
    public Integer countArticleView() {
        Integer count=0;
        try {
            count= articleMapper.countArticleView();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("count article view error,cause:{}",e);
        }
        return count;
    }

    @Override
    public Integer countArticleByCategoryId(Integer categoryId) {
        Integer count=0;
        try {
            count= articleCategoryRefMapper.countArticleByCategoryId(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("count article by categoryId error,categoryId:{},cause:{}",e);
        }
        return count;
    }

    @Override
    public Integer countArticleByTagId(Integer tagId) {
        Integer count=0;
        try {
            count= articleTagRefMapper.countArticleByTagId(tagId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("count article by tagId error,tagId:{},cause:{}",e);
        }
        return count;
    }

    @Override
    public List<Article> listArticle(HashMap<String, Object> criteria) {
       List<Article> articleList=null;
        try {
           articleList= articleMapper.findAll(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("list article error,criteria:{},cause:{}",criteria,e);
        }
        return articleList;
    }

    @Override
    public List<Article> listRecentArticle(Integer limit) {
        List<Article> articleList=null;
        try {
            articleList= articleMapper.listArticleByLimit(limit);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("list recent article error,limit:{},cause:{}",limit,e);
        }
        return articleList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArticleDetail(Article article) {
        article.setArticleUpdateTime(new Date());
        articleMapper.update(article);

        if (article.getTagList() != null) {
            //删除标签和文章关联
            articleTagRefMapper.deleteByArticleId(article.getArticleId());
            //添加标签和文章关联
            for (int i = 0; i < article.getTagList().size(); i++) {
                ArticleTagRef articleTagRef = new ArticleTagRef(article.getArticleId(), article.getTagList().get(i).getTagId());
                articleTagRefMapper.insert(articleTagRef);
            }
        }

        if (article.getCategoryList() != null) {
            //添加分类和文章关联
            articleCategoryRefMapper.deleteByArticleId(article.getArticleId());
            //删除分类和文章关联
            for (int i = 0; i < article.getCategoryList().size(); i++) {
                ArticleCategoryRef articleCategoryRef = new ArticleCategoryRef(article.getArticleId(), article.getCategoryList().get(i).getCategoryId());
                articleCategoryRefMapper.insert(articleCategoryRef);
            }
        }
    }

    @Override
    public void updateArticle(Article article) {
        try {
            articleMapper.update(article);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("update article error,article:{},cause:{}",article,e);
        }
    }

    @Override
    public void deleteArticleBatch(List<Integer> ids) {
        //TODO 没有删除中间表的数据
        try {
            articleMapper.deleteBatch(ids);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("delete article batch error,ids:{},cause:{}",ids,e);
        }
    }

    @Override
    public void deleteArticle(Integer id) {
        //TODO 没有删除中间表的数据
        try {
            articleMapper.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("delete article by id error,id:{},cause:{}",id,e);
        }
    }

    @Override
    public PageInfo<Article> pageArticle(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Article> articleList = articleMapper.findAll(criteria);
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
    public Article getArticleByStatusAndId(Integer status, Integer id) {
        Article article = articleMapper.getArticleByStatusAndId(status, id);
        if (article != null) {
            List<Category> categoryList = articleCategoryRefMapper.listCategoryByArticleId(article.getArticleId());
            List<Tag> tagList = articleTagRefMapper.listTagByArticleId(article.getArticleId());
            article.setCategoryList(categoryList);
            article.setTagList(tagList);
        }
        return article;
    }

    @Override
    public List<Article> listArticleByViewCount(Integer limit) {
        List<Article> articleList=null;
        try {
            articleList=articleMapper.listArticleByViewCount(limit);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("list article by view count error,limit:{},cause:{}",limit,e);
        }
        return articleList;
    }

    @Override
    public Article getAfterArticle(Integer id) {
        Article article=null;
        try {
           article= articleMapper.getAfterArticle(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("get after article error,id:{},cause:{}",id,e);
        }
        return article;
    }

    @Override
    public Article getPreArticle(Integer id) {
        Article article=null;
        try {
            article= articleMapper.getPreArticle(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("get pre article error,id:{},cause:{}",id,e);
        }
        return article;
    }

    @Override
    public List<Article> listRandomArticle(Integer limit) {
        List<Article> articleList=null;
        try {
          articleList=  articleMapper.listRandomArticle(limit);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("list random article error,limit:{},cause:{}",limit,e);
        }
        return articleList;
    }

    @Override
    public List<Article> listArticleByCommentCount(Integer limit) {
        List<Article> articleList=null;
        try {
            articleList=  articleMapper.listArticleByCommentCount(limit);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("list article by comment count error,limit:{},cause:{}",limit,e);
        }
        return articleList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertArticle(Article article) {
        //添加文章
        article.setArticleCreateTime(new Date());
        article.setArticleUpdateTime(new Date());
        article.setArticleIsComment(ArticleCommentStatus.ALLOW.getValue());
        article.setArticleViewCount(0);
        article.setArticleLikeCount(0);
        article.setArticleCommentCount(0);
        article.setArticleOrder(1);
        articleMapper.insert(article);
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
        try {
            articleMapper.updateCommentCount(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("update comment count error,articleId:{},cause:{}",articleId,e);
        }
    }

    @Override
    public Article getLastUpdateArticle() {
        Article article=null;
        try {
          article=  articleMapper.getLastUpdateArticle();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("get last article error,cause:{}",e);
        }
        return article;
    }

    @Override
    public List<Article> listArticleByCategoryId(Integer cateId, Integer limit) {
        List<Article> articleList=null;
        try {
            articleList= articleMapper.findArticleByCategoryId(cateId,limit);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("list article by categoryId error,categoryId:{},cause:{}",cateId,e);
        }
        return articleList;
    }

    @Override
    public List<Article> listArticleByCategoryIds(List<Integer> cateIds, Integer limit) {
        if (cateIds == null || cateIds.size() == 0) {
            return null;
        }
        List<Article> articleList=null;
        try {
          articleList=  articleMapper.findArticleByCategoryIds(cateIds,limit);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("list article by categoryIds error,cateIds:{},limit:{},cause:{},",cateIds,limit,e);
        }
        return articleList;

    }

    @Override
    public List<Integer> listCategoryIdByArticleId(Integer articleId) {
        List<Integer> list=null;
        try {
          list=  articleCategoryRefMapper.selectCategoryIdByArticleId(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("list categoryId by articleId error,articleId:{},cause:{} ",articleId,e);
        }
        return list;
    }

    @Override
    public List<Article> listAllNotWithContent() {
        List<Article> articleList=null;
        try {
           articleList= articleMapper.listAllNotWithContent();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("list all article with no content error,cause:{}",e);
        }
        return articleList;
    }
}
