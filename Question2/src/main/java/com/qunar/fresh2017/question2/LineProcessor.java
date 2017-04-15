package com.qunar.fresh2017.question2;

import java.io.IOException;

/**
 * 行处理类，IO类中调用，利用策略模式
 * Created by shuai.lv on 2017/2/22.
 */
public interface LineProcessor<T> {

    boolean processLine(String line) throws IOException;

    T getResult();
}
