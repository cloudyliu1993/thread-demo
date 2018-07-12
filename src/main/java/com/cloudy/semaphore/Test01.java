package com.cloudy.semaphore;

import com.cloudy.semaphore.service.Service;
import com.cloudy.semaphore.service.ServiceUninterruptibly;
import com.cloudy.semaphore.service.TestA;
import org.junit.Test;

/**
 * Created by Administrator on 2018/7/12.
 */
public class Test01 {

    /**
     * 能够被中断
     */
    @Test
    public void test01() {
        Service service = new Service(5, 1, 3);

        for (int i = 0; i < 20; i++) {
            Thread testA = new Thread(new TestA(service));
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
    @Test
    public void test02() {
        ServiceUninterruptibly service = new ServiceUninterruptibly(5, 1, 3);

        for (int i = 0; i < 20; i++) {
            Thread testA = new Thread(new TestA(service));
            testA.start();
            if (i == 10) {
                testA.interrupt();
            }
        }
        while (true);
    }

}
