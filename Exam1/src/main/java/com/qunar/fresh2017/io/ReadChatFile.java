package com.qunar.fresh2017.io;

import com.qunar.fresh2017.bean.ChatBean;
import com.qunar.fresh2017.exam1.Exam1;
import com.qunar.fresh2017.util.Parse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 聊天记录文件读取，读入到ArrayList
 * Created by shuai.lv on 2017/2/20.
 */
public class ReadChatFile {

    private String filename; //要读取的文件名
    private static final Logger logger = LoggerFactory.getLogger(ReadChatFile.class);

    public ReadChatFile(String filename) {
        this.filename = filename;
    }

    public ArrayList<ChatBean> read() {
        ArrayList<ChatBean> msg = new ArrayList<ChatBean>();

        String path = Exam1.class.getResource("/").getPath() + filename;
        File file = new File(path);


        /*将文件按行读取到ArrayList，读取完毕关闭文件流*/
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                ChatBean chatBean = new ChatBean(Parse.parse(line));
                msg.add(chatBean);
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
        return msg;
    }

}