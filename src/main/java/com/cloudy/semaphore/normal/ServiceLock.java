package com.cloudy.semaphore.normal;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2018/7/12.
 */
public class ServiceLock implements IService{

    private Semaphore semaphore;

    private ReentrantLock lock = new ReentrantLock();

    private int acquireNum = 1;
    private int releaseNum = 1;

    public ServiceLock(int permits, int acquireNum, int releaseNum) {
        semaphore = new Semaphore(permits);
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
            lock.lock();

            System.out.println(Thread.currentThread().getName() + "-acquire....");
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName() + "-sleep finish....");

            lock.unlock();
            semaphore.release(releaseNum);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "进入了catch");
            e.printStackTrace();
        }
    }
}
