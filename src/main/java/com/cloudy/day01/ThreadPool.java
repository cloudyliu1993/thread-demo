package com.cloudy.day01;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/7/10.
 */
public class ThreadPool {

    @Test
    public void testExecutor() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i ++) {
            final int index = i;
            executorService.execute(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName() +  "---index:" + index);
                }
            });
        }
        Thread.sleep(2000);
    }


}
