package com.qunar.fresh2017.question3;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 保存结果的抽象类，模板模式
 * Created by shuai.lv on 2017/2/23.
 */
public class AbstractResult implements Result {

    /*LinkedHashMap存储结果，便于统计和按顺序输出*/
    public LinkedHashMap<String,Integer> resultMap = new LinkedHashMap<String, Integer>();

    @Override
    public void initial() {

    }

    @Override
    public List<String> getResult() {
        List<String> list = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            list.add(entry.getKey() + STRING_SEPARATOR + entry.getValue() + "个");
        }
        return list;
    }

    @Override
    public void add() {
    }

}
