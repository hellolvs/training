package com.qunar.fresh2017.question3;

/**
 * 保存数字的具体统计结果
 * Created by shuai.lv on 2017/2/23.
 */
public class NumberDisplayResult extends AbstractResult{
    @Override
    public void initial() {
        for (int i = 0; i < 10; i++){
            resultMap.put(NUMBER+i,0);
        }
    }

    public void addNumber(Character character) {
        resultMap.put(NUMBER+character,resultMap.get(NUMBER+character) + 1);
    }
}
