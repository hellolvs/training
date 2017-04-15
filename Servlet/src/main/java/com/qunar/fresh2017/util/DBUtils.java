package com.qunar.fresh2017.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库连接工具类
 * Created by shuai.lv on 2017/3/10.
 */
public class DBUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(DBUtils.class);

    private static String DRIVER_NAME = null;
    private static String URL = null;
    private static String USER_NAME = null;
    private static String PWD = null;

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(DBUtils.class.getResourceAsStream("/jdbc.properties"));
        } catch (IOException e) {
            LOGGER.error("加载配置文件jdbc.properties异常",e);
        }
        DRIVER_NAME = properties.getProperty("jdbc.driver");
        URL = properties.getProperty("jdbc.url");
        USER_NAME = properties.getProperty("jdbc.username");
        PWD = properties.getProperty("jdbc.password");

        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            LOGGER.error("加载数据库驱动异常", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER_NAME,PWD);
    }

    public static void close(Connection connection,Statement statement,ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            if (!resultSet.isClosed()) {
                resultSet.close();
            }
        }
        if (statement != null) {
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        if (connection != null) {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }
}
