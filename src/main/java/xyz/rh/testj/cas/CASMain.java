/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.cas;


/**
 * Created by xionglei01@baidu.com on 2022/8/2.
 */
class CASMain {

    CASLock casLock = new CASLock();

    public static void main(String[] args) {

        CASMain main = new CASMain();


        new Thread("Thread-A"){

            @Override public void run() {
                System.out.println("A线程开始获取lock...");
                main.casLock.lock();
                System.out.println("A线程已经获取到了lock!");
                try {
                    System.out.println("A线程开始睡眠20秒。。。。。。");
                    Thread.sleep(20000);
                    System.out.println("A线程睡眠完成。。。。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    main.casLock.unlock();
                }
            }
        }.start();

        new Thread("Thread-B"){

            @Override public void run() {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("B线程开始获取lock...");
                main.casLock.lock();
                System.out.println("B线程已经获取到了lock!");


            }
        }.start();




    }



}