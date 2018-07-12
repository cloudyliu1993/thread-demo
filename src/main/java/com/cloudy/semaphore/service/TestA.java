package com.cloudy.semaphore.service;

/**
 * Created by Administrator on 2018/7/12.
 */
public class TestA implements Runnable{

    private IService service;

    public TestA(IService service) {
        this.service = service;
    }

    public void run() {
        service.doService();
    }
}
