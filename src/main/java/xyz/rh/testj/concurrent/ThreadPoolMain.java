/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.jetbrains.annotations.NotNull;
import xyz.rh.testj.XLog;

/**
 *
 * ThreadPool 线程池深入理解
 * Created by xionglei01@baidu.com on 2022/7/22.
 */
class ThreadPoolMain {

   public static void main(String[] args){


      // 自定义线程池：CPU密集型：核心线程数 = CPU核数 + 1； IO密集型：核心线程数 = CPU核数 * 2 + 1
      // 如果任务被阻塞的时间少于执行时间，即这些任务是计算密集型的，则程序所需线程数将随之减少，但最少也不应低于处理器的核心数（例如可选择 CPU核数+1）；
      // 如果任务被阻塞的时间大于执行时间，即该任务是IO密集型的，比如如果任务有50%的时间处于阻塞状态，我们就需要创建比处理器核心数大几倍数量的线程（例如可选择 CPU核数*2+1）。
      //ExecutorService myExecutorServices = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1,
      //         10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());


      // 固定大小线程池
      // coreSize = maxSize = 传入的值， keepAliveTime=0, 不存在销毁
      // 因为不存在销毁一说，所以在main方法里如果是用生成了FixedThreadPool后只要不主动执行ExecutorService#shutDown()或shutDownNow() 则程序是不会停止的！！！
      ExecutorService executorServiceFixed = Executors.newFixedThreadPool(10, new ThreadFactory() {
         @Override public Thread newThread(@NotNull Runnable r) {
            return new Thread(r, "__newFixedThreadPool_thread_");
         }
      });

      // 单线程的线程池
      // coreSize=1, maxSize=1, keepAliveTime=0, 不存在销毁
      // 永远就一个线程存在，来回复用，也不会销毁
      // 因为不存在销毁一说，所以在main方法里如果是用生成了FixedThreadPool后只要不主动执行ExecutorService#shutDown()或shutDownNow() 则程序是不会停止的！！！
      ExecutorService executorServiceSingle = Executors.newSingleThreadExecutor(new ThreadFactory() {
         @Override public Thread newThread(@NotNull Runnable r) {
            return new Thread(r, "__newSingleThreadExecutor__thread_");
         }
      });

      executorServiceSingle.execute(new Runnable() {
         @Override public void run() {
            XLog.log("singleThreadExecutor start run.......");
         }
      });


      // 不限制大小的线程池，coreSize=0，无常驻核心线程，当线程执行完后只要达到keepAliveTime后就进行销毁，keepAliveTime=60s
      // 所以针对newCachedThreadPool，当任务执行完成后delay 60秒后线程就会自动退出！！！
      // 最大线程数是 INT.MAXVALUE
      // keepAliveTime = 60S
      ExecutorService executorServiceCached = Executors.newCachedThreadPool(new ThreadFactory() {
         @Override public Thread newThread(@NotNull Runnable r) {
            return new Thread(r, "__newCachedThreadPool__thread_");
         }
      });

      DelayQueue delayQueue = new DelayQueue();

      // 刚新建线程池后，其实线程池是没运行的，里面没具体的线程在running，只要执行了execute或者submit，就算提交的runnable是一个空方法很快执行完
      // 那么这个线程池中就产生了线程，会有线程在里面持久性的工作，也就说只要execute或submit后main主函数就不会停下来了，一直运行，因为里面有线程池在执行了不会自动退出
      executorServiceCached.execute(new Runnable() {
         @Override public void run() {

         }
      });

      executorServiceCached.submit(new Runnable() {
         @Override public void run() {


            while (true) {
               try {
                  Thread.sleep(1000);
               } catch (Exception ex) {

               }
            }

         }
      });

      // submit有返回值，而execute没有
      // submit方便Exception处理: 如果你在你的task里会抛出checked或者unchecked exception，
      // 而你又希望外面的调用者能够感知这些exception并做出及时的处理，那么就需要用到submit，通过捕获Future.get抛出的异常。
      //

      Future<String> future =  executorServiceCached.submit(new Callable<String>() {
         @Override public String call() throws Exception {
            int count=0;

            while (!Thread.currentThread().isInterrupted()) {
                  Thread.sleep(1000);
                  System.out.println("sumit callable" + count++);
            }
            // 这里有一个疑问：
            // 下面通过shutdownNow来会终止当前正在执行的线程，那么理论上sleep是响应了终端异常了，call方法是把异常抛给外层了，也就是main方法，
            // 但是main方法也没处理这个异常，不知道这个中断异常到底被谁消费了？？？
            // 后来搞懂这个问题了： 原来当执行shutdownNow后，sleep响应了中断，call函数把中断抛给后来的结果获取函数了future.get()，也就是那个当任务被
            // 中断之后，通过future.get()会获取到终端异常，也就是被future给消费掉了

            // 上述的分析原因也是为什么执行shutdownNow之后下面这行日志不会打印出来的原因
            System.out.println("has been interrupt, return while loop");
            return "data:" + count;
         }
      });

      try {
         Thread.sleep(5000);
      } catch (Exception e) {
         e.printStackTrace();
      }
      try {
         System.out.println("after 5s,  futrue.get = " + future.get(10, TimeUnit.MILLISECONDS));
      } catch (ExecutionException | TimeoutException | InterruptedException e) {
         System.out.println("future.get timeout....");
         e.printStackTrace();
      }

      try {
         Thread.sleep(20_000);
      } catch (Exception e) {
         e.printStackTrace();
      }

      System.out.println("shutdown after 20s");

      // shutdownNow走的是interruptWorkers()，中断所有的works
      executorServiceCached.shutdownNow();

      // shutdown走的是interruptIdleWorkers()，只会中断还没执行的（在队列中的）的works
      //executorServiceCached.shutdown();

      try {
         System.out.println("after 20s and shutdown,  futrue.get = " + future.get());
      } catch (ExecutionException e) {
         e.printStackTrace();
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }


}
