package com.ymt.manage.demo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/16
 */
public class BIOClient {

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 8080);

        OutputStream os = client.getOutputStream();
        String name = UUID.randomUUID().toString();
        System.out.println("客户端发送数据：" + name);
        os.write(name.getBytes());
        InputStream is = client.getInputStream();
        byte[] buff = new byte[1024];
        int read = is.read(buff);
        if (read > 0) {
            System.out.println(new String(buff, 0, read));
        }
        os.close();
        is.close();

    }
}
