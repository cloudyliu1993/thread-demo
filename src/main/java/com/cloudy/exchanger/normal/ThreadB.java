package com.cloudy.exchanger.normal;

import java.util.concurrent.Exchanger;

/**
 * Created by Administrator on 2018/7/16.
 */
public class ThreadB implements Runnable {
    private Exchanger<String> exchanger;
    public ThreadB(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    public void run() {
        try {
            System.out.println("在线程B中获取线程A的值:" + exchanger.exchange("中国人B"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
