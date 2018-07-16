package com.cloudy.countdownlatch.normal;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/7/16.
 */
public class MyThreadTimeOut extends Thread {

    private CountDownLatch latch = new CountDownLatch(1);

    public void run() {
        System.out.println(Thread.currentThread().getName() + "准备" + System.currentTimeMillis());
        try {
            latch.await(2, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName() + "结束" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
