package com.qunar.fresh2017.exam1;

import com.qunar.fresh2017.bean.ChatBean;
import com.qunar.fresh2017.handle.HandleMsg;
import com.qunar.fresh2017.io.ReadChatFile;
import com.qunar.fresh2017.io.WriteToFile;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 入口类
 * Created by shuai.lv on 2017/2/20.
 */
public class Exam1 {
    public static void main(String[] args) {
        /*将聊天文件按行读入到ArrayList*/
        ReadChatFile readChatFile = new ReadChatFile("unorderedmsg.txt");
        ArrayList<ChatBean> msg = readChatFile.read();

        /*处理聊天记录信息*/
        HandleMsg handleMsg = new HandleMsg(msg);
        handleMsg.sort();//对聊天记录按时间排序
        HashMap<String, Integer> chatMap = handleMsg.count();//统计每人的聊天次数到HashMap

        /*输出排序后的聊天记录和每人对应的聊天次数*/
        WriteToFile writeToFile = new WriteToFile();
        writeToFile.writeMsg(msg,"orderedmsg.txt");
        writeToFile.writeChatMap(chatMap,"count.txt");
    }
}