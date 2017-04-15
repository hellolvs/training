package com.qunar.fresh2017.util;

import org.junit.Test;

import java.sql.Connection;

public class DBUtilsTest {

    @Test
    public void testGetConnection() throws Exception {
        Connection connection = DBUtils.getConnection();
        System.out.println(connection);
    }
}