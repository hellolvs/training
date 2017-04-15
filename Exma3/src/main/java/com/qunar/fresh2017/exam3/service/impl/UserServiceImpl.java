package com.qunar.fresh2017.exam3.service.impl;

import com.google.common.collect.ImmutableMap;
import com.qunar.fresh2017.exam3.service.UserService;
import com.qunar.fresh2017.exam3.util.MD5Util;
import com.qunar.fresh2017.exam3.util.PropertyLoadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by shuai.lv on 2017/3/23.
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final int COOKIE_MAX_AGE = 24 * 60 * 60 * 7;

    @Override
    public void login(String username, String password, HttpServletResponse response) {
        ImmutableMap<String, String> userMap = PropertyLoadUtil.getUserMap();//从属性文件获取用户信息

        if (username == null || username.length() <= 0 || password == null || password.length() <= 0) {
            LOG.debug("please input username and password");
            throw new RuntimeException("please input username and password");
        }
        if (!userMap.containsKey(username)) {
            LOG.debug("username is not exist");
            throw new RuntimeException("username is not exist");
        }
        if (!userMap.get(username).equals(password)) {
            LOG.debug("password error");
            throw new RuntimeException("password error");
        }

        Cookie cookie1 = new Cookie("username", username); //登录成功则添加登录信息到cookie中
        Cookie cookie2 = new Cookie("password", MD5Util.encode(password)); //cookie中对密码加密处理
        cookie1.setMaxAge(COOKIE_MAX_AGE);
        cookie2.setMaxAge(COOKIE_MAX_AGE);
        cookie1.setPath("/");
        cookie2.setPath("/");
        response.addCookie(cookie1);
        response.addCookie(cookie2);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        //清除cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
    }
}
