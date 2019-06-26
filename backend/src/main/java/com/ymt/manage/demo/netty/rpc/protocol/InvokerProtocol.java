package com.ymt.manage.demo.netty.rpc.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description TODO
 * @Author yangmingtian
 * @Date 2019/6/24
 */
@Data
public class InvokerProtocol implements Serializable {

    private String className;
    private String methodNmme;
    private Class<?>[] parames;
    private Object[] values;
}
