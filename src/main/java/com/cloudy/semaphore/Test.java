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

    @org.junit.Test
    public void test03() {
        IService service = new ServicePermits(1, 1, 1);

        for (int i = 0; i < 20; i++) {
            Thread testA = new Thread(new ServiceHandler(service));
            testA.start();
        }

        while (true);
    }

}
