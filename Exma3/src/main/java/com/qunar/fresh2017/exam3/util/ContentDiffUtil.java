package com.qunar.fresh2017.exam3.util;

import com.google.common.base.Splitter;
import com.google.common.base.StandardSystemProperty;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 对比文件内容工具类，使用了guava Maps.
 * Created by shuai.lv on 2017/3/23.
 */
public class ContentDiffUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ContentDiffUtil.class);
    private static final String LINE_SEPARATOR = StandardSystemProperty.LINE_SEPARATOR.value();
    private static final Splitter.MapSplitter MAP_SPLITTER = Splitter.on(LINE_SEPARATOR).trimResults().omitEmptyStrings().withKeyValueSeparator("=");

    public static String diff(String source,String target){
        StringBuilder sb = new StringBuilder();

        /*将String形式的内容解析为Map，使用Maps获得对比差异结果*/
        Map<String, String> sourceMap = MAP_SPLITTER.split(source);
        Map<String, String> targetMap = MAP_SPLITTER.split(target);
        MapDifference<String, String> difference = Maps.difference(sourceMap, targetMap);

        /**
         * 解析结果，输出指定形式的差异内容
         * 源存在、目标不存在 => -a2=y
         * 源不存在、目标存在 => +a4=n
         * 源和目标存在、但不同 => -a3=z;+a3=x
         */
        for (Map.Entry<String, String> entry : difference.entriesOnlyOnLeft().entrySet()) { //源存在、目标不存在
            sb.append("-").append(entry.getKey()).append("=").append(entry.getValue()).append(LINE_SEPARATOR);
        }
        for (Map.Entry<String, String> entry : difference.entriesOnlyOnRight().entrySet()) { //源不存在、目标存在
            sb.append("+").append(entry.getKey()).append("=").append(entry.getValue()).append(LINE_SEPARATOR);
        }
        for (Map.Entry<String, MapDifference.ValueDifference<String>> entry : difference.entriesDiffering().entrySet()) { //源和目标存在、但不同
            sb.append("-").append(entry.getKey()).append("=").append(entry.getValue().leftValue()).append(";")
                    .append("+").append(entry.getKey()).append("=").append(entry.getValue().rightValue()).append(LINE_SEPARATOR);
        }
        LOG.debug("对比完成，返回差异内容");
        return sb.toString();
    }

}
