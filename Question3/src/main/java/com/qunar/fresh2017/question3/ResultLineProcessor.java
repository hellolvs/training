package com.qunar.fresh2017.question3;

import com.google.common.io.LineProcessor;
import org.apache.commons.lang3.CharUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 行处理器具体实现类，策略模式
 * Created by shuai.lv on 2017/2/23.
 */
public class ResultLineProcessor implements LineProcessor<List<Result>> {

    private OverallResult overallResult = new OverallResult();
    private LineResult lineResult = new LineResult();
    private NumberDisplayResult numberDisplayResult = new NumberDisplayResult();
    private LetterDisplayResult letterDisplayResult = new LetterDisplayResult();

    public ResultLineProcessor() {
        overallResult.initial();
        lineResult.initial();
        numberDisplayResult.initial();
        letterDisplayResult.initial();
    }

    @Override
    public boolean processLine(String line) throws IOException {

        lineResult.add();//行数自增
        for (char character : line.toCharArray()) {
            if(CharUtils.isAsciiNumeric(character)){//数字
                overallResult.addNumber();
                numberDisplayResult.addNumber(character);
            }else if(CharUtils.isAsciiAlpha(character)){//字母
                overallResult.addLetter();
                letterDisplayResult.addLetter(character);
            }else if(Character.isWhitespace(character)){//空格
                overallResult.addSpace();
            }else if(OverallResult.pattern.matcher(String.valueOf(character)).find()){//汉字
                overallResult.addChinese();
            }
        }
        return true;
    }

    /*返回结果，共四种Result*/
    @Override
    public List<Result> getResult() {
        List<Result> resultList = new ArrayList<Result>();
        resultList.add(overallResult);
        resultList.add(lineResult);
        resultList.add(numberDisplayResult);
        resultList.add(letterDisplayResult);
        return resultList;
    }
}
