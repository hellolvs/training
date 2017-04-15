package com.qunar.fresh2017.servlet;

import com.qunar.fresh2017.dao.impl.UserDaoImpl;
import com.qunar.fresh2017.model.User;
import com.qunar.fresh2017.util.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * 处理注册的servlet
 * Created by shuai.lv on 2017/3/8.
 */
@WebServlet(name = "registerServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOGGER.info("进入RegisterServlet");

        String username = req.getParameter("username");
        String nickname = req.getParameter("nickname");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");

        PrintWriter out = resp.getWriter();

        UserDaoImpl userDao = new UserDaoImpl();
        User user = null;
        try {
            user = userDao.queryByUserName(username);
        } catch (SQLException e) {
            LOGGER.error("RegisterServlet：数据库查询操作失败，username：{}", username, e);
            out.println("<h1 align=\"center\">注册失败！数据库查询操作失败，详情请看日志</h1>");
            return;
        }

        //注册校验
        if (username != null && username.length() > 0 && password != null && password.length() > 0) {
            if (!password.equals(password2)) {
                LOGGER.info("RegisterServlet：注册失败！两次密码不一致！");
                out.println("<h1 align=\"center\"> 注册失败！两次密码不一致！</h1>");
                out.println("<br><h1 align=\"center\"><a href='register.html'>重新注册</a></h1>");
            } else if (user != null) {
                LOGGER.info("RegisterServlet：注册失败！该用户名已存在！");
                out.println("<h1 align=\"center\"> 注册失败！该用户名已存在！</h1>");
                out.println("<br><h1 align=\"center\"><a href='register.html'>重新注册</a></h1>");
            } else {
                //将获取的的参数存到user中，调用操作类中的方法保存
                try {
                    userDao.insertUser(new User(username, MD5Utils.encode(password), nickname));
                } catch (SQLException e) {
                    LOGGER.error("RegisterServlet：数据库插入操作失败，username：{}", username, e);
                    out.println("<h1 align=\"center\">注册失败！数据库插入操作失败，详情请看日志</h1>");
                    return;
                } catch (NoSuchAlgorithmException e) {
                    LOGGER.error("RegisterServlet：MD5加密失败：{}", e.getMessage(), e);
                }
                LOGGER.info("RegisterServlet：用户{}注册成功！跳转到登录页/login.html", username);
                out.println("<h1 align=\"center\">注册成功！1秒钟跳转到登录页...</h1>");
                resp.setHeader("refresh", "1;url=/login.html");//设置1秒钟跳转
            }
        } else {
            LOGGER.info("RegisterServlet：注册失败！用户名/密码不能为空！");
            out.println("<h1 align=\"center\"> 注册失败！用户名/密码不能为空！</h1>");
            out.println("<br><h1 align=\"center\"><a href='register.html'>重新注册</a></h1>");

        }
    }
}
