package com.cloudy.semaphore;

import com.cloudy.semaphore.service.Service;
import com.cloudy.semaphore.service.TestA;
import org.junit.Test;

/**
 * Created by Administrator on 2018/7/12.
 */
public class Test01 {

    @Test
    public void test01() {
        Service service = new Service(5, 1, 3);

        for (int i = 0; i < 20; i++) {
            Thread testA = new Thread(new TestA(service));
            testA.start();
        }
        while (true);
    }

}
