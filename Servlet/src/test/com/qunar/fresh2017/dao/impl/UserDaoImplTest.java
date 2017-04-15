package com.qunar.fresh2017.dao.impl;

import com.qunar.fresh2017.model.User;
import com.qunar.fresh2017.util.DBPoolUtils;
import com.qunar.fresh2017.util.MD5Utils;
import org.junit.Test;

public class UserDaoImplTest {

    @Test
    public void testQueryByUserName() throws Exception {
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.queryByUserName("cc", DBPoolUtils.getConnection());
        System.out.println(user);
        System.out.println(user.getPassword().equals(MD5Utils.encode("cc")));
    }

    @Test
    public void testInsertUser() throws Exception {
        UserDaoImpl userDao = new UserDaoImpl();
        User user = new User();
        user.setUsername("dd");
        user.setPassword(MD5Utils.encode("dd"));
        user.setNickname("第八方");
        System.out.println(userDao.insertUser(user, DBPoolUtils.getConnection()));
    }
}