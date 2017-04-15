package com.qunar.fresh2017.exam2;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * wc命令，只考虑带-l参数
 * Created by shuai.lv on 2017/3/6.
 */
public class WcCommand implements SingleCommand {

    private String option;
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
    public List<String> execute() {
        List<String> result = FileUtil.readFile(fileName);
        return execute(result);
    }

    @Override
    public List<String> execute(List<String> list) {
        List<String> result = Lists.newArrayList();
        result.add(String.valueOf(list.size())); //返回行数
        return result;
    }

    @Override
    public void parseBy(List<String> list) throws IllegalArgumentException {
        if (piped) {
            if (list.size() != 2) {
                throw new IllegalArgumentException("wc命令参数个数错误：" + list.toString() + " usage: wc -l [filename]");
            } else if (!list.get(1).equals("-l")) {
                throw new IllegalArgumentException("wc命令option参数错误：" + list.toString() + " usage: wc -l [filename]");
            } else {
                option = list.get(1);
            }
        } else {
            if (list.size() != 3) {
                throw new IllegalArgumentException("wc命令参数个数错误：" + list.toString() + " usage: wc -l [filename]");
            } else if (!list.get(1).equals("-l")) {
                throw new IllegalArgumentException("wc命令option参数错误：" + list.toString() + " usage: wc -l [filename]");
            } else {
                option = list.get(1);
                fileName = list.get(2);
            }
        }
    }
}
