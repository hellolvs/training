package com.qunar.fresh2017.question3;

import java.util.regex.Pattern;

/**
 * 保存总体的结果，数字、字母、汉字、空格数量
 * Created by shuai.lv on 2017/2/23.
 */
public class OverallResult extends AbstractResult {

    public static Pattern pattern = Pattern.compile("[\u4E00-\u9FA5]");

    @Override
    public void initial() {
        resultMap.put(NUMBER,0);
        resultMap.put(LETTER,0);
        resultMap.put(CHINESE,0);
        resultMap.put(SPACE,0);
    }

    public void addNumber() {
        resultMap.put(NUMBER,resultMap.get(NUMBER) + 1);
    }

    public void addLetter() {
        resultMap.put(LETTER,resultMap.get(LETTER) + 1);
    }

    public void addChinese() {
        resultMap.put(CHINESE,resultMap.get(CHINESE) + 1);
    }

    public void addSpace() {
        resultMap.put(SPACE,resultMap.get(SPACE) + 1);
    }
}
