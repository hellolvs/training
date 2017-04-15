package com.qunar.fresh2017.exam2;

import java.util.List;

/**
 * cat命令
 * Created by shuai.lv on 2017/3/6.
 */
public class CatCommand implements SingleCommand {

    private String fileName;
    private boolean piped;

    @Override
    public boolean isPiped() {
        return piped;
    }

    @Override
    public void setPiped(boolean piped) {
        this.piped = piped;
    }

    @Override
    public List<String> execute() { //cat命令即为读取文件内容并返回
        List<String> result = FileUtil.readFile(fileName);
        return execute(result);
    }

    @Override
    public List<String> execute(List<String> list) {
        return list;
    }

    @Override
    public void parseBy(List<String> list) throws IllegalArgumentException {
        if (piped) {
            if (list.size() != 1) {
                throw new IllegalArgumentException("cat命令参数个数错误：" + list.toString() + " usage: cat [filename]");
            }
        } else {
            if (list.size() != 2) {
                throw new IllegalArgumentException("cat命令参数个数错误：" + list.toString() + " usage: cat [filename]");
            } else {
                fileName = list.get(1);
            }
        }
    }
}
