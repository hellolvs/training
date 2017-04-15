package com.qunar.fresh2017.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理登出的servlet
 * Created by shuai.lv on 2017/3/8.
 */
@WebServlet(name = "logoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOGGER.info("进入LogoutServlet");

        //清除cookie
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }

        LOGGER.info("LogoutServlet：Cookies清除成功，跳转到登录页/login.html");

        resp.getWriter().println("<h1 align=\"center\">退出成功！1秒钟跳转到登录页...</h1>");
        resp.setHeader("refresh", "1;url=/login.html");//设置1秒钟跳转
    }
}
