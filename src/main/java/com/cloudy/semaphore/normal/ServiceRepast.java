package com.cloudy.semaphore.normal;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2018/7/15.
 */
public class ServiceRepast {

    private volatile Semaphore setSemaphore = new Semaphore(10);
    private volatile Semaphore getSemaphore = new Semaphore(20);
    private volatile ReentrantLock lock = new ReentrantLock();
    private volatile Condition setCondition = lock.newCondition();
    private volatile Condition getCondition = lock.newCondition();
    private volatile Object[] producePosition = new Object[4];

    private boolean isEmpty() {
        boolean isEmpty = true;
        for (int i = 0; i < producePosition.length; i++) {
            if (null != producePosition[i]) {
                isEmpty = false;
                break;
            }
        }

        return isEmpty;
    }

    private boolean isFull() {
        boolean isFull = true;
        for (int i = 0; i < producePosition.length; i++) {
            if (null == producePosition[i]) {
                isFull = false;
                break;
            }
        }

        return isFull;
    }

    public void set() {
        try {
            setSemaphore.acquire();
            lock.lock();

            while (isFull()) {
                System.out.println("数据已满............");
                setCondition.await();
            }

            for (int i = 0; i < producePosition.length; i++) {
                if (null == producePosition[i]) {
                    producePosition[i] = "数据";
                    System.out.println(Thread.currentThread().getName() + "生产了" + producePosition[i]);
                    break;
                }
            }

            getCondition.signalAll();
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            setSemaphore.release();
        }
    }

    public void get() {
        try {
            getSemaphore.acquire();
            lock.lock();

            while (isEmpty()) {
                System.out.println("数据已空............");
                getCondition.await();
            }

            for (int i = 0; i < producePosition.length; i++) {
                if (null != producePosition[i]) {
                    producePosition[i] = null;
                    System.out.println(Thread.currentThread().getName() + "消费了数据");
                    break;
                }
            }

            setCondition.signalAll();
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            getSemaphore.release();
        }
    }


}
