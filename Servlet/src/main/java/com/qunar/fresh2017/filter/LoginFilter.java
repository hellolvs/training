package com.qunar.fresh2017.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据是否登录过滤
 * Created by shuai.lv on 2017/3/8.
 */
@WebFilter(filterName = "loginFilter", urlPatterns = {"*.jsp", "*.html"})
public class LoginFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        LOGGER.info("进入LoginFilter");

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Cookie[] cookies = req.getCookies();
        String requestURI = req.getRequestURI();// 得到用户请求的URI
        String ctxPath = req.getContextPath();// 得到web应用程序的上下文路径
        String uri = requestURI.substring(ctxPath.length());// 去除上下文路径，得到剩余部分的路径

        LOGGER.info("LoginFilter:获得当前URI：{}",uri);

        /*判断用户访问的是否是登录页面*/
        if (uri.equals("/login.html") || uri.equals("/register.html")) {
            LOGGER.info("LoginFilter:当前URI：{}，是登录页面，执行filterChain.doFilter",uri);
            filterChain.doFilter(req, resp);
        } else {
            // 如果访问的不是登录页面，则判断用户是否已经登录
            boolean isLogin = false;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("username")) {
                        isLogin = true;
                        break;
                    }
                }
            }
            if (isLogin) {
                LOGGER.info("LoginFilter:当前URI：{}，非登录页面，用户已登录，执行filterChain.doFilter",uri);
                filterChain.doFilter(req, resp);
            } else { //未登录则自动跳转到登录页
                LOGGER.info("LoginFilter:当前URI：{}，非登录页面，用户未登录，转发到/login.html",uri);
                req.getRequestDispatcher("/login.html").forward(req, resp);
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
