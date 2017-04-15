package com.qunar.fresh2017.exam2;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * linux命令，封装一个或多个由管道符分割的单一命令
 * Created by shuai.lv on 2017/3/6.
 */
public class Command {

    private List<SingleCommand> commandList = Lists.newArrayList();

    public void addCommand(SingleCommand command) {
        commandList.add(command);
    }

    /*利用动态绑定遍历执行封装的单一命令*/
    public List<String> execute() {
        List<String> result = Lists.newArrayList();
        for (SingleCommand aSingleCommand : commandList) {
            if (!aSingleCommand.isPiped()) { //如果是管道符之前的命令则根据文件路径参数执行
                result = aSingleCommand.execute();
            } else { //如果是管道符之后的命令则根据上一个命令的执行结果执行
                result = aSingleCommand.execute(result);
            }
        }
        return result;
    }
}
