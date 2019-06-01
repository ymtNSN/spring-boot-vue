package com.ymt.manage.demo.thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description TODO
 * @Author yangmingtian
 * @Date 2019/5/30
 */
public class VolatileDemo {

//    static AtomicInteger num = new AtomicInteger(0);

    //    private final static Unsafe unsafe = Unsafe.getUnsafe();
    private static Unsafe unsafe;
    private static long valueOffset;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
            try {
                valueOffset = unsafe.objectFieldOffset
                        (AtomicInteger.class.getDeclaredField("value"));
            } catch (Exception ex) {
                throw new Error(ex);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static volatile int num;
    static CountDownLatch countDownLatch = new CountDownLatch(1);


    void incr() {
        num = unsafe.getAndAddInt(this, valueOffset, 1) + 1;
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileDemo volatileDemo = new VolatileDemo();
        ExecutorService executorService = Executors.newCachedThreadPool();
//        new ThreadPoolExecutor()
        new Thread(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10000; i++) {
                volatileDemo.incr();
                System.out.println("thread1:" + i);
            }
            System.out.println(num);
        }).start();
        new Thread(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10000; i++) {
                volatileDemo.incr();
                System.out.println("thread2:" + i);
            }
            System.out.println(num);
        }).start();
//        executorService.execute(thread1);
//        executorService.execute(thread2);
//        executorService.shutdown();
        System.out.println(VolatileDemo.valueOffset);
        Thread.sleep(1000);
        countDownLatch.countDown();

    }
}
