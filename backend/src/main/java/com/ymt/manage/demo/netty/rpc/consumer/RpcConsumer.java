package com.ymt.manage.demo.netty.rpc.consumer;

import com.ymt.manage.demo.netty.rpc.api.IRpcHelloService;
import com.ymt.manage.demo.netty.rpc.api.IRpcService;
import com.ymt.manage.demo.netty.rpc.consumer.proxy.RpcProxy;
import com.ymt.manage.demo.netty.rpc.registry.RpcRegistry;

/**
 * @Description TODO
 * @Author yangmingtian
 * @Date 2019/6/24
 */
public class RpcConsumer {

    public static void main(String[] args) {
        IRpcHelloService iRpcHelloService = RpcProxy.create(IRpcHelloService.class);
        System.out.println(iRpcHelloService.hello("ymt"));

        IRpcService iRpcService = RpcProxy.create(IRpcService.class);
        System.out.println(iRpcService.add(5, 7));
        System.out.println(iRpcService.sub(5, 7));
        System.out.println(iRpcService.mult(5, 7));
        System.out.println(iRpcService.div(5, 7));
    }
}
