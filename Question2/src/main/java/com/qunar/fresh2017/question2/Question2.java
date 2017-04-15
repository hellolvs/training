package com.qunar.fresh2017.question2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.*;

/**
 * 入口类
 * Created by shuai.lv on 2017/2/22.
 */
public class Question2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Question2.class);
    private static final String PROP_URL= "https://owncloud.corp.qunar.com/index.php/s/2pvS0d2Zs5onsF2/download";
    private static final String TEMPLATE_URL= "https://owncloud.corp.qunar.com/index.php/s/2mElvSWUJgppSBx/download";
    private static final String OUTPUT_FILE_NAME = "/sdxl.txt";
    private static final String STRING_SEPARATOR = "\t";

    public static void main(String[] args) throws IOException, ParseException {
        /*从网络读取文件*/
        List<IndexedWord> indexedWordList = loadIndexedWord(PROP_URL);
        List<String> sentenceList = loadSentence(TEMPLATE_URL);

        /*利用indexedWordList按规则替换sentenceList中内容*/
        ReplaceWords replaceWords = new ReplaceWords(indexedWordList);
        replaceWords.initial();
        List<String> resultList = replaceWords.replaceAll(sentenceList);

        /*将替换结果输出到指定文件*/
        writeSentence(OUTPUT_FILE_NAME, resultList);
    }

    /*载入文件，返回List<IndexedWord>*/
    private static List<IndexedWord> loadIndexedWord(String url) throws IOException, ParseException {
        return IOUtils.readLines(new URL(url), new LineProcessor<List<IndexedWord>>() {
            private List<IndexedWord> indexedWordList = new ArrayList<IndexedWord>();

            @Override
            public boolean processLine(String line) throws IOException {
                IndexedWord indexedWord = parseIndexedWord(line);
                if (indexedWord != null) {
                    indexedWordList.add(indexedWord);
                }
                return true;
            }

            @Override
            public List<IndexedWord> getResult() {
                return indexedWordList;
            }
        });
    }

    /*载入文件，返回List<String>*/
    private static List<String> loadSentence(String url) throws IOException, ParseException {
        return IOUtils.readLines(new URL(url), new LineProcessor<List<String>>() {
            private List<String> sentenceList = new ArrayList<String>();

            @Override
            public boolean processLine(String line) throws IOException {
                if (line != null) {
                    sentenceList.add(line);
                }
                return true;
            }

            @Override
            public List<String> getResult() {
                return sentenceList;
            }
        });
    }

    /*输出到文件*/
    private static void writeSentence(String fileName, List<String> sentenceList) throws IOException {
        IOUtils.writeLines(fileName, sentenceList);
    }

    /*将文本行解析为对象*/
    private static IndexedWord parseIndexedWord(String line) {
        String[] strings = line.split(STRING_SEPARATOR);
        if (strings.length == 2) {
            return new IndexedWord(Integer.parseInt(strings[0]), strings[1]);
        } else {
            LOGGER.error("sentence line format error, line={}", line);
        }
        return null;
    }

}
