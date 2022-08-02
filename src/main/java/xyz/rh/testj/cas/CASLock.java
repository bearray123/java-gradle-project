/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.cas;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 手动实现一个通过CAS机制的自旋锁
 * Created by xionglei01@baidu.com on 2022/8/2.
 */
class CASLock {

    //是否被持有，是否空闲
    AtomicBoolean isFree = new AtomicBoolean(true);

    void lock() {
        int i=0;
        // 判断是否是空闲=true，
        // 如果不是空闲=false，继续while循环
        // 循环
        // 如果是空闲 =true，则设置成非空闲，然后跳出循环
        while (!isFree.compareAndSet(true, false)) // 如果不是true，不是空闲的，则进行自旋
        {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("" + Thread.currentThread().getName() + "自旋: " + i++);
        }
    }

    void unlock() {
        isFree.set(true);
    }

}
