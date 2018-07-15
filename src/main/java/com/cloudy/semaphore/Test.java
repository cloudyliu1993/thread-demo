package com.cloudy.semaphore;

import com.cloudy.semaphore.normal.*;

/**
 * Created by Administrator on 2018/7/12.
 */
public class Test {

    /**
     * 能够被中断
     */
    @org.junit.Test
    public void test01() {
        IService service = new Service(5, 1, 3);

        for (int i = 0; i < 20; i++) {
            Thread testA = new Thread(new ServiceHandler(service));
            testA.start();
            if (i == 10) {
                testA.interrupt();
            }
        }
        while (true);
    }

    /**
     * 不能被中断
     */
    @org.junit.Test
    public void test02() {
        IService service = new ServiceUninterruptibly(5, 1, 3);

        for (int i = 0; i < 20; i++) {
            Thread testA = new Thread(new ServiceHandler(service));
            testA.start();
            if (i == 10) {
                testA.interrupt();
            }
        }
        while (true);
    }

    /**
     * 获取semaphore可用许可数、重置许可数、获取队列等待线程数、判断是否有等待现场
     */
    @org.junit.Test
    public void test03() {
        IService service = new ServicePermits(1, 1, 1);

        for (int i = 0; i < 20; i++) {
            Thread testA = new Thread(new ServiceHandler(service));
            testA.start();
        }

        while (true);
    }

    /**
     * 公平信号量
     */
    @org.junit.Test
    public void test04() {
        IService service = new ServiceFair(1, 1, 1);

        for (int i = 0; i < 20; i++) {
            Thread testA = new Thread(new ServiceHandler(service));
            testA.start();
        }

        while (true);
    }

    /**
     * 非公平信号量
     */
    @org.junit.Test
    public void test05() {
        IService service = new ServiceNonFair(1, 1, 1);

        for (int i = 0; i < 20; i++) {
            Thread testA = new Thread(new ServiceHandler(service));
            testA.start();
        }

        while (true);
    }

    /**
     * tryAcquire使用
     */
    @org.junit.Test
    public void test06() {
        IService service = new ServiceTryAcquire(1, 2, 1);

        for (int i = 0; i < 20; i++) {
            Thread testA = new Thread(new ServiceHandler(service));
            testA.start();
        }

        while (true);
    }

    /**
     * 多进 单处理
     */
    @org.junit.Test
    public void test07() {
        IService service = new ServiceLock(5, 1, 3);

        for (int i = 0; i < 20; i++) {
            Thread testA = new Thread(new ServiceHandler(service));
            testA.start();
        }
        while (true);
    }
}
