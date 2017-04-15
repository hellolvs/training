package com.qunar.fresh2017.exam2;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * grep命令
 * Created by shuai.lv on 2017/3/6.
 */
public class GrepCommand implements SingleCommand {

    private String keyword;
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
        for (String s : list) { //将包含keyword的行添加到结果集
            if (s.contains(keyword)) {
                result.add(s);
            }
        }
        return result;
    }

    @Override
    public void parseBy(List<String> list) throws IllegalArgumentException {
        if (piped) {
            if (list.size() != 2) {
                throw new IllegalArgumentException("grep命令参数个数错误：" + list.toString() + " usage: grep keyword [filename]");
            } else {
                keyword = list.get(1);
            }
        } else {
            if (list.size() != 3) {
                throw new IllegalArgumentException("grep命令参数个数错误：" + list.toString() + " usage: grep keyword [filename]");
            } else {
                keyword = list.get(1);
                fileName = list.get(2);
            }
        }
    }
}
