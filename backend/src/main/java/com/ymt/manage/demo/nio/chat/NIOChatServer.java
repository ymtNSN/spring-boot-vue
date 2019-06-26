package com.ymt.manage.demo.nio.chat;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description TODO
 * @Author yangmingtian
 * @Date 2019/6/26
 */
public class NIOChatServer {

    private int port;
    private Charset charset = Charset.forName("UTF-8");
    //相当于自定义协议格式，与客户端协商好
    private static String USER_CONTENT_SPILIT = "#@#";
    private static String USER_EXIST = "系统提示：该昵称已经存在，请换一个昵称";
    //用来记录在线人数，以及昵称
    private static HashSet<String> users = new HashSet<String>();
    private Selector selector = null;

    public NIOChatServer(int port) throws IOException {
        this.port = port;
        ServerSocketChannel server = ServerSocketChannel.open();

        server.bind(new InetSocketAddress(this.port));
        server.configureBlocking(false);

        selector = Selector.open();

        server.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务已启动，监听端口是：" + this.port);
    }

    public static void main(String[] args) throws IOException {
        new NIOChatServer(8083).listen();
    }

    public void listen() throws IOException {
        while (true) {
            int wait = selector.select();
            if (wait == 0) continue;
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                process(key);
            }
        }
    }

    private void process(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            //注册选择器，并设置为读取模式，收到一个连接请求，然后起一个SocketChannel，并注册到selector上，之后这个连接的数据，就由这个SocketChannel处理
            client.register(selector, SelectionKey.OP_READ);

            //将此对应的channel设置为准备接受其他客户端请求
            key.interestOps(SelectionKey.OP_ACCEPT);
            client.write(charset.encode("请输入你的昵称"));
        }

        if (key.isReadable()) {
            //返回该SelectionKey对应的 Channel，其中有数据需要读取
            SocketChannel client = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            StringBuilder content = new StringBuilder();
            try {
                while (client.read(buffer) > 0) {
                    buffer.flip();
                    content.append(charset.decode(buffer));
                }
                //将此对应的channel设置为准备下一次接受数据
                key.interestOps(SelectionKey.OP_READ);

            } catch (IOException io) {
                key.cancel();
                if (key.channel() != null) {
                    key.channel().close();
                }
            }
            if (content.length() > 0) {
                String[] arrContent = content.toString().split(USER_CONTENT_SPILIT);
                //注册用户
                if (arrContent != null && arrContent.length == 1) {
                    String nickName = arrContent[0];
                    if (users.contains(nickName)) {
                        client.write(charset.encode(USER_EXIST));
                    } else {
                        users.add(nickName);
                        String message = "欢迎 " + nickName + " 进入聊天室！当前在线人数：" + onlineCount();
                        broadCast(null, message);
                    }
                } else if (arrContent != null && arrContent.length > 1) {
                    String nickName = arrContent[0];
                    String message = content.substring(nickName.length() + USER_CONTENT_SPILIT.length());
                    message = nickName + " 说 " + message;
                    if (users.contains(nickName)) {
                        broadCast(client, message);
                    }
                }
            }

        }
    }

    private void broadCast(SocketChannel client, String content) throws IOException {
        for (SelectionKey key : selector.keys()) {
            Channel channel = key.channel();
            if (channel instanceof SocketChannel && channel != client) {
                SocketChannel target = (SocketChannel) channel;
                target.write(charset.encode(content));
            }
        }
    }

    private int onlineCount() {
        int res = 0;
        for (SelectionKey key : selector.keys()) {
            Channel channel = key.channel();
            if (channel instanceof SocketChannel) {
                res++;
            }
        }
        return res;
    }
}
