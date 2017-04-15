package com.qunar.fresh2017.io;

import com.qunar.fresh2017.handler.LinesHandler;
import com.qunar.fresh2017.question1.Question1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 读取Java文件，使用Handler类统计有效行数
 * Created by shuai.lv on 2017/2/21.
 */
public class ReadJavaFile {

    private String filename; //要读取的文件名
    private static final Logger logger = LoggerFactory.getLogger(ReadJavaFile.class);

    public ReadJavaFile(String filename) {
        this.filename = filename;
    }

    public int read() {

        String path = Question1.class.getResource("/").getPath() + filename;
        File file = new File(path);
        if(!file.exists() || !file.isFile() || !file.getName().endsWith(".java")){
            logger.error("不是有效的Java文件!");
        }

        /*将文件按行读取，使用LinesHandler类提供的静态方法统计有效行*/
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                LinesHandler.countEffectiveLines(line);
            }
        } catch (IOException e) {
            logger.warn("文件读取异常，reason:{}",e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    logger.warn("文件流关闭异常，reason:{}",e.getMessage());
                }
            }
        }
        return LinesHandler.effectiveLines;
    }

}