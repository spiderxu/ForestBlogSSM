package com.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: xuzhi
 * @date: 2020/10/31 18:28
 * 文章表
 */
@Data
public class Article implements Serializable {
    private static final long serialVersionUID = 5207865247400761539L;

    private Integer articleId;
    private Integer articleUserId;
    private String articleTitle;
    private String articleContent;
    private Integer articleViewCount;
    private Integer articleCommentCount;
    private Integer articleLikeCount;
    private Integer articleIsComment;
    private Integer articleStatus;
    private Integer articleOrder;
    private Date articleUpdateTime;
    private Date articleCreateTime;
    private String articleSummary;

    private User user;
    private List<Tag> tagList;
    private List<Category> categoryList;
}
