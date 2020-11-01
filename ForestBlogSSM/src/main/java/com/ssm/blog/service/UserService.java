package com.ssm.blog.service;

import com.ssm.blog.entity.User;

import java.util.List;

/**
 * @author: xuzhi
 * @date: 2020/11/1 19:10
 */
public interface UserService {

    /**
     * 获取用户列表
     * @return
     */
    List<User> listUser();

    /**
     * 根据userId获取用户信息
     * @param userId
     * @return
     */
    User getUserById(Integer userId);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 增加用户
     * @return
     */
    User insertUser(User user);

    /**
     * 根据用户名或者邮箱查询用户
     * @param str
     * @return
     */
    User getUserByNameOrEmail(String str);

    /**
     * 根据用户名查询用户
     * @param name
     * @return
     */
    User getUserByName(String name);

    /**
     * 根据邮箱查询用户
     * @param email
     * @return
     */
    User getUserByEmail(String email);

}
