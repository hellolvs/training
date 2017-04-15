package com.qunar.fresh2017.servlet;

import com.qunar.fresh2017.dao.impl.UserDaoImpl;
import com.qunar.fresh2017.model.User;
import com.qunar.fresh2017.util.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * 处理登录的servlet
 * Created by shuai.lv on 2017/3/8.
 */
@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOGGER.info("进入LoginServlet");

        String username = req.getParameter("username");
        String password = null;
        try {
            password = MD5Utils.encode(req.getParameter("password"));
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("LoginServlet：MD5加密失败：{}", e.getMessage(), e);
        }

        PrintWriter out = resp.getWriter();

        //登录校验
        if (username != null && username.length() > 0 && password != null && password.length() > 0) {

            UserDaoImpl userDao = new UserDaoImpl();
            User user = null;
            try {
                user = userDao.queryByUserName(username);
            } catch (SQLException e) {
                LOGGER.error("LoginServlet：数据库查询操作失败，username：{}", username, e);
                out.println("<h1 align=\"center\">登录失败！数据库查询操作失败，详情请看日志</h1>");
                return;
            }

            if (user != null) {
                if (user.getPassword().equals(password)) {
                    Cookie cookie1 = new Cookie("username", username); //登录成功则添加登录信息到cookie中
                    Cookie cookie2 = new Cookie("password", password);
                    Cookie cookie3 = new Cookie("nickname", URLEncoder.encode(user.getNickname(), "UTF-8")); //cookie存中文需要编码
                    cookie1.setMaxAge(24 * 60 * 60 * 7);
                    cookie2.setMaxAge(24 * 60 * 60 * 7);
                    cookie3.setMaxAge(24 * 60 * 60 * 7);
                    resp.addCookie(cookie1);
                    resp.addCookie(cookie2);
                    resp.addCookie(cookie3);
                    LOGGER.info("LoginServlet:用户{}登录成功，登录信息添加到Cookie，重定向到/hello.jsp", username);
                    resp.sendRedirect("/hello.jsp");
                    //req.getRequestDispatcher("/hello.jsp").forward(req, resp);
                } else {
                    LOGGER.info("LoginServlet:用户{}登录失败！密码错误！", username);
                    out.println("<h1 align=\"center\">登录失败！密码错误！</h1>");
                    out.println("<br><h1 align=\"center\"><a href='login.html'>重新登录</a></h1>");
                }
            } else {
                LOGGER.info("LoginServlet:登录失败！用户不存在！");
                out.println("<h1 align=\"center\">登录失败！用户不存在！</h1>");
                out.println("<br><h1 align=\"center\"><a href='login.html'>重新登录</a></h1>");
            }

        } else {
            LOGGER.info("LoginServlet:登录失败！请输入用户名/密码！");
            out.println("<h1 align=\"center\">登录失败！请输入用户名/密码！</h1>");
            out.println("<br><h1 align=\"center\"><a href='login.html'>重新登录</a></h1>");
        }
    }
}
