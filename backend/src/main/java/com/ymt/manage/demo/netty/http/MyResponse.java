package com.ymt.manage.demo.netty.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import java.io.IOException;
import java.sql.Timestamp;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/22
 */
public class MyResponse {

    private ChannelHandlerContext ctx;
    private HttpRequest req;

    public MyResponse(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }

    public void write(String msg) throws IOException, InterruptedException {
        Thread.sleep(5000);
        System.out.println(new Timestamp(System.currentTimeMillis()));
        try {
            if (msg == null || msg.length() == 0) {
                return;
            }
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(msg.getBytes("UTF-8")));
            response.headers().set("Content-Type", "Text/html");
            ctx.write(response);
        } finally {
            ctx.flush();
            ctx.close();
        }

    }
}
