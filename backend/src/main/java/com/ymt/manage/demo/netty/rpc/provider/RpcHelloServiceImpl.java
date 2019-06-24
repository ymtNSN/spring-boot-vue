package com.ymt.manage.demo.netty.rpc.provider;

import com.ymt.manage.demo.netty.rpc.api.IRpcHelloService;

/**
 * @Description TODO
 * @Author yangmingtian
 * @Date 2019/6/24
 */
public class RpcHelloServiceImpl implements IRpcHelloService {
    @Override
    public String hello(String name) {
        return "hello "+name;
    }
}
