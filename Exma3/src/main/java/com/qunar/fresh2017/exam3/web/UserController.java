package com.qunar.fresh2017.exam3.web;

import com.qunar.fresh2017.exam3.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录登出控制
 * Created by shuai.lv on 2017/3/23.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /*登录*/
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response) {
        userService.login(username, password, response);
        LOG.info("用户:{}登录成功，登录信息添加到Cookie，跳转到对比列表页/diff/list/1", username);
        return "redirect:/diff/list/1";
    }

    /*展示登录页面*/
    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String loginPage() {
        LOG.info("展示登录页面");
        return "login";
    }

    /*登出*/
    @RequestMapping(value = "/logout", method = {RequestMethod.GET})
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        userService.logout(request, response);
        LOG.debug("Cookies清除成功，跳转到对比列表页/diff/list/1");
        return "redirect:/diff/list/1";
    }

    /*异常处理，输出异常信息*/
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<String> handlerRuntimeException(RuntimeException e) {
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        LOG.error(e.getMessage(), e);
        return responseEntity;
    }
}
