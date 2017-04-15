package com.qunar.fresh2017.exam2;

import java.util.List;

/**
 * 单一命令接口
 * Created by shuai.lv on 2017/3/6.
 */
public interface SingleCommand {

    /*是否在管道符之后*/
    public boolean isPiped();

    public void setPiped(boolean piped);

    /*执行命令*/
    public List<String> execute();

    public List<String> execute(List<String> list);

    /*由输入的字符串信息构造命令参数*/
    public void parseBy(List<String> list) throws IllegalArgumentException;

}
