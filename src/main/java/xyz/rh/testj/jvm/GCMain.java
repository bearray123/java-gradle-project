/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.jvm;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xionglei01@baidu.com on 2022/7/13.
 */
class GCMain {

   public static void main(String[] args) {
      testWeakReference();
   }

   /**
    * 测试弱引用在GC时被回收；
    * 结论：对于强软弱虚引用，弱引用常用来做缓存，也就是在系统触发gc时，如果该对象不存在其他强，软引用时，该对象就会被回收；
    * 纠正之前错误的理解，之前粗略的认为对于弱引用只要是系统gc了，那么弱引用里的对象就一定会被回收，其实不是这样的；需要该对象满足不再被其他强、软等引用才行；
    */
   public static void testWeakReference() {
      Object obj = new Object();

      // 对于这种仅存在被弱引用引用的对象new Object(); 在触发gc后就会被回收；
      //WeakReference<Object> weakReference=new WeakReference<Object>(new Object());
      // 这里传的是obj引用，该对象被两个地方引用：1是申明的地方，2是弱引用，所以在触发gc后还存在申明地方的引用；故不会马上被回收
      WeakReference<Object> weakReference=new WeakReference<Object>(obj);


      System.out.println("beforeGC:"+weakReference.get());
      List<Byte[]> list=new ArrayList<>();
      // 触发gc：1.可以通过大量申请内存后系统自动触发gc 2. 通过主动调用系统函数来触发gc
      //for(int i=0;i<1000000;i++){ // 在我电脑上要写10w次内存申请才会达到
      //    list.add(new Byte[1024]);
      //}
      System.gc();
      System.out.println("afterGC:"+weakReference.get());
   }

}
