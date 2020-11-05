package com.ssm.blog.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: xuzhi
 * @date: 2020/11/5 13:59
 */
@Data
public class ArticleParam {
    private Integer articleId;

    private String articleTitle;

    private String articleContent;

    private Integer articleParentCategoryId;

    private Integer articleChildCategoryId;

    private Integer articleOrder;

    private Integer articleStatus;

    private List<Integer> articleTagIds;
}
