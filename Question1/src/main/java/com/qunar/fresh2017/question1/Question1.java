package com.qunar.fresh2017.question1;

import com.qunar.fresh2017.io.ReadJavaFile;
import com.qunar.fresh2017.io.WriteToFile;

/**
 * 入口类
 * Created by shuai.lv on 2017/2/21.
 */
public class Question1 {
    public static void main(String[] args) {

        /*读取并统计java文件有效行数*/
        int effectiveLines = new ReadJavaFile("StringUtils.java").read();

        /*输出有效行数*/
        new WriteToFile().write(effectiveLines, "validLineCount.txt");
    }
}
