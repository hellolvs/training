package com.qunar.fresh2017.exam3.support;

import com.google.common.collect.ImmutableMap;
import com.qunar.fresh2017.exam3.util.MD5Util;
import com.qunar.fresh2017.exam3.util.PropertyLoadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 判断用户是否登录的拦截器，获取登录状态和用户名
 * Created by shuai.lv on 2017/3/23.
 */
public class LoginCheckHandlerInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOG = LoggerFactory
            .getLogger(LoginCheckHandlerInterceptor.class);

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOG.debug("进入拦截器，判断用户是否登录...");
        boolean isLogin = false;
        String username = "";
        String password = "";
        ImmutableMap<String, String> userMap = PropertyLoadUtil.getUserMap();//从属性文件获取用户信息

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }
                if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
        }
        if (userMap.containsKey(username) && MD5Util.encode(userMap.get(username)).equals(password)) {
            isLogin = true;
        }
        LOG.debug("用户登录状态：{}，用户名：{}", isLogin, username);

        //将用户登录状态传递给Controller
        modelAndView.getModel().put("isLogin", isLogin);
        modelAndView.getModel().put("username", username);

        LOG.debug("退出拦截器...");
    }
}
