package com.qunar.fresh2017.access;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.io.LineProcessor;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 行处理器，对每条请求进行汇总
 * Created by shuai.lv on 2017/3/1.
 */
public class RequsetLineProcessor implements LineProcessor<RequestInfo> {

    private int totalRequest;
    private HashMap<URI, AtomicInteger> uriCountMap = Maps.newHashMap();
    private HashMap<String, AtomicInteger> methodCountMap = Maps.newHashMap();
    private Multimap<String, String> uriCategoryMap = HashMultimap.create();

    @Override
    public boolean processLine(String line) throws IOException {
        totalRequest++;

        List<String> requestList = Splitter.on(CharMatcher.whitespace()).splitToList(line);
        Request request = new Request(requestList.get(0), requestList.get(1));

        AtomicInteger uriCount = uriCountMap.get(request.getUri());
        if (uriCount != null) {
            uriCount.getAndIncrement();
        } else {
            uriCountMap.put(request.getUri(), new AtomicInteger(1));
        }

        AtomicInteger methodCount = methodCountMap.get(request.getMethod());
        if (methodCount != null) {
            methodCount.getAndIncrement();
        } else {
            methodCountMap.put(request.getMethod(), new AtomicInteger(1));
        }

        String uri = request.getUri().getPath();
        List<String> uriList = Splitter.on("/").splitToList(uri);
        uriCategoryMap.put("/" + (uriList.size() <= 2 ? "" : uriList.get(1)), uri);

        return true;
    }

    @Override
    public RequestInfo getResult() {
        return new RequestInfo(totalRequest, uriCountMap, methodCountMap, uriCategoryMap);
    }
}
