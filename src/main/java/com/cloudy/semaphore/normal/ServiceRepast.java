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




}
