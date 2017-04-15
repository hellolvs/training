package com.qunar.fresh2017.dao;

import com.qunar.fresh2017.model.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by shuai.lv on 2017/3/10.
 */
public interface UserDao {
    User queryByUserName(String username) throws SQLException;

    User queryByUserName(String username, Connection connection) throws SQLException;

    int insertUser(User user) throws SQLException;

    int insertUser(User user, Connection connection) throws SQLException;
}
