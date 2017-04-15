package com.qunar.fresh2017.spring.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <code>DurationHandlerInterceptor</code>
 */
public class IpHandlerInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOG = LoggerFactory
            .getLogger(IpHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        LOG.info("本次请求的ip地址为：{}", request.getRemoteAddr());
        return true;
    }
}
