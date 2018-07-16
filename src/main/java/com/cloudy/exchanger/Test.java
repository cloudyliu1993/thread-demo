package com.cloudy.exchanger;

import com.cloudy.exchanger.normal.ExchangerTimeOut;
import com.cloudy.exchanger.normal.ThreadA;
import com.cloudy.exchanger.normal.ThreadB;

import java.util.concurrent.Exchanger;

/**
 * Created by Administrator on 2018/7/16.
 */
public class Test {

    /**
     * 数据交换
     * @throws InterruptedException
     */
    @org.junit.Test
    public void test01() throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<String>();
        ThreadA threadA = new ThreadA(exchanger);
        ThreadB threadB = new ThreadB(exchanger);

        Thread thread1 = new Thread(threadA);
        Thread thread2 = new Thread(threadB);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    /**
     *
     * @throws InterruptedException
     */
    @org.junit.Test
    public void test02() throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<String>();
        ExchangerTimeOut exchangerTimeOut = new ExchangerTimeOut(exchanger);
        exchangerTimeOut.start();
        exchangerTimeOut.join();
        System.out.println("test02 end!");
    }

}
