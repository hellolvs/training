package com.qunar.fresh2017.question2;

/**
 * 索引文本抽象后的bean类
 * Created by shuai.lv on 2017/2/22.
 */
public class IndexedWord {
    private int index;
    private String word;

    public IndexedWord(int index, String word) {
        this.index = index;
        this.word = word;
    }

    public int getIndex() {
        return index;
    }

    public String getWord() {
        return word;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
