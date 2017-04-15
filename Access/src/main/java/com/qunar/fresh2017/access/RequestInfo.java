package com.qunar.fresh2017.access;

import com.google.common.collect.Multimap;

import java.net.URI;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 请求信息汇总类，使用多种Map进行汇总
 * Created by shuai.lv on 2017/3/1.
 */
public class RequestInfo {

    private int totalRequest;
    private HashMap<URI, AtomicInteger> uriCountMap;
    private HashMap<String, AtomicInteger> methodCountMap;
    private Multimap<String, String> uriCategoryMap;

    public RequestInfo(int totalRequest, HashMap<URI, AtomicInteger> uriCountMap,
                       HashMap<String, AtomicInteger> methodCountMap, Multimap<String, String> uriCategoryMap) {
        this.totalRequest = totalRequest;
        this.uriCountMap = uriCountMap;
        this.methodCountMap = methodCountMap;
        this.uriCategoryMap = uriCategoryMap;
    }

    public int getTotalRequest() {
        return totalRequest;
    }

    public void setTotalRequest(int totalRequest) {
        this.totalRequest = totalRequest;
    }

    public HashMap<URI, AtomicInteger> getUriCountMap() {
        return uriCountMap;
    }

    public void setUriCountMap(HashMap<URI, AtomicInteger> uriCountMap) {
        this.uriCountMap = uriCountMap;
    }

    public HashMap<String, AtomicInteger> getMethodCountMap() {
        return methodCountMap;
    }

    public void setMethodCountMap(HashMap<String, AtomicInteger> methodCountMap) {
        this.methodCountMap = methodCountMap;
    }

    public Multimap<String, String> getUriCategoryMap() {
        return uriCategoryMap;
    }

    public void setUriCategoryMap(Multimap<String, String> uriCategoryMap) {
        this.uriCategoryMap = uriCategoryMap;
    }
}
