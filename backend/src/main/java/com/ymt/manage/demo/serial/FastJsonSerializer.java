package com.ymt.manage.demo.serial;

import com.alibaba.fastjson.JSON;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/15
 */
public class FastJsonSerializer implements ISerializer {
    @Override
    public <T> byte[] serialize(T obj) {
        return JSON.toJSONString(obj).getBytes();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return (T) JSON.parseObject(data, clazz);
    }
}
