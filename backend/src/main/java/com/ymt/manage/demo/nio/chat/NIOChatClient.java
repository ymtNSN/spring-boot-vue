package com.ymt.manage.demo.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @Description TODO
 * @Author yangmingtian
 * @Date 2019/6/26
 */
public class NIOChatClient {
    private final InetSocketAddress serverAddress = new InetSocketAddress("localhost", 8083);
    private Selector selector = null;
    private SocketChannel client = null;

    private String nickName = "";
    private Charset charset = Charset.forName("UTF-8");
    private static String USER_EXIST = "系统提示：该昵称已经存在，请换一个昵称";
    private static String USER_CONTENT_SPILIT = "#@#";

    public NIOChatClient() throws IOException {
        selector = Selector.open();
        //连接远程主机的ip和端口
        client = SocketChannel.open(serverAddress);
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }

    public static void main(String[] args) throws IOException {
        new NIOChatClient().session();
    }

    public void session() {
        new Reader().start();
        new Writer().start();
    }

    private class Reader extends Thread {

        @Override
        public void run() {
            try {
                while (true) {
                    int readyChannels = selector.select();
                    if (readyChannels == 0) continue;
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    if (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        process(key);
                    }
                }
            } catch (Exception e) {

            }
        }
    }

    private void process(SelectionKey key) throws IOException {
        if (key.isReadable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            String content = "";
            while (channel.read(buffer) > 0) {
                buffer.flip();
                content += charset.decode(buffer);
            }
            if (USER_EXIST.equals(content)) {
                nickName = "";
            }
            System.out.println(content);
            key.interestOps(SelectionKey.OP_READ);
        }
    }

    private class Writer extends Thread {

        @Override
        public void run() {
            try {
                //在主线程中 从键盘读取数据输入到服务器端
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if ("".equals(line)) continue;
                    if ("".equals(nickName)) {
                        nickName = line;
                        line = nickName + USER_CONTENT_SPILIT;
                    } else {
                        line = nickName + USER_CONTENT_SPILIT + line;
                    }
                    client.write(charset.encode(line));
                }
                scanner.close();
            } catch (Exception e) {

            }
        }
    }
}
