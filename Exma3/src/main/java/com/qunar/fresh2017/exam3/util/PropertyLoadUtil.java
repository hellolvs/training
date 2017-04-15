package com.qunar.fresh2017.exam3.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * 属性文件加载工具类，用于获取用户信息
 * Created by shuai.lv on 2017/3/23.
 */
public class PropertyLoadUtil {

    private static final Logger LOG = LoggerFactory.getLogger(PropertyLoadUtil.class);
    private static final Properties PROPERTIES = new Properties();
    private static final ImmutableMap<String,String> USER_MAP;

    static{
        try {
            PROPERTIES.load(PropertyLoadUtil.class.getResourceAsStream("/config/account.properties"));
        } catch (IOException e) {
            LOG.error("account.properties加载失败：{}",e.getMessage());
        }
        USER_MAP = Maps.fromProperties(PROPERTIES);
    }

    public static ImmutableMap<String,String> getUserMap(){
        return USER_MAP;
    }
}