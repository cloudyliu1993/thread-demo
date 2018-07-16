package com.cloudy.exchanger.normal;

import java.util.concurrent.Exchanger;

/**
 * Created by Administrator on 2018/7/16.
 */
public class ThreadA implements Runnable {

    private Exchanger<String> exchanger;

    public ThreadA(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    public void run() {
        try {
            System.out.println("在线程A中获得线程B的值:" + exchanger.exchange("中国人A"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
