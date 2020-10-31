package com.ssm.blog.mapper;

import com.ssm.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: xuzhi
 * @date: 2020/10/31 19:20
 */
@Mapper
public interface UserMapper {
    /**
     * 根据Id删除用户
     * @param userId
     * @return
     */
    int deleteById(@Param("userId") Integer userId);

    /**
     * 新增用户
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    User getUserById(Integer userId);

    /**
     * 更新用户
     * @param user
     * @return
     */
    int update(User user);

    /**
     * 获取用户列表
     * @return
     */
    List<User> listUser();

    /**
     * 根据用户名或者Email来查询用户
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
     * 根据Email查询用户
     * @param email
     * @return
     */
    User getUserByEmail(String email);
}
