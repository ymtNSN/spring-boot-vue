package com.ymt.manage.demo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/16
 */
public class BIOServer {
    ServerSocket serverSocket;


    public BIOServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("BIO服务已启动，监听端口：" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() throws IOException {
        while (true) {
            // 等待客户端连接
            Socket client = serverSocket.accept();
            InputStream is = client.getInputStream();

            byte[] buff = new byte[1024];
            int len = is.read(buff);
            if (len > 0) {
                String msg = new String(buff, 0, len);
                System.out.println("收到：" + msg);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BIOServer bioServer = new BIOServer(8080);
        bioServer.listen();
    }
}
