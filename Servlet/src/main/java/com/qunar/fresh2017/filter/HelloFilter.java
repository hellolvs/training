package com.qunar.fresh2017.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * 为hello.jsp添加输出用户昵称
 * Created by shuai.lv on 2017/3/8.
 */
@WebFilter(filterName = "helloFilter", urlPatterns = {"/hello.jsp"})
public class HelloFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        LOGGER.info("进入HelloFilter");

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Cookie[] cookies = req.getCookies();
        String username = "";
        String nickname = "";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }
                if (cookie.getName().equals("nickname")) {
                    nickname = URLDecoder.decode(cookie.getValue(), "utf-8");
                }
            }
        }
        LOGGER.info("HelloFilter：根据Cookie取得用户名：{}    昵称：{}", username, nickname);

        //resp.addDateHeader("Last-Modified",System.currentTimeMillis()); //设置html页面不缓存，防止数据延迟

        /*利用URL添加时间戳穿透缓存
        String requestURL = req.getRequestURL().toString();
        String newURL = requestURL + "?t=" + new Date().getTime();
        LOGGER.info("HelloFilter：添加时间戳完成");
        resp.sendRedirect(newURL);*/

        resp.getWriter().println("<h1 align=\"center\">Hello " + nickname + "</h1>");

        LOGGER.info("HelloFilter：执行filterChain.doFilter");
        filterChain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
