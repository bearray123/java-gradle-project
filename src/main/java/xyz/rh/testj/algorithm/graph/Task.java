/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.algorithm.graph;

/**
 * Created by xionglei01@baidu.com on 2022/8/20.
 */
public class Task {

   public String name;
   public long cost;

   Task(String name, long costime) {
      this.name = name;
      this.cost = costime;
   }

   void run() {
      System.out.println("Task [" + name + "] start running...mybe cost " + cost);
      try {
         Thread.sleep(cost);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      System.out.println("Task [" + name + "] completed...");
   }

   public String getUniqueId() {
      return this.getClass().getSimpleName() + "$$" + name;
   }

   @Override public String toString() {
      return "（Task" + name + ")";
   }
}
