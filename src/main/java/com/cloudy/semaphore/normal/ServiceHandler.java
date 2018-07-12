package com.cloudy.semaphore.normal;

/**
 * Created by Administrator on 2018/7/12.
 */
public class ServiceHandler implements Runnable {

    private IService service;

    public ServiceHandler(IService service) {
        this.service = service;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程启动了...");
        service.doService();
    }
}
