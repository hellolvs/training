package com.qunar.fresh2017.handler;

/**
 * 统计有效行的实现类
 * Created by shuai.lv on 2017/2/21.
 */
public class LinesHandler {

    public static int effectiveLines = 0;//有效行数
    private static boolean flag = false;//多行注释状态标志位

    public static void countEffectiveLines(String line){
        if(isEffectiveLines(line)){
            effectiveLines++;
        }
    }

    public static boolean isEffectiveLines(String line){
        line = line.trim();//去除首尾空格符
        if(line.isEmpty()){
            return false;
        }//空行
        if(line.startsWith("/*") && line.endsWith("*/")){
            return false;
        }//形如/*...*/的单行注释
        if(line.startsWith("/*")){
            flag = true;
            return false;
        }//如果只以/*开头，不以*/结尾，则置多行注释状态标志位为true，进入多行注释状态
        if(flag && line.endsWith("*/")){
            flag = false;
            return false;
        }//在多行注释状态下，以*/结尾，则置多行注释状态标志位为false，退出多行注释状态
        if(line.startsWith("//")){
            return false;
        }//形如//...的单行注释
        return !flag; //是否处于多行注释代码段的首尾行之间
    }
}
