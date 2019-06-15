package com.ymt.manage.demo.serial;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/15
 */
public class ProtobufDemo {

    public static void main(String[] args) {
        UserProtos.User user = UserProtos.User.newBuilder().setAge(19).setName("ymt").build();

        byte[] bytes = user.toByteArray();
        System.out.println(bytes.length);

        for (int i= 0; i< bytes.length;i++){
            System.out.print(bytes[i]+" ");
        }
    }
}
