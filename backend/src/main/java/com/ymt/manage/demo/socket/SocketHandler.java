package com.ymt.manage.demo.socket;

import java.io.*;
import java.net.Socket;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/2
 */
public class SocketHandler implements Runnable {

    private Socket socket;

    public SocketHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 拿到输入流
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));

            PrintWriter printWriter = new PrintWriter(os);
            // 通过控制台拿到数据
            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("client:" + in.readLine());
            String line = sin.readLine();
            while (!line.equals("bye")) {
                // 写回客户端
                printWriter.println(line);
                printWriter.flush();
                System.out.println("client:" + in.readLine());
                // 重新读取控制台的数据
                line = sin.readLine();
            }
            // 获得客户端输入信息
            System.out.println(in.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
