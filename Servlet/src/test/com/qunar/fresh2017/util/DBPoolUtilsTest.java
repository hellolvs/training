package com.qunar.fresh2017.util;

import org.junit.Test;

public class DBPoolUtilsTest {

    @Test
    public void testGetConnection() throws Exception {
        System.out.println(DBPoolUtils.getConnection());
    }
}