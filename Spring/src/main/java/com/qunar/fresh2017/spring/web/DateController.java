package com.qunar.fresh2017.spring.web;

import com.qunar.fresh2017.spring.support.DateFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * <code>DateController</code>
 * 解析输入的日期格式的String为Date对象
 * Created by shuai.lv on 2017/3/15.
 */
@Controller
@RequestMapping("/binding_date.do")
public class DateController {

    private static final Logger LOG = LoggerFactory.getLogger(DateController.class);

    @Autowired
    private DateFormatter dateFormatter;

    public void setDateFormatter(DateFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.TEXT_HTML_VALUE})
    @ResponseBody
    public String parse(@RequestParam("date") String date) {
        try {
            Date date1 = dateFormatter.parse(date, Locale.ENGLISH);
            LOG.info("日期解析完成：{}",date1.toString());
            return date1.toString();
        } catch (ParseException e) {
            LOG.error("日期解析错误：{}", e.getMessage());
            return e.getMessage();
        }
    }
}
