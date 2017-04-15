package com.qunar.fresh2017.util;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * dbcp数据库连接池工具类
 * Created by shuai.lv on 2017/3/12.
 */
public class DBPoolUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(DBPoolUtils.class);

    private static Properties properties = new Properties();

    private static DataSource dataSource;

    static {
        try {
            properties.load(DBPoolUtils.class.getResourceAsStream("/jdbcpool.properties"));
        } catch (IOException e) {
            LOGGER.error("加载jdbcpool.properties失败",e);
        }
        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            LOGGER.error("初始化数据库连接池失败",e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
