package com.qunar.fresh2017.question2;

/**
 * 函数接口类，利用了策略模式
 * Created by shuai.lv on 2017/2/22.
 */
public interface Function<F, T> {
    T apply(F input);
}
