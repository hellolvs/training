package com.qunar.fresh2017.handle;

import com.qunar.fresh2017.bean.ChatBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 处理聊天记录信息，包括按时间排序和统计聊天次数
 * Created by shuai.lv on 2017/2/20.
 */
public class HandleMsg {

    private static final Logger logger = LoggerFactory.getLogger(HandleMsg.class);
    private ArrayList<ChatBean> msg;

    public HandleMsg(ArrayList<ChatBean> msg) {
        this.msg = msg;
    }

    /*使用Collections类的sort方法排序*/
    public void sort(){
        Collections.sort(this.msg, new Comparator<ChatBean>() {

            public int compare(ChatBean o1, ChatBean o2) {

                /*抽取聊天记录日期，用SimpleDateFormat格式化，使用Date类比较日期大小进行排序*/
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date1 = null;
                Date date2 = null;
                try {
                    date1 = df.parse(o1.getDate());
                    date2 = df.parse(o2.getDate());
                } catch (ParseException e) {
                    logger.warn("日期解析异常，reason:{}", e.getMessage());
                }
                if ((date1 != null) && (date2 != null)) {
                    if (date1.compareTo(date2) != 0) {
                        return date1.compareTo(date2);
                    }
                }

                /*日期相等则按名字排序*/
                String name1 = o1.getName();
                String name2 = o2.getName();
                return name1.compareTo(name2);
            }
        });
    }

    /*由聊天记录统计每个人的聊天次数，保存到HashMap*/
    public HashMap<String, Integer> count() {
        HashMap<String, Integer> chatMap = new HashMap<String, Integer>();
        for (ChatBean chatBean : this.msg) {
            String name = chatBean.getName();
            if (chatMap.containsKey(name)) {
                chatMap.put(name, chatMap.get(name) + 1);
            } else {
                chatMap.put(name, 1);
            }
        }
        return chatMap;
    }
}
