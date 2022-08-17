/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.concurrent;

import java.util.concurrent.locks.LockSupport;
import xyz.rh.testj.XLog;

/**
 * LockSupport park和unpark其实实现了wait和notify的功能，不过还是有一些差别:
 * 1. park不需要获取某个对象的锁
 * 2. 可以做到先唤醒线程再阻塞线程，比如先调用unpark, 再调用park；但对称执行多次是无效的，跟底层原理有关
 *
 * 使用wait，notify来实现等待唤醒功能至少有两个缺点：
 * 1. wait和notify都是Object中的方法,在调用这两个方法前必须先获得锁对象，这限制了其使用场合:只能在同步代码块中。
 * 2. 当对象的等待队列中有多个线程时，notify只能随机选择一个线程唤醒，无法唤醒指定的线程，或者要么全部唤醒notifyAll。
 *
 * Created by xionglei01@baidu.com on 2022/8/9.
 */
class LockSupportMain {

   public static void main(String[] args) {


      Thread t1 = new Thread("lock_supprt_t") {

         @Override public void run() {

            XLog.log("t1  start  running.....");
            XLog.log("t1  sleeping....2s...");
            try {
               sleep(2000);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            XLog.log("t1  开始阻塞");

            LockSupport.park();

            XLog.log("t1  解除阻塞！");


         }
      };
      t1.start();

      try {
         XLog.log("主线程开始睡眠10秒...");
         Thread.sleep(10000);
         XLog.log("主线程结束睡眠！");
      } catch (InterruptedException e) {
      }
      //LockSupport.unpark(t1);
      t1.interrupt();
   }


   //LockSupport就是通过控制变量_counter来对线程阻塞唤醒进行控制的。原理有点类似于信号量机制。
   //
   //当调用park()方法时，会将_counter置为0，同时判断前值，小于1说明前面被unpark过,则直接退出，否则将使该线程阻塞。
   //当调用unpark()方法时，会将_counter置为1，同时判断前值，小于1会进行线程唤醒，否则直接退出。
   //形象的理解，线程阻塞需要消耗凭证(permit)，这个凭证最多只有1个。当调用park方法时，如果有凭证，则会直接消耗掉这个凭证然后正常退出；但是如果没有凭证，就必须阻塞等待凭证可用；而unpark则相反，它会增加一个凭证，但凭证最多只能有1个。
   //为什么可以先唤醒线程后阻塞线程？
   //因为unpark获得了一个凭证,之后调用park因为有凭证消费，故不会阻塞。
   //为什么唤醒两次后阻塞两次会阻塞线程。
   //因为凭证的数量最多为1，连续调用两次unpark和调用一次unpark效果一样，只会增加一个凭证；而调用两次park却需要消费两个凭证。



}
