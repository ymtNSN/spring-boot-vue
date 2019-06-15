package com.ymt.manage.demo.serial;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/15
 */
public interface ISerializer {

    <T> byte[] serialize(T obj);

    <T> T deserialize(byte[] data, Class<T> clazz);
}
