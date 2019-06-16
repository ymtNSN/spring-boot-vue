package com.ymt.manage.demo.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/16
 */
public class NIOServer {

    private int port = 8080;

    private Selector selector;

    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    public NIOServer(int port) {
        try {
            this.port = port;
            ServerSocketChannel server = ServerSocketChannel.open();
            server.bind(new InetSocketAddress(this.port));
            server.configureBlocking(false);

            selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        System.out.println("listen on " + this.port + ".");
        try {
            while (true) {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    process(key);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void process(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel channel = server.accept();
            channel.configureBlocking(false);
            key = channel.register(selector, SelectionKey.OP_READ);
        } else if (key.isReadable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            int len = channel.read(buffer);
            if (len > 0) {
                buffer.flip();
                String content = new String(buffer.array(), 0, len);
                key = channel.register(selector, SelectionKey.OP_WRITE);
                key.attach(content);
                System.out.println("读取内容：" + content);
            }
        } else if (key.isWritable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            String content = (String) key.attachment();
            channel.write(ByteBuffer.wrap(("输出：" + content).getBytes()));
            channel.close();
        }
    }

    public static void main(String[] args) {
        new NIOServer(8080).listen();
    }
}
