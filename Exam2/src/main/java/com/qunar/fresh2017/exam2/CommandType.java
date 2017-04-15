package com.qunar.fresh2017.exam2;

/**
 * 命令枚举类
 * Created by shuai.lv on 2017/3/6.
 */
public enum CommandType {
    CAT_COMMAND("cat"), GREP_COMMAND("grep"), WC_COMMAND("wc");

    CommandType(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static CommandType nameOf(String name) {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.getName().equals(name)) {
                return commandType;
            }
        }
        return null;
    }
}
