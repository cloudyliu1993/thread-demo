package com.cloudy.countdownlatch;

import com.cloudy.countdownlatch.normal.MyThread;
import com.cloudy.countdownlatch.normal.MyThreadTimeOut;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2018/7/16.
 */
public class Test {

    /**
     * 简单使用
     */
    @org.junit.Test
    public void test01() {
        final CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "就绪");
                    latch.countDown();
                }
            });
            thread.start();
        }

        try {
            System.out.println("await");
            latch.await();
            System.out.println("结束...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 运动会例子
     */
    @org.junit.Test
    public void test02() throws InterruptedException {
        CountDownLatch comingTag = new CountDownLatch(10);   // 裁判等待所有运动员到来
        CountDownLatch waitTag = new CountDownLatch(1);;     // 等待裁判说准备开始
        CountDownLatch waitRunTag = new CountDownLatch(10);;  // 等待起跑
        CountDownLatch beginTag = new CountDownLatch(1);;    // 起跑
        CountDownLatch endTag = new CountDownLatch(10);;      // 所有运动员到达终点

        MyThread[] myThreads = new MyThread[10];
        for (int i = 0; i < myThreads.length; i++) {
            myThreads[i] = new MyThread(comingTag, waitTag, waitRunTag, beginTag, endTag);
            myThreads[i].start();
        }

        System.out.println("等待选手到来！");
        comingTag.await();
        System.out.println("运动员到齐，各就各位前巡视用时5秒");
        Thread.sleep(5000);
        waitTag.countDown();
        System.out.println("各就各位！");
        waitRunTag.await();
        Thread.sleep(2000);
        System.out.println("发令枪响起！");
        beginTag.countDown();
        endTag.await();
        System.out.println("所有运动员到达，统计比赛名次！");
    }

    /**
     * 超时时间
     * @throws InterruptedException
     */
    @org.junit.Test
    public void test03() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            MyThreadTimeOut myThread = new MyThreadTimeOut();
            myThread.start();
        }
        Thread.sleep(300000);
    }

    /**
     * getCount
     */
    @org.junit.Test
    public void test04() {
        CountDownLatch latch = new CountDownLatch(3);
        System.out.println("count：" + latch.getCount());
        latch.countDown();
        System.out.println("count：" + latch.getCount());
        latch.countDown();
        System.out.println("count：" + latch.getCount());
        latch.countDown();
        System.out.println("count：" + latch.getCount());
        latch.countDown();
        System.out.println("count：" + latch.getCount());
        latch.countDown();
        System.out.println("count：" + latch.getCount());
        latch.countDown();
        System.out.println("count：" + latch.getCount());
        latch.countDown();
        System.out.println("count：" + latch.getCount());
        latch.countDown();
        System.out.println("count：" + latch.getCount());
        latch.countDown();
    }

}
