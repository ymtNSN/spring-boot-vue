package com.ymt.manage.demo.netty.tomcat;

import com.ymt.manage.demo.netty.http.MyRequst;
import com.ymt.manage.demo.netty.http.MyResponse;
import com.ymt.manage.demo.netty.http.MyServlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.util.concurrent.EventExecutorGroup;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/22
 */
public class MyTomcat {
    private int port = 8080;
    private ServerSocket serverSocket;
    private Map<String, MyServlet> servletMap = new HashMap<>();

    private Properties webxml = new Properties();

    public static void main(String[] args) {
        new MyTomcat().start();
    }

    public void start() {
        init();
        // boss线程
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        // worker线程
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup, workerGroup)
                    //线程处理类
                    .channel(NioServerSocketChannel.class)
                    // 子线程处理类
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 客户端初始化处理
                        @Override
                        protected void initChannel(SocketChannel client) throws Exception {

                            client.pipeline().addLast(new HttpResponseEncoder());
                            client.pipeline().addLast(new HttpRequestDecoder());
                            client.pipeline().addLast(new MyTomcatHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = server.bind(port).sync();
            System.out.println("my tomcat start ..." + port);
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


    private void init() {
        try {

            String WEB_INFO = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(WEB_INFO + "web2.properties");

            webxml.load(fis);

            for (Object k : webxml.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webxml.getProperty(key);
                    String className = webxml.getProperty(servletName + ".className");
                    MyServlet o = (MyServlet) Class.forName(className).newInstance();
                    servletMap.put(url, o);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class MyTomcatHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof HttpRequest) {
                HttpRequest req = (HttpRequest) msg;

                //转交给我们自己的request实现
                MyRequst request = new MyRequst(ctx, req);
                MyResponse response = new MyResponse(ctx, req);

                String url = request.getUrl();

                if (servletMap.containsKey(url)) {
                    servletMap.get(url).service(request, response);
                } else {
                    response.write("404 - Not Found");
                }


            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        }
    }
}
