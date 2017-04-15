package com.qunar.fresh2017.exam3.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 首页控制
 * Created by shuai.lv on 2017/3/23.
 */
@Controller
@RequestMapping("/")
public class WelcomeController {

    private static final Logger LOG = LoggerFactory.getLogger(WelcomeController.class);

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String list() {
        LOG.info("首页默认跳转到对比列表页面");
        return "redirect:/diff/list/1";
    }

}
