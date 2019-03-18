package com.funtl.hello.spring.cloud.zuul.filter;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Zuul过滤器
 */
@Component
public class ZuulFilter extends com.netflix.zuul.ZuulFilter {
    /**
     * 配置过滤类型，有四种不同生命周期的过滤器类型
     * 1. pre：路由之前
     * 2. routing：路由之时
     * 3. post：路由之后
     * 4. error：发送错误调用
     * @return
     */
    @Override
    public String filterType() {
            return "pre";
    }

    /**
     * 配置过滤器的优先级
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 配置是否需要过滤：true/需要，false/不需要
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体业务代码
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //RequestContext:在过滤器之间传递消息
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request= context.getRequest();
        String toker = request.getParameter("toker");
        if(toker==  null){
            HttpServletResponse response = context.getResponse();
            response.setContentType("text/html;charset=utf-8");
            try {
                response.getWriter().write("非法请求");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
