/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.algorithm;

/**
 *
 * 深入理解递归思想的运作
 * Created by xionglei01@baidu.com on 2022/8/7.
 */
class RecursiveUnderstandMain {

   public static void main(String[] args) {
       testacb(10);
   }

   ///// 理解递归到底是怎么运作的：递归真正的本质是利用栈的性质，函数的调用就是压栈和出栈的过程
   //// 最好是采用debug，看线程的栈，也就是jvm虚拟机栈，每一层调用都有一个栈帧
   public static int testacb(int i) {
      ///////////////// 以下打印在入栈时调用
      String store_i = "store_i is " + i;
      if (i == 0) {
         return 0;
      }
      System.out.println("入栈前 : print i = " + i);
      //////////////////////////////////////
      int ret = testacb(--i);
      //////////////////// 以下打印在出栈后调用
      System.out.println("出栈后 : print i = " + i + ", store_i ==" + store_i + ", ret = " + ret);

      return i + 1000;

      // store_i 和 i这些函数内的局部变量其实都会存放到栈帧里，ret是在函数出栈前（执行testacb后半部分）时才会保存到栈帧上，当函数执行完后就完成了出栈操作
      // 所以最后开始挨个出栈时是都可以拿到这些局部变量的，相当于栈起了一个保存的作用，
      // 这也应该就是闭包可以保存运行时变量的一个原因把

   }

}
