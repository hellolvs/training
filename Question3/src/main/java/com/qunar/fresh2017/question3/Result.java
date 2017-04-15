package com.qunar.fresh2017.question3;

import java.util.List;

/**
 * 保存统计结果的接口，模板模式
 * Created by shuai.lv on 2017/2/23.
 */
public interface Result {

    public static final String NUMBER = "数字";
    public static final String LETTER = "字母";
    public static final String CHINESE = "汉字";
    public static final String SPACE = "空格";
    public static final String LINECOUNT = "行数";
    public static final String STRING_SEPARATOR = ":";

    public void initial();

    public void add();

    public List<String> getResult();

}
