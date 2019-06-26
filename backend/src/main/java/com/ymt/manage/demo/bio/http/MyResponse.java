package com.ymt.manage.demo.bio.http;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/22
 */
public class MyResponse {
    private OutputStream out;

    public MyResponse(OutputStream os) {
        this.out = os;
    }

    public void write(String msg) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html;\n")
                .append("\r\n")
                .append(msg);
        out.write(sb.toString().getBytes());

    }
}
