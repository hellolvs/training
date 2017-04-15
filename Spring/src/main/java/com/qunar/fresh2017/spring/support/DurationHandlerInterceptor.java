package com.qunar.fresh2017.spring.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <code>DurationHandlerInterceptor</code>
 */
public class DurationHandlerInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOG = LoggerFactory
            .getLogger(DurationHandlerInterceptor.class);
    private NamedThreadLocal<Long> durationTimeThreadLocal =
            new NamedThreadLocal<Long>(DurationHandlerInterceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        long beginTime = System.currentTimeMillis();
        durationTimeThreadLocal.set(beginTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();
        long beginTime = durationTimeThreadLocal.get();
        long durationTime = endTime - beginTime;
        LOG.info("the request : {}, method : {}, duration time : {} millis", request.getRequestURI(), request.getMethod(), durationTime);

    }
}
