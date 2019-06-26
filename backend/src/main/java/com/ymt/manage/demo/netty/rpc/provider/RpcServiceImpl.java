package com.ymt.manage.demo.netty.rpc.provider;

import com.ymt.manage.demo.netty.rpc.api.IRpcService;

/**
 * @Description TODO
 * @Author yangmingtian
 * @Date 2019/6/24
 */
public class RpcServiceImpl implements IRpcService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public int mult(int a, int b) {
        return a * b;
    }

    @Override
    public int div(int a, int b) {
        return a / b;
    }
}
