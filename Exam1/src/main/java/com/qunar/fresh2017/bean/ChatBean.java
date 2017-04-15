package com.qunar.fresh2017.bean;

/**
 * 聊天记录对应的Bean类
 * Created by shuai.lv on 2017/2/20.
 */
public class ChatBean {

    private String name;
    private String date;
    private String content;

    public ChatBean() {
    }

    /*构造方法，每行按空字符串分割,抽取相应属性*/
    public ChatBean(String[] strings) {
        this.name = strings[0];
        this.date = strings[1];
        this.content = strings[2];
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return this.name + "    " + this.date + "    " + this.content;
    }
}
