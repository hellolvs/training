package com.qunar.fresh2017.spring.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <code>WelcomeController</code>
 * 设置首页默认跳转到用户列表页面
 * Created by shuai.lv on 2017/3/15.
 */
@Controller
@RequestMapping("/")
public class WelcomeController {

    private static final Logger LOG = LoggerFactory.getLogger(WelcomeController.class);

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String list() {
        LOG.info("首页默认跳转到用户列表页面");
        return "redirect:/user/list/1";
    }
}
