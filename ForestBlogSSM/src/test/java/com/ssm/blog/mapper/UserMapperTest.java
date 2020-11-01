package com.ssm.blog.mapper;

import com.ssm.blog.entity.User;
import com.ssm.blog.util.ObjectFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author: xuzhi
 * @date: 2020/11/1 18:35
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mybatis.xml"})
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 测试插入和查询
     */
    @Test
    @Transactional
    public void  insert_and_getUserById_test(){
        User origin= ObjectFactory.getUser();
        userMapper.insert(origin);

        User user = userMapper.getUserById(origin.getUserId());

        assertThat(user.getUserId()).isEqualTo(origin.getUserId());
        assertThat(user.getUserName()).isEqualTo(origin.getUserName());
        assertThat(user.getUserNickname()).isEqualTo(origin.getUserNickname());
    }

}
