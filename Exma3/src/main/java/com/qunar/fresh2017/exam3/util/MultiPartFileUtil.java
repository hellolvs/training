package com.qunar.fresh2017.exam3.util;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.StandardSystemProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * 文件上传工具类，返回文件内容String
 * Created by shuai.lv on 2017/3/23.
 */
public class MultiPartFileUtil {

    private static final Logger LOG = LoggerFactory.getLogger(MultiPartFileUtil.class);
    private static final Charset FILE_ENCODING = Charsets.UTF_8;
    private static final String LINE_SEPARATOR = StandardSystemProperty.LINE_SEPARATOR.value();

    public static String read(MultipartFile multipartFile){
        Preconditions.checkNotNull(multipartFile);
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try{
            br = new BufferedReader(new InputStreamReader(multipartFile.getInputStream(),FILE_ENCODING));
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line).append(LINE_SEPARATOR);
            }
        } catch (IOException e) {
            LOG.error("文件读取异常：{}",e.getMessage());
        }finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    LOG.error("文件流关闭异常：{}",e.getMessage());
                }
            }
        }
        LOG.debug("上传文件读取完成，filename:{}", multipartFile.getOriginalFilename());
        return sb.toString();
    }

}
