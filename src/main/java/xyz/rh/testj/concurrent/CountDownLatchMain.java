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
