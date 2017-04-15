package com.qunar.fresh2017.spring.support;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by shuai.lv on 2017/3/19.
 */
@Service("dateService")
public class DateFormatter implements Formatter<Date> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String print(Date date, Locale locale) {
        return dateFormat.format(date);
    }

    @Override
    public Date parse(String s, Locale locale) throws ParseException {
        return dateFormat.parse(s);
    }
}
