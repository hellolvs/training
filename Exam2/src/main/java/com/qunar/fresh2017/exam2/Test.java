package com.qunar.fresh2017.exam2;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 入口类
 * Created by shuai.lv on 2017/3/6.
 */
public class Test {
    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {

        Map<CommandType, Class> commandMap = initCommandMap(); //初始化命令类型表，用于解析生成命令

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {  //循环接受用户输入
            String line = scanner.nextLine();
            try {
                Command command = Parser.parse(line, commandMap); //解析用户输入生成命令
                List<String> result = command.execute(); //执行命令，返回结果
                print(result); //使用日志将结果打印到控制台
            } catch (Exception e) {
                //异常已在调用的方法中输出到日志并上抛至此，此处catch是为了防止程序终止，以便循环读入命令
            }
        }
    }

    /*初始化命令类型表，用于解析生成命令*/
    public static Map<CommandType, Class> initCommandMap() {
        Map<CommandType, Class> commandMap = Maps.newHashMap();
        commandMap.put(CommandType.CAT_COMMAND, CatCommand.class);
        commandMap.put(CommandType.GREP_COMMAND, GrepCommand.class);
        commandMap.put(CommandType.WC_COMMAND, WcCommand.class);
        return commandMap;
    }

    /*使用日志将结果打印到控制台*/
    public static void print(List<String> list) {
        if (list.isEmpty()) {
            LOGGER.info("");
        } else {
            for (String s : list) {
                LOGGER.info(s);
            }
        }
    }
}
