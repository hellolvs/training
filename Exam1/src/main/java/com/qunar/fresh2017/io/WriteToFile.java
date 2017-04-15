package com.qunar.fresh2017.io;

import com.qunar.fresh2017.bean.ChatBean;
import com.qunar.fresh2017.exam1.Exam1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * 将处理结果输出到相应文件
 * Created by shuai.lv on 2017/2/20.
 */
public class WriteToFile {

    private static final Logger logger = LoggerFactory.getLogger(WriteToFile.class);

    /*输出按时间排序后的聊天记录，关闭输出流*/
    public void writeMsg(ArrayList<ChatBean> msg,String filename){
        BufferedWriter bw = null;
        try {
            String path = Exam1.class.getResource("/").getPath() + filename;
            File outputfile = new File(path);
            if(!outputfile.exists()){
                outputfile.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(outputfile));
            String text = "";
            for (ChatBean chatBean : msg) {
                text += chatBean.toString() + "\n";
            }
            bw.write(text);

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

    /*输出每人对应的聊天次数到相应文件，关闭输出流*/
    public void writeChatMap(HashMap<String,Integer> chatMap,String filename){
        BufferedWriter bw = null;
        try {
            String path = Exam1.class.getResource("/").getPath() + filename;
            File outputfile = new File(path);
            if(!outputfile.exists()){
                outputfile.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(outputfile));
            String text = "";
            for (String key : chatMap.keySet()) {
                text += key + "    " + chatMap.get(key).toString() + "\n";
            }
            bw.write(text);

        } catch (IOException e) {
            logger.warn("文件输出异常，reason:{}", e.getMessage());
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
