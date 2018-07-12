package com.cloudy.semaphore.normal;

import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2018/7/12.
 */
public class ServiceFair implements IService{

    private Semaphore semaphore;

    private int acquireNum = 1;
    private int releaseNum = 1;

    public ServiceFair(int permits, int acquireNum, int releaseNum) {
        semaphore = new Semaphore(permits, Boolean.TRUE);
        this.acquireNum = acquireNum;
        this.releaseNum = releaseNum;
    }

    /**
     * 多线程调用doService会根据许可数阻塞到acquire行，如果release数大于acquire数会动态增加semaphore.permits
     * acquire 能够被中断
     */
    public void doService() {
        try {
            System.out.println(Thread.currentThread().getName() + "-tryAcquire....");
            semaphore.acquire(acquireNum);
            System.out.println(Thread.currentThread().getName() + "-acquire....");
            semaphore.release(releaseNum);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "进入了catch");
            e.printStackTrace();
        }
    }
}
