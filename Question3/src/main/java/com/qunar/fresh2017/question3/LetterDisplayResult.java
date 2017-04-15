package com.qunar.fresh2017.question3;

/**
 * 保存字母的具体统计结果
 * Created by shuai.lv on 2017/2/23.
 */
public class LetterDisplayResult extends AbstractResult{
    @Override
    public void initial() {
        for (char c = 'A'; c <= 'Z'; c++) {
            resultMap.put(LETTER+c, 0);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            resultMap.put(LETTER+c, 0);
        }
    }

    public void addLetter(Character character){
        resultMap.put(LETTER+character,resultMap.get(LETTER+character) + 1);
    }
}
