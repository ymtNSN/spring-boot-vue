package com.ymt.manage.demo.socket;

import java.io.*;
import java.net.Socket;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/1
 */
public class ClientSocketDemo {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8070);

            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();
            // 在当前连接上写入输入
            PrintWriter out = new PrintWriter(os, true);
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            // 通过控制台拿到数据
            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
            String line = sin.readLine();
            while (!line.equals("bye")) {
                out.println(line);
                out.flush();
                System.out.println("Server:" + in.readLine());
                line = sin.readLine();
            }
//            out.println("hello ymt");
//            socket.close();
//            os.close();
//            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
