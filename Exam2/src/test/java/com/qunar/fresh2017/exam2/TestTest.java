package com.qunar.fresh2017.exam2;

import junit.framework.TestCase;

import java.util.List;
import java.util.Map;

public class TestTest extends TestCase {

    @org.junit.Test
    public void testMain() throws Exception {
        Test test = new Test();
        Map<CommandType, Class> commandMap = test.initCommandMap();

        String line = "cat  mysql.txt |  grep  select";
        System.out.println("运行命令：   " + line);
        test.print(test(line, commandMap));

        String line1 = "cat  mysql.txt |  grep  select |wc -l";
        System.out.println("运行命令：   " + line1);
        test.print(test(line1, commandMap));

        try {
            String line2 = "cat  mysql |  grep  select |wc -l";
            System.out.println("运行命令：   " + line2);
            test.print(test(line2, commandMap));
        } catch (Exception e) {
        }

        try {
            String line3 = "less  mysql.txt";
            System.out.println("运行命令：   " + line3);
            test.print(test(line3, commandMap));
        } catch (Exception e) {
        }
    }

    public static List<String> test(String line, Map<CommandType, Class> commandMap) {
        Command command = Parser.parse(line, commandMap);
        return command.execute();
    }
}