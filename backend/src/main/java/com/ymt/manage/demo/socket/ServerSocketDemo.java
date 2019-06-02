package com.ymt.manage.demo.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/1
 */
public class ServerSocketDemo {

    static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            // 监听端口，默认是本机ip
            serverSocket = new ServerSocket(8070);
            // 接收客户端的连接（阻塞）
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new SocketHandler(socket));
//                new Thread(new SocketHandler(socket)).start();
            }
//            // 拿到输入流
//            InputStream is = socket.getInputStream();
//            OutputStream os = socket.getOutputStream();
//            BufferedReader in = new BufferedReader(new InputStreamReader(is));
//
//            PrintWriter printWriter = new PrintWriter(os);
//            // 通过控制台拿到数据
//            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
//            System.out.println("client:" + in.readLine());
//            String line = sin.readLine();
//            while (!line.equals("bye")) {
//                // 写回客户端
//                printWriter.println(line);
//                printWriter.flush();
//                System.out.println("client:" + in.readLine());
//                // 重新读取控制台的数据
//                line = sin.readLine();
//            }
//            // 获得客户端输入信息
//            System.out.println(in.readLine());
//            socket.close();
//            is.close();
//            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
