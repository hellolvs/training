package com.qunar.fresh2017.io;

import com.qunar.fresh2017.question1.Question1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 将处理结果输出到相应文件
 * Created by shuai.lv on 2017/2/21.
 */
public class WriteToFile {

    private static final Logger logger = LoggerFactory.getLogger(WriteToFile.class);

    /*输出有效代码行数，关闭输出流*/
    public static void write(Integer effectiveLines,String filename){
        BufferedWriter bw = null;
        try {
            String path = Question1.class.getResource("/").getPath() + filename;
            File outputfile = new File(path);
            if(!outputfile.exists()){
                outputfile.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(outputfile));
            bw.write(effectiveLines.toString());
        } catch (IOException e) {
            logger.warn("文件输出异常，reason:{}",e.getMessage());
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    logger.warn("文件流关闭异常，reason:{}",e.getMessage());
                }
            }
        }
    }
}
