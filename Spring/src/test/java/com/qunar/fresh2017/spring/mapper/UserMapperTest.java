package com.qunar.fresh2017.spring.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qunar.fresh2017.spring.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.Date;
import java.util.List;

@ContextConfiguration(locations = {"classpath:spring/mybatis.xml", "classpath:spring/property.xml",
        "classpath:spring/druid.xml"})
public class UserMapperTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    UserMapper userMapper;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testDeleteByPrimaryKey() throws Exception {
        System.out.println(userMapper.deleteByPrimaryKey(1));
    }

    @Test
    public void testSelectByPrimaryKey() throws Exception {
        System.out.println(objectMapper.writeValueAsString(userMapper.selectByPrimaryKey(2)));
    }

    @Test
    public void testFindAll() throws Exception {
        List<User> list = userMapper.findAll();
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            System.out.println("user " + i + " = " + objectMapper.writeValueAsString(user));
        }
    }

    @Test
    public void testInsert() throws Exception {
        User user = new User();
        user.setName("Test");
        user.setGender(true);
        user.setAge(20);
        user.setComment("测试");
        user.setValidity(false);
        user.setCreateTime(new Date());
        user.setLastModifiedTime(new Date());
        System.out.println(userMapper.insert(user));
    }

    @Test
    public void testUpdateByPrimaryKey() throws Exception {
        User user = new User();
        user.setId(3);
        user.setName("Test");
        user.setGender(true);
        user.setAge(20);
        user.setComment("测试");
        user.setValidity(false);
        user.setCreateTime(new Date());
        user.setLastModifiedTime(new Date());
        System.out.println(userMapper.updateByPrimaryKey(user));
    }
}