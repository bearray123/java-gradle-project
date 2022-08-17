/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.algorithm.link;

import xyz.rh.testj.algorithm.structure.ListNode;

/**
 * Created by xionglei01@baidu.com on 2022/8/11.
 */
class LinkMain {


   //1,5,6
   //2,8,3,9, 9, 9
   //
   //
   //    1,5,6,2,9,9,9
   //    2,8,3,7
   /**
    * 用链表 计算两数之和：
    * LeetCode执行用时：
    * 1 ms, 在所有 Java 提交中击败了100.00%的用户
    * 内存消耗：41.7 MB, 在所有 Java 提交中击败了21.42%的用户
    * 通过测试用例：1568 / 1568
    */
   public static ListNode addTwoSum(ListNode l1, ListNode l2) {
      ListNode head1 = l1, head2=l2;
      ListNode tail1 = null, tail2=null;
      if (l1 == null) {
         return l2;
      }
      if (l2 == null) {
         return l1;
      }

      int flag = 0;
      int sum = 0;
      while (head1 != null && head2 != null) {
         sum = head1.val + head2.val + flag;
         if (sum > 9) {
            flag = 1;
            head1.val = sum % 10;
         } else {
            flag = 0;
            head1.val = sum;
         }
         tail1 = head1;
         tail2 = head2;
         head1 = head1.next;
         head2 = head2.next;
      }
      // 遍历完成后 head1 或 head2 指向的是tail
      //跳出循环后L1和L2至少有一个已经遍历完成；
      if(head1 == null && head2 == null) { // L1 == L2
         if(flag == 1) {
            tail1.next = new ListNode(1);
         }
      }
      if (head1 != null && head2 == null) { // L1 > L2
         while (head1 != null) {
            sum = head1.val + flag;
            if (sum > 9) {
               head1.val = 0;
               flag = 1;
            } else {
               head1.val = sum;
               flag = 0;
            }
            tail1.next = head1;
            tail1 = tail1.next;
            head1 = head1.next;
         }
         if (flag == 1) {
            tail1.next = new ListNode(1);
         }
      }

      if (head1 == null && head2 != null) { // L1 < L2
         while (head2 != null) {
            sum = head2.val + flag;
            if (sum > 9) {
               head2.val = 0;
               flag = 1;
            } else {
               head2.val = sum;
               flag = 0;
            }
            tail1.next = head2;
            tail1 = tail1.next;
            head2 = head2.next;
         }
         if (flag == 1) {
            tail1.next = new ListNode(1);
         }
      }
      return l1;
   }


}



