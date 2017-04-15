package com.qunar.fresh2017.exam3.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * Created by shuai.lv on 2017/3/23.
 */
public class MD5Util {

    private static final Logger LOG = LoggerFactory.getLogger(MD5Util.class);

    public static String encode(String s) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            LOG.error("MD5加密算法失败：{}", e.getMessage());
        }
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return md5 != null ? base64Encoder.encode(md5.digest(s.getBytes())) : null;
    }
}
