/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.designpatterns;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by xionglei01@baidu.com on 2022/7/22.
 */
class ProduceConsumerMain {

   public static void main(String[] args) {

      LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>(20);
      ProduceThread produceThread = new ProduceThread(q);
      ConsumerThread consumerThread = new ConsumerThread(q);
      produceThread.start();
      consumerThread.start();

   }



   public static class ProduceThread extends Thread {

      LinkedBlockingQueue<String> blockingQueue;

      public ProduceThread(LinkedBlockingQueue queue) {
         this.blockingQueue = queue;
      }

      @Override public void run() {

         int count = 0;
         while (true) {
            try {
               sleep(100);
               String data = "data_string_" + count++;
               System.out.println("生产者线程：put数据 ： " + data);
               blockingQueue.put(data);

            } catch (Exception exception ) {
               System.out.println("生产者线程 抛出异常");
               exception.printStackTrace();
               return;
            }
         }
      }

      public void stopThread() {
         this.interrupt();
      }

   }


   public static class ConsumerThread extends Thread {

      LinkedBlockingQueue<String> blockingQueue;

      public ConsumerThread(LinkedBlockingQueue queue) {
         this.blockingQueue = queue;
      }

      @Override public void run() {

         while (true) {
            try {
               String data = blockingQueue.take();
               System.out.println("-----消费者线程：take数据： " + data);
               sleep(5000);

            } catch (Exception exception) {
               System.out.println("消费者线程 抛出异常");
               exception.printStackTrace();
               return;
            }
         }
      }

      public void stopThread() {
         this.interrupt();
      }
   }



}
