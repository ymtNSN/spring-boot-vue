package com.ymt.manage.demo.netty.rpc.registry;

import com.ymt.manage.demo.netty.rpc.protocol.InvokerProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author yangmingtian
 * @Date 2019/6/24
 */
public class RpcRegistryHandler extends ChannelInboundHandlerAdapter {

    private List<String> classNames = new ArrayList<>();
    private Map<String, Object> registryMap = new HashMap<>();

    public RpcRegistryHandler() {
        scannerClass("com.ymt.manage.demo.netty.rpc.provider");
        doRegistry();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object result = new Object();

        InvokerProtocol requset = (InvokerProtocol) msg;

        //当客户端建立连接时，需要从自定义协议中获取信息，拿到具体的服务和实参
        //使用反射调用
        if (registryMap.containsKey(requset.getClassName())) {
            Object clazz = registryMap.get(requset.getClassName());
            Method method = clazz.getClass().getMethod(requset.getMethodNmme(), requset.getParames());
            result = method.invoke(clazz, requset.getValues());
        }
        System.out.println(result);
        ctx.write(result);
        ctx.flush();
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private void doRegistry() {
        if (classNames.size() == 0) {
            return;
        }
        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);
                Class<?> i = clazz.getInterfaces()[0];
                registryMap.put(i.getName(), clazz.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void scannerClass(String packageNmae) {
        URL url = this.getClass().getClassLoader().getResource(packageNmae.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                scannerClass(packageNmae + "." + file.getName());
            } else {
                classNames.add(packageNmae + "." + file.getName().replace(".class", "").trim());
            }
        }
    }

}
