package com.qiuzeming.servicezuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by qiuzeming on 2018/3/21.
 */
@Component
public class MyFilter extends ZuulFilter {

    private final Logger log = LoggerFactory.getLogger(MyFilter.class);

    /**
     * 返回一个字符串代表过滤器类型
     * pre: 路由之前
     * routing: 路由之时
     * post: 路由之后
     * error: 发送错误调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤顺序
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        if (accessToken == null) {
            log.warn("token is empty!");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);

            try {
                requestContext.getResponse().getWriter().write("token is empty!");
            } catch (IOException e) {

            }
            return null;
        }
        log.info("ok");
        return null;
    }
}