package com.qunar.fresh2017.exam2;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.StandardSystemProperty;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * 文件读写工具类
 * Created by shuai.lv on 2017/3/6.
 */
public class FileUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
    public static final Charset FILE_ENCODING = Charsets.UTF_8;
    public static final String LINE_SEPARATOR = StandardSystemProperty.LINE_SEPARATOR.value();
    public static final String CLASS_PATH = FileUtil.class.getResource("/").getPath();

    /*读取文件*/
    public static List<String> readFile(String fileName) {
        Preconditions.checkNotNull(fileName);
        String path = CLASS_PATH + fileName;
        File file = new File(path);
        return readFile(file);
    }

    /*读取文件*/
    public static List<String> readFile(File file) {
        Preconditions.checkNotNull(file);
        if (!file.exists() || !file.isFile()) {
            LOGGER.error("文件不存在：" + file.getPath());
            throw new RuntimeException("文件不存在：" + file.getPath());
        }
        List<String> content;
        try {
            content = Files.readLines(file, FILE_ENCODING);
        } catch (IOException e) {
            LOGGER.warn("文件流读取异常：{}", e.getMessage());
            throw new RuntimeException(e);
        }
        return content;
    }

    /*写入文件*/
    public static void writeFile(String fileName, List<String> content) {
        Preconditions.checkNotNull(fileName, content);
        String contentStr = Joiner.on(LINE_SEPARATOR).join(content);
        writeFile(fileName, contentStr);
    }

    /*写入文件*/
    public static void writeFile(String fileName, String content) {
        Preconditions.checkNotNull(fileName, content);
        String path = CLASS_PATH + fileName;
        File file = new File(path);
        try {
            Files.write(content, file, FILE_ENCODING);
        } catch (IOException e) {
            LOGGER.warn("写入文件流异常:{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
