/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;
import xyz.rh.testj.XLog;

/**
 * 闭锁：CountDownLatch
 * 是一种同步辅助，让我们多个线程执行任务时，需要等待任务线程执行完成后，才能执行后面的任务，相当于是加强版的Thread.join，比join管理粒度更细更强大
 * 它是一个计数器，线程完成一个记录一个，计数器递减。当预备的个数new CountDownLatch(3) 减少到0时代表任务都执行完成了，跟kotlin的async-await有点像
 * 主要用于多任务执行时存在依赖时通过计数器进行管理，比如 task3的执行依赖于task1和task2执行完成，其中task1和task2无任何关联
 *
 * 内部原理：通过AQS锁框架来实现，AQS的内部结构有一个state字段，通过该字段来控制锁的操作，CountDownLatch是如何控制多个线程执行都执行结束？其实CountDownLatch内部是将state作为计数器来使用，
 * 比如我们初始化时，state计数器为3，同时开启三个线程当有一个线程执行成功，每当有一个线程执行完成后就将state值减少1，直到减少到为0时，说明所有线程已经执行完毕。
 *
 *
 * 实现多线程任务同步的其他手段还有传统的Thread.join方法，让当前任务线程执行完成后再走后面的，不过join只能线程的run方法整体去串行，然后再执行后面的，无法做到
 * 在run方法里某行代码执行完成后就执行后面的，这点只有CountDownLatch可以办到，它粒度更细。
 * 其实Thread.join方法底层实现还是通过Object.wait来操作的，wait是一个native方法。
 *
 * 与闭锁相关的还有CyclicBarrier 也在JDK并发包里
 *
 * Created by xionglei01@baidu.com on 2022/8/18.
 */
class CountDownLatchMain {


   // 定义3个task
   private CountDownLatch mCountDownlatch = new CountDownLatch(3);

   ExecutorService mCachedThreadPoolExecutor = Executors.newFixedThreadPool(3, new ThreadFactory() {
      int nameCountSuffix = 1;
      @Override public Thread newThread(@NotNull Runnable r) {
         return new Thread(r, "fixed_thread_pool_create_" + nameCountSuffix++);
      }
   });

   public static void main(String[] args) {

      CountDownLatchMain sMain = new CountDownLatchMain();

      sMain.startTask1(sMain);
      sMain.startTask2(sMain);
      sMain.startTask3(sMain);

      try {
         sMain.mCountDownlatch.await();

         // 这里测试只等8秒，任务1执行要1s，任务2执行要5秒，任务3执行要15秒，所以最多只能等到任务1，2执行完成！,最终countdownlatch.getCount=1
         //sMain.mCountDownlatch.await(8, TimeUnit.SECONDS);

      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      XLog.log("THE END!!!");

      XLog.log("countdownlatch.getCount === " + sMain.mCountDownlatch.getCount());
      XLog.log("FixedThreadPoolExecutor.isShutdown() ==" + sMain.mCachedThreadPoolExecutor.isShutdown());
      XLog.log("FixedThreadPoolExecutor.isTerminated() ==" + sMain.mCachedThreadPoolExecutor.isTerminated());

      // 因为用的cached线程池，所以主要不主动shutdown，程序就不会退出，一直持续keepAliveTime = 60s的时间后才会退出
      sMain.mCachedThreadPoolExecutor.shutdown();

   }

   private void startTask1(CountDownLatchMain owner) {
      owner.mCachedThreadPoolExecutor.execute(new Runnable() {
         @Override public void run() {
            XLog.log("task1 is running..mybe cost 1 seconds");
            try {
               Thread.sleep(1000);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            owner.mCountDownlatch.countDown();
            XLog.log("task1 is completed!");
         }
      });
   }

   private void startTask2(CountDownLatchMain owner) {
      owner.mCachedThreadPoolExecutor.execute(new Runnable() {
         @Override public void run() {
            XLog.log("task2 is running....mybe cost 5 seconds");
            try {
               Thread.sleep(5_000);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            owner.mCountDownlatch.countDown();
            XLog.log("task2 is completed!");
         }
      });
   }

   private void startTask3(CountDownLatchMain owner) {
      owner.mCachedThreadPoolExecutor.execute(new Runnable() {
         @Override public void run() {
            XLog.log("task3 is running........mybe cost 15 seconds");
            try {
               Thread.sleep(15_000);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            owner.mCountDownlatch.countDown();
            XLog.log("task3 is completed!");
         }
      });
   }


}
