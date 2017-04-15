package com.qunar.fresh2017.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.qunar.base.meerkat.http.QunarHttpClient;
import com.qunar.base.meerkat.http.data.HttpResult;
import com.qunar.base.meerkat.http.data.PostParameter;
import com.qunar.base.meerkat.http.data.QunarHttpGet;
import com.qunar.base.meerkat.http.data.QunarHttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;

/**
 * 通过http get请求 获取用户密匙接口
 * 解析json获取到用户密匙,将接口密匙使用post方式传递给校验用户密匙接口
 * Created by shuai.lv on 2017/3/13.
 */
public class Http {

    private static final Logger LOGGER = LoggerFactory.getLogger(Http.class);

    private static final String TOKEN_URL = "http://l-qmexp1.f.dev.cn6.qunar.com:10000/httpDemo/userToken/getUserInfo";
    private static final String VALIDATE_URL = "http://l-qmexp1.f.dev.cn6.qunar.com:10000/httpDemo/userToken/validateToken";
    private static final String UTF_8 = "utf-8";
    private static final String USERNAME = "shuai.lv";
    private static final String GZIP_HEADER = "Accept-Encoding:gzip";

    private static final QunarHttpClient QUNAR_HTTP_CLIENT = QunarHttpClient.createDefaultClient(20000,20000,200,50);

    static {
        QUNAR_HTTP_CLIENT.allowCookiePolicy(); //开启cookie
    }

    public static void main(String[] args) throws IOException {
        try {
            String content = httpGet(USERNAME); //发送GET请求返回响应内容
            String token = parseToken(content);  //Jackson解析响应内容，获取token
            httpPost(USERNAME, token);  //发送Post请求验证用户token
        } finally {
            if (QUNAR_HTTP_CLIENT != null) {
                QUNAR_HTTP_CLIENT.close();
            }
        }
    }

    /*发送GET请求返回响应内容*/
    public static String httpGet(String userName) throws IOException {

        Preconditions.checkNotNull(userName);
        Preconditions.checkArgument(!userName.isEmpty());

        //新建GET请求，在Header中添加Gzip方式
        QunarHttpGet qunarHttpGet = new QunarHttpGet(TOKEN_URL + "?userName=" + userName);
        qunarHttpGet.addHeader(GZIP_HEADER);
        LOGGER.info("建立GET请求对象：{}", qunarHttpGet);

        //发送GET请求，返回响应内容
        HttpResult httpResult = QUNAR_HTTP_CLIENT.httpExecute(qunarHttpGet, UTF_8);
        String content = httpResult.getContent();
        LOGGER.info("发送GET请求返回的响应内容为:{}", content);

        return content;
    }

    /*Jackson解析响应内容，获取token*/
    public static String parseToken(String content) throws IOException {

        Preconditions.checkNotNull(content);
        Preconditions.checkArgument(!content.isEmpty());

        //将json字符串解析到HashMap中
        HashMap resultMap = new ObjectMapper().readValue(content, HashMap.class);
        String token = resultMap.get("data").toString();
        LOGGER.info("jackson解析响应内容获取到的token为:{}", token);

        return token;
    }

    /*发送Post请求验证用户token,返回响应内容*/
    public static String httpPost(String userName, String token) throws IOException {

        Preconditions.checkNotNull(userName);
        Preconditions.checkArgument(!userName.isEmpty());
        Preconditions.checkNotNull(token);
        Preconditions.checkArgument(!token.isEmpty());

        //新建Post请求，在Header中添加Gzip方式
        QunarHttpPost qunarHttpPost = new QunarHttpPost(VALIDATE_URL);
        qunarHttpPost.addHeader(GZIP_HEADER);
        LOGGER.info("建立POST请求对象：{}", qunarHttpPost);

        //设置Post请求参数
        PostParameter postParameter = new PostParameter();
        postParameter.put("userName", userName);
        postParameter.put("userToken", token);
        qunarHttpPost.setEntity(new UrlEncodedFormEntity(postParameter.getNvps(), UTF_8));
        LOGGER.info("设置POST请求参数：{}", postParameter.getNvps().toString());

        //发送POST请求，返回响应内容
        HttpResult httpResult = QUNAR_HTTP_CLIENT.httpExecute(qunarHttpPost, UTF_8);
        String content = httpResult.getContent();
        LOGGER.info("发送POST请求返回的响应内容为:{}", content);

        return content;
    }

}

