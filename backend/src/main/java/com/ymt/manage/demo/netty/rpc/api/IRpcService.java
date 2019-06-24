package com.ymt.manage.demo.netty.rpc.api;

/**
 * @Description TODO
 * @Author yangmingtian
 * @Date 2019/6/24
 */
public interface IRpcService {

    public int add(int a, int b);

    public int sub(int a, int b);

    public int mult(int a, int b);

    public int div(int a, int b);
}
