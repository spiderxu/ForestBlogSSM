package com.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xuzhi
 */
@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = 489914127235951698L;
    private Integer menuId;

    private String menuName;

    private String menuUrl;

    /**
     * 1--顶部菜单   2--主体菜单
     */
    private Integer menuLevel;

    private String menuIcon;

    private Integer menuOrder;

}