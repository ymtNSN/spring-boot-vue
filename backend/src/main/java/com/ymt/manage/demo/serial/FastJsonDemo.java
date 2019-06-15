package com.ymt.manage.demo.serial;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/15
 */
public class FastJsonDemo {
    public static void main(String[] args) {
        FastJsonSerializer fastJsonSerializer = new FastJsonSerializer();

        User user = new User();
        user.setAge(40);
        user.setName("ymt");

        byte[] bytes = fastJsonSerializer.serialize(user);
        System.out.println(bytes.length);

        User user1 = fastJsonSerializer.deserialize(bytes, User.class);
        System.out.println(user1);
    }
}
