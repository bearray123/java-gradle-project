/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import xyz.rh.testj.XLog;

/**
 * Created by xionglei01@baidu.com on 2022/7/23.
 */
class ReentrantLockMain {

   public static void main(String[] args) {

      //base();

      conditionTest();


   }

   public static void conditionTest() {

      ReentrantLock lock = new ReentrantLock();
      Condition putCondition = lock.newCondition();

      new Thread("Thread-A") {
         @Override public void run() {

            XLog.log("线程A 尝试拿锁...");
            lock.lock();
            XLog.log("线程A 拿到了锁!");
            try {
               XLog.log("线程A 开始10秒的睡眠 zzzzzzzzzz");
               putCondition.await(10, TimeUnit.SECONDS);
               XLog.log("线程A 醒来了");
            } catch (InterruptedException e) {
               XLog.log("线程A 出现异常：" + e.getMessage());
               e.printStackTrace();
            } finally {
               lock.unlock();
            }
         }
      }.start();

      new Thread("Thread-B") {
         @Override public void run() {

            XLog.log("线程B 尝试拿锁...");
            lock.lock();
            XLog.log("线程B 拿到了锁!");
            try {
               XLog.log("线程B 睡眠3秒  zzz");
               sleep(3000);
               XLog.log("线程B 醒来了! 然后执行putCondition.signal()");
               putCondition.signal();
            } catch (InterruptedException e) {
               XLog.log("线程B 出现异常：" + e.getMessage());
               e.printStackTrace();
            } finally {
               lock.unlock();
            }
         }
      }.start();


   }


   public static void base() {
      ReentrantLock lock = new ReentrantLock();

      new Thread("Thread-A") {
         @Override public void run() {
            try {
               //sleep(5000);
               XLog.log("线程A 调用lock");
               lock.lock();
               try {
                  XLog.log("线程A 拿到了lock");
                  sleep(10000);
               } finally {
                  lock.unlock();
                  XLog.log("线程A sleep 10s, 释放了锁");
               }

            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
      }.start();

      new Thread("Thread-B") {
         @Override public void run() {

            try {
               sleep(2000);
               XLog.log("线程B 调用lock");
               lock.lock();
               try {
                  XLog.log("线程B 拿到了lock");
                  sleep(2000);
               } finally {
                  lock.unlock();
                  XLog.log("线程B sleep 2s, 释放了锁");
               }

            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
      }.start();
   }

}
