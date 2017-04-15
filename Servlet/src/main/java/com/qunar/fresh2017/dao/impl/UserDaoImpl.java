package com.qunar.fresh2017.dao.impl;

import com.qunar.fresh2017.dao.UserDao;
import com.qunar.fresh2017.model.User;
import com.qunar.fresh2017.util.DBPoolUtils;
import com.qunar.fresh2017.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shuai.lv on 2017/3/10.
 */
public class UserDaoImpl implements UserDao {

    @Override
    public User queryByUserName(String username) throws SQLException {
        return queryByUserName(username, DBPoolUtils.getConnection());
    }

    @Override
    public User queryByUserName(String username, Connection connection) throws SQLException {

        String sql = "SELECT user_name,password,nickname FROM user WHERE user_name=?";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            User user = null;
            while (resultSet.next()) {
                user = new User();
                user.setUsername(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("password"));
                user.setNickname(resultSet.getString("nickname"));
            }
            return user;
        } finally {
            DBUtils.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int insertUser(User user) throws SQLException {
        return insertUser(user, DBPoolUtils.getConnection());
    }

    @Override
    public int insertUser(User user, Connection connection) throws SQLException {

        String sql = "insert into user (user_name,password,nickname) values(?,?,?)";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getNickname());
            return preparedStatement.executeUpdate();
        } finally {
            DBUtils.close(connection, preparedStatement, null);
        }
    }
}
