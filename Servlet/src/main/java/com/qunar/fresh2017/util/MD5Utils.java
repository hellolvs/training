package com.qunar.fresh2017.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * Created by shuai.lv on 2017/3/10.
 */
public class MD5Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MD5Utils.class);

    public static String encode(String s) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return md5 != null ? base64Encoder.encode(md5.digest(s.getBytes())) : null;
    }
}
