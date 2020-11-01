package com.ssm.blog.util;

import com.ssm.blog.entity.User;

/**
 * @author: xuzhi
 * @date: 2020/11/1 18:41
 */
public class ObjectFactory {

    public static User getUser(){
        User user=new User();
        user.setUserId(5555);
        user.setUserName("xuzhi");
        user.setUserPass("123456");
        user.setUserNickname("haha");

        return user;
    }
}
