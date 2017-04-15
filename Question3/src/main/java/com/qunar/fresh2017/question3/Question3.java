package com.qunar.fresh2017.question3;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.io.Closer;
import com.google.common.io.Files;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

/**
 * 入口类
 * Created by shuai.lv on 2017/2/23.
 */
public class Question3 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Question3.class);
    private static final String OUTPUT_FILE_NAME = "characterType.txt";

    public static void main(String[] args) throws IOException, ParseException {
        Preconditions.checkArgument(args.length > 0, "please init the folder location in args");
        try {
            List<File> fileList = searchFile(new File(args[0])); //返回指定目录文件列表
            List<Result> countList = countCharacterType(fileList); //统计字符数量
            outputCountInfo(OUTPUT_FILE_NAME, countList); //统计结果输出到文件
        } catch (IOException e) {
            LOGGER.error("count character type occur error", e);
        }
    }

    /*返回指定目录下的文件列表*/
    private static List<File> searchFile(File entranceFile) {
        List<File> fileLis = Lists.newArrayList();
        LinkedList<File> fileQueue = Lists.newLinkedList(Lists.newArrayList(entranceFile));
        while (!fileQueue.isEmpty()) {
            File file = fileQueue.poll();
            if (file.isDirectory()) {
                fileQueue.addAll(Lists.newArrayList(file.listFiles()));
            } else {
                fileLis.add(file);
            }
        }
        return fileLis;
    }

    /*统计各种字符数量，保存到List<Result>*/
    private static List<Result> countCharacterType(List<File> fileList) throws IOException {
        ResultLineProcessor resultLineProcessor = new ResultLineProcessor();
        for (File file : fileList) {
            Files.readLines(file, Charsets.UTF_8, resultLineProcessor);
        }
        return resultLineProcessor.getResult();
    }

    /*统计结果输出到文件*/
    private static void outputCountInfo(String countInfoFileName, List<Result> countList) throws IOException {
        Closer closer = Closer.create();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter((new File(Question3.class
                    .getResource("/").getFile() + countInfoFileName))));
            closer.register(bufferedWriter);
            for (Result result : countList) {
                List<String> list = result.getResult();//将统计结果按题目要求的指定字符串形式返回
                for (String s : list) {
                    bufferedWriter.write(s + IOUtils.LINE_SEPARATOR);
                }
                bufferedWriter.write(IOUtils.LINE_SEPARATOR);
            }
        } finally {
            closer.close();
        }
    }

}
