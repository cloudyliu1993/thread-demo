package com.cloudy.semaphore.normal;

import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2018/7/12.
 */
public class ServicePermits implements IService{

    private Semaphore semaphore;

    private int acquireNum = 1;
    private int releaseNum = 1;

    public ServicePermits(int permits, int acquireNum, int releaseNum) {
        semaphore = new Semaphore(permits);
        this.acquireNum = acquireNum;
        this.releaseNum = releaseNum;
    }

    /**
     * 获取semaphore可用许可数、重置许可数、获取队列等待线程数、判断是否有等待现场
     */
    public void doService() {
        try {
            System.out.println(Thread.currentThread().getName() + "-tryAcquire...." + semaphore.getQueueLength());
            semaphore.acquire(acquireNum);
            Thread.sleep(1000);

            System.out.println(Thread.currentThread().getName() + "-queueLength:" + semaphore.getQueueLength() + "-hasQueuedThreads:" + semaphore.hasQueuedThreads());
            System.out.println(Thread.currentThread().getName() + "-acquire....");
            System.out.println(Thread.currentThread().getName() + "-availablePermits:" + semaphore.availablePermits());
            System.out.println(Thread.currentThread().getName() + "-drainPermits:" + semaphore.drainPermits() + "-availablePermits:" + semaphore.availablePermits());
            System.out.println(Thread.currentThread().getName() + "-drainPermits:" + semaphore.drainPermits() + "-availablePermits:" + semaphore.availablePermits());
            System.out.println(Thread.currentThread().getName() + "-drainPermits:" + semaphore.drainPermits() + "-availablePermits:" + semaphore.availablePermits());

            semaphore.release(releaseNum);
            System.out.println(Thread.currentThread().getName() + "-availablePermits:" + semaphore.availablePermits());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "进入了catch");
            e.printStackTrace();
        }
    }
}
