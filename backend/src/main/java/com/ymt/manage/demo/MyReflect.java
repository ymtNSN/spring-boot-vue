package com.ymt.manage.demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description TODO
 * @Author yangmingtian
 * @Date 2019/5/10
 */
public class MyReflect {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Proxy proxy = new Proxy();
        proxy.run();
        Method method = Proxy.class.getDeclaredMethod("run");
        method.invoke(proxy);

    }


    static class Proxy{
        public void run(){
            System.out.println("run ---");
        }
    }
}
