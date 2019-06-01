package com.ymt.manage.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description TODO
 * @Author yangmingtian
 * @Date 2019/5/31
 */
public class ThreadPoolDemo {
    static ExecutorService executors = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        executors.execute(new Thread(()->{
            System.out.println("sss");
        }));
    }
}
