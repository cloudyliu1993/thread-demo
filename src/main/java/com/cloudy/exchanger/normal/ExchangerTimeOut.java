package com.cloudy.exchanger.normal;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2018/7/16.
 */
public class ExchangerTimeOut extends Thread {

    private Exchanger<String> exchanger;

    public ExchangerTimeOut(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    public void run() {
        try {
            System.out.println("在线程A中得到线程B的值：" + exchanger.exchange("中国人A", 5, TimeUnit.SECONDS));
            System.out.println("A end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
