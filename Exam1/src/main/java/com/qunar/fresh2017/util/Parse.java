package com.qunar.fresh2017.util;

/**
 * 解析聊天记录类
 * Created by shuai.lv on 2017/2/20.
 */
public class Parse {
    public static String[] parse(String s){
        String[] strings = s.split("    ");
        return strings;
    }
}
