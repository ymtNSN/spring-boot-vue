package com.ymt.manage.demo.netty.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/22
 */
public class MyRequst {

    private ChannelHandlerContext ctx;
    private HttpRequest req;

    public MyRequst(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }

    public Map<String, List<String>> getParameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(req.uri());
        return decoder.parameters();
    }

    public String getParameter(String name) {
        Map<String, List<String>> parameters = getParameters();
        List<String> param = parameters.get(name);
        return null == name ? null : param.get(0);
    }


    public String getMethod() {
        return req.method().name();
    }

    public String getUrl() {
        return req.uri();
    }
}
