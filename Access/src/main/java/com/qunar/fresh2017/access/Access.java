package com.qunar.fresh2017.access;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.StandardSystemProperty;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Ordering;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 入口类
 * Created by shuai.lv on 2017/3/1.
 */
public class Access {
    private static final Logger LOGGER = LoggerFactory.getLogger(Access.class);
    private static final String CLASS_PATH = Access.class.getResource("/").getPath();
    private static final String INPUT_FILE_NAME = "access.log";
    private static final String OUTPUT_FILE_NAME = "requestInfo.txt";
    private static final String LINE_SEPARATOR = StandardSystemProperty.LINE_SEPARATOR.value();
    private static final String STRING_SEPARATOR = "\t";

    public static void main(String[] args) {
        File inputfile = new File(CLASS_PATH + INPUT_FILE_NAME);
        RequestInfo requestInfo = getRequestInfo(inputfile); //读取文件，返回请求信息汇总对象
        List<String> result = processRequestInfo(requestInfo); //处理请求信息，返回统计结果描述
        File outputfile = new File(CLASS_PATH + OUTPUT_FILE_NAME);
        outputResult(result, outputfile); //将统计结果输出到文件
    }

    /*读取文件，返回请求信息汇总对象*/
    public static RequestInfo getRequestInfo(File file) {
        Preconditions.checkArgument(file.exists() && file.isFile(), "文件不存在");
        RequsetLineProcessor requsetLineProcessor = new RequsetLineProcessor();
        try {
            Files.readLines(file, Charsets.UTF_8, requsetLineProcessor);
        } catch (IOException e) {
            LOGGER.warn("文件流读取异常：{}", e.getMessage());
        }
        LOGGER.trace("文件{}读取完成，请求信息放入RequstInfo对象", file.getName());
        return requsetLineProcessor.getResult();
    }

    /*处理请求信息，返回统计结果描述*/
    public static List<String> processRequestInfo(RequestInfo requestInfo) {
        int totalRequest = requestInfo.getTotalRequest();
        HashMap<URI, AtomicInteger> uriCountMap = requestInfo.getUriCountMap();
        HashMap<String, AtomicInteger> methodCountMap = requestInfo.getMethodCountMap();
        Multimap<String, String> uriCategoryMap = requestInfo.getUriCategoryMap();

        //使用Ordering类取出uriCountMap中value前10的元素
        List entryList = Lists.newArrayList(uriCountMap.entrySet());
        Ordering ordering = new Ordering<Map.Entry<URI, AtomicInteger>>() {
            @Override
            public int compare(Map.Entry<URI, AtomicInteger> o1, Map.Entry<URI, AtomicInteger> o2) {
                Integer var1 = o1.getValue().get();
                Integer var2 = o2.getValue().get();
                return var1.compareTo(var2);
            }
        };
        List<Map.Entry<URI, AtomicInteger>> resultList = ordering.greatestOf(entryList, 10);

        //返回统计结果描述信息
        List<String> result = Lists.newArrayList();
        StringBuilder sb = new StringBuilder();
        sb.append("total request times:").append(STRING_SEPARATOR).append(totalRequest);
        result.add(sb.toString());
        result.add(LINE_SEPARATOR + "top 10 request uris:");
        for (Map.Entry<URI, AtomicInteger> entry : resultList) {
            sb.delete(0, sb.length()).append(entry.getKey()).append(STRING_SEPARATOR).append(entry.getValue());
            result.add(sb.toString());
        }
        result.add(LINE_SEPARATOR + "get and post request times:");
        for (String key : methodCountMap.keySet()) {
            sb.delete(0, sb.length()).append(key).append(STRING_SEPARATOR).append(methodCountMap.get(key));
            result.add(sb.toString());
        }
        result.add(LINE_SEPARATOR + "uris in each category:");
        for (String key : uriCategoryMap.keySet()) {
            sb.delete(0, sb.length()).append(key).append(STRING_SEPARATOR).append(uriCategoryMap.get(key));
            result.add(sb.toString());
        }
        LOGGER.trace("完成对RequstInfo对象的处理，返回统计结果");
        return result;
    }

    /*将统计结果输出到文件*/
    public static void outputResult(List<String> result, File file) {
        Preconditions.checkNotNull(result);
        Preconditions.checkNotNull(file);
        if (file.exists()) {
            file.delete();
        }
        try {
            for (String s : result) {
                Files.append(s + LINE_SEPARATOR, file, Charsets.UTF_8);
            }
        } catch (IOException e) {
            LOGGER.warn("写入文件流异常:{}", e.getMessage());
        }
        LOGGER.trace("已将统计结果输出到文件{}", file.getName());
    }
}