package com.qunar.fresh2017.exam2;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 解析器，解析用户输入生成命令
 * Created by shuai.lv on 2017/3/6.
 */
public class Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);
    private static final Splitter WHITESPACE_SPLITTER = Splitter.on(CharMatcher.whitespace());
    private static final Splitter PIPE_SPLITTER = Splitter.on("|");

    public static Command parse(String line, Map<CommandType, Class> commandMap) {
        Command command = new Command();
        List<String> multiCommandList = PIPE_SPLITTER.omitEmptyStrings().splitToList(line);
        for (String s : multiCommandList) {
            boolean piped = true;
            if (s.equals(multiCommandList.get(0))) { //第一条命令为非管道命令
                piped = false;
            }
            List<String> singleCommandList = WHITESPACE_SPLITTER.omitEmptyStrings().splitToList(s);
            SingleCommand aSingleCommand = null;//根据类型获得具体命令
            try {
                Class aClass = commandMap.get(CommandType.nameOf(singleCommandList.get(0)));
                if (aClass == null) {
                    LOGGER.error("无法识别的命令：{}", singleCommandList.get(0));
                    throw new RuntimeException("无法识别的命令：" + singleCommandList.get(0));
                }
                aSingleCommand = (SingleCommand) aClass.newInstance();//利用反射取得对应实例
            } catch (InstantiationException e) {
                LOGGER.error("反射实例化失败：{}", e.getMessage());
            } catch (IllegalAccessException e) {
                LOGGER.error("非法访问：{}", e.getMessage());
            }

            aSingleCommand.setPiped(piped);
            try {
                aSingleCommand.parseBy(singleCommandList);
            } catch (IllegalArgumentException e) {
                LOGGER.error("命令解析错误：{}", e.getMessage());
                throw e;
            }
            command.addCommand(aSingleCommand);
        }
        return command;
    }
}
