package com.ssm.blog.mapper;

import com.ssm.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: xuzhi
 * @date: 2020/11/1 19:18
 */
@Mapper
public interface ArticleMapper {

    /**
     * 根据ID删除
     * @param articleId
     * @return
     */
    Integer deleteById(Integer articleId);

    /**
     * 添加文章
     * @param article
     * @return
     */
    Integer insert(Article article);

    /**
     * 更新文章
     * @param article
     * @return
     */
    Integer update(Article article);

    /**
     * 获取所有的文章
     * @param criteria
     * @return
     */
    List<Article> findAll(Map<String,Object> criteria);

    /**
     * 文章归档
     * @return
     */
    List<Article> listAllNotWithContent();

    /**
     * 获取文章总数
     * @param status
     * @return
     */
    Integer countArticle(Integer status);

    /**
     * 获取文章留言总数
     * @return
     */
    Integer countArticleComment();

    /**
     * 获取文章浏览量
     * @return
     */
    Integer countArticleView();

    /**
     * 获取所有文章
     * @return
     */
    List<Article> listArticle();

    /**
     * 获取文章信息
     * @param status
     * @param id
     * @return
     */
    Article getArticleByStatusAndId(@Param("status") Integer status,@Param("id")Integer id);

    /**
     * 统计该用户文章数量
     * @param userId
     * @return
     */
    Integer countArticleByUser(Integer userId);

    /**
     * 分页操作
     *
     * @param status    状态
     * @param pageIndex 从第几页开始
     * @param pageSize  数量
     * @return 文章列表
     */
    @Deprecated
    List<Article> pageArticle(@Param(value = "status") Integer status,
                              @Param(value = "pageIndex") Integer pageIndex,
                              @Param(value = "pageSize") Integer pageSize);


    /**
     * 获得访问最多的文章(猜你喜欢)
     *
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listArticleByViewCount(@Param(value = "limit") Integer limit);

    /**
     * 获得下一篇文章
     *
     * @param id 文章ID
     * @return 文章
     */
    Article getAfterArticle(@Param(value = "id") Integer id);

    /**
     * 获得上一篇文章
     *
     * @param id 文章ID
     * @return 文章
     */
    Article getPreArticle(@Param(value = "id") Integer id);

    /**
     * 获得随机文章
     *
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listRandomArticle(@Param(value = "limit") Integer limit);

    /**
     * 热评文章
     *
     * @param limit  查询数量
     * @return 文章列表
     */
    List<Article> listArticleByCommentCount(@Param(value = "limit") Integer limit);



    /**
     * 更新文章的评论数
     *
     * @param articleId 文章ID
     */
    void updateCommentCount(@Param(value = "articleId") Integer articleId);

    /**
     * 获得最后更新的记录
     *
     * @return 文章
     */
    Article getLastUpdateArticle();


    /**
     * 根据分类ID
     *
     * @param categoryId 分类ID
     * @param limit      查询数量
     * @return 文章列表
     */
    List<Article> findArticleByCategoryId(@Param("categoryId") Integer categoryId,
                                          @Param("limit") Integer limit);

    /**
     * 根据分类ID
     *
     * @param categoryIds 分类ID集合
     * @param limit       查询数量
     * @return 文章列表
     */
    List<Article> findArticleByCategoryIds(@Param("categoryIds") List<Integer> categoryIds,
                                           @Param("limit") Integer limit);

    /**
     * 获得最新文章
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Article> listArticleByLimit(Integer limit);

    /**
     * 批量删除文章
     *
     * @param ids 文章Id列表
     * @return 影响行数
     */
    Integer deleteBatch(@Param("ids") List<Integer> ids);
}
