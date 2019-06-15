package com.ymt.manage.tasks;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author yangmingtian
 * @Date 2019/6/5
 */
@Component
public class AsyncService {

    @Async("asyncPoolTaskExecutor")
    public void test() {
        System.out.println(Thread.currentThread().getName() + ": asyn ....");
    }

    public static void main(String[] args) {
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);
    }
}
