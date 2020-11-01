package com.ssm.blog.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author: xuzhi
 * @date: 2020/10/31 18:33
 * 用户表
 */
@Data
public class User {
    private static final long serialVersionUID = -4415517704211731385L;

    private Integer userId;
    private String userName;
    /**
     * 用户密码，不能为null
     */
    private String userPass;
    private String userNickname;
    private String userEmail;
    private String userUrl;
    private String userAvatar;
    private String userLastLoginIp;
    private Date userRegisterTime;
    private Date userLastLoginTime;
    private Integer userStatus;

    /**
     * 文章数量（不是数据库字段）
     */
    private Integer articleCount;

}
