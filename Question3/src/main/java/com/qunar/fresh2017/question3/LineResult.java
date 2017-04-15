package com.qunar.fresh2017.question3;

import java.util.ArrayList;
import java.util.List;

/**
 * 保存行数统计结果
 * Created by shuai.lv on 2017/2/23.
 */
public class LineResult extends AbstractResult {
    @Override
    public void initial() {
        resultMap.put(LINECOUNT,0);
    }

    @Override
    public List<String> getResult() {
        List<String> list = new ArrayList<String>();
        list.add(LINECOUNT + STRING_SEPARATOR + resultMap.get(LINECOUNT) + "行");
        return list;
    }

    @Override
    public void add() {
        resultMap.put(LINECOUNT,resultMap.get(LINECOUNT) + 1);
    }
}
