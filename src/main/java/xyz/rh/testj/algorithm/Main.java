/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.algorithm;

import java.util.HashMap;
import java.util.Stack;
import xyz.rh.testj.algorithm.sort.CommonMain;
import xyz.rh.testj.algorithm.structure.ListNode;

/**
 * Created by xionglei01@baidu.com on 2022/7/13.
 */
class Main {

   public static void main(String[] args) {

       //ListNode head = new ListNode(1);
      //head.buildNext(2)
      //    .buildNext(3)
      //    .buildNext(4)
      //    .buildNext(5);

      //int[] arr = {1,2,3,4,5,6,7,8,9};
      //ListNode head = ListNode.buildListNodeFromArray(arr);


      //while(head != null) {
      //   xlog(head.val);
      //   head = head.next;
      //}

      //xlog("print head = " + head);

      //int[] arr1 = {2,4,3};
      //int[] arr2 = {5,6,6,9};
      //ListNode sumHead = addTwoSum(ListNode.buildListNodeFromArray(arr1), ListNode.buildListNodeFromArray(arr2));

       //int[] arr1 = {8,9};
       //int[] arr = {1,2,3,4,5,6,7,8,9};
       //int left = 1, right=2;
       //ListNode reverHead = reverseBetween(ListNode.buildListNodeFromArray(arr1), left, right);


      // int[] arr1 = {1,2};
      // ListNode reverHead = removeNthFromEnd(ListNode.buildListNodeFromArray(arr1), 1);
      //
      //while(reverHead != null) {
      //   xlog(reverHead.val);
      //    reverHead = reverHead.next;
      //}

       int[] arr1 = {89, 2, 18, 9, 10, 56, 3, 4, 99, 17, 78};

       CommonMain.quickSort(arr1,0, arr1.length-1);
       for (int i=0;i<arr1.length; i++) {
           System.out.print(arr1[i] + "  ");
       }



   }


   //               1   ->   2   ->   3   ->   4   -> 5
   //head/begin      mid      end
   //
   //               1   ->   2   ->   3   ->   4   -> 5
   //head/begin   <- mid      end

   public ListNode reverseListNode(ListNode head) {
      ListNode mid = null, begin = null, end = null;
      if (head == null || head.next == null) {
         return head;
      }

      begin = null;
      mid = head;
      while (mid != null) {

        end = mid.next;
        mid.next = begin;
        begin = mid;
        mid = end;

      }
      return begin;
   }

   //3,4
   //1 -> 2 -> 3  ->  4  ->  5   ->   6  ->   7
   //     pl   tmpL    pr   tmpR
   //     pre  start        end
   // left : 3  right: 5


    // 翻转链表中的第left到第right节点
    //LeetCode 执行结果：通过
    //执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：39.7 MB, 在所有 Java 提交中击败了5.02%的用户
    //通过测试用例：44 / 44
   public static ListNode reverseBetween(ListNode head, int left, int right) {
       if (left == right) {
           return head;
       }
       Stack<ListNode> stack = new Stack();

       ListNode start = head, end = head, pre=head, last=head;
       for (int i=0; i< left-1; i++) {
           pre = start;
           start = start.next;
       }
       boolean includeStart = false , includeEnd = false;
       if (pre == start) {
           includeStart = true;
       }
       for (int i=0; i< right-1; i++) {
           end = end.next;
       }

       if (end.next == null) {
           includeEnd = true;
       } else {
           last = end.next;
       }

       while(start != end.next) {
            stack.push(start);
            start = start.next;
       }
       ////////////核心思想，使用栈//////////////
       ListNode stackHead = null, stackTail= null; // 从栈弹出的新链表；
       while (!stack.empty()) {
           ListNode node = stack.pop();
           if (stackHead == null) {
               stackHead = node;
               stackTail = node;
           } else {
               stackTail.next = node;
               stackTail = stackTail.next;
           }
       }
       stackTail.next = null; // 这句不能少，因为链表之间的引用并没有断开，如果不把出栈构成的链表队尾制空，会造成循环链表了
       ///////////////////////////////
       if (includeStart && includeEnd) {
            return stackHead;
       }
       if (includeStart && !includeEnd) {
           stackTail.next = last;
           return stackHead;
       }
       if (!includeStart && includeEnd) {
           pre.next = stackHead;
           return head;
       }
       if (!includeStart && !includeEnd) {
            pre.next = stackHead;
            stackTail.next = last;
            return head;
       }
       return head;

   }

    //1-> 2 -> 3 -> 4 -> 5 -> 6
    // 1，2  ： 1

    // 删除链表的倒数第 N 个结点
    // LeetCode结果：通过
    // 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
    // 内存消耗：39.8 MB, 在所有 Java 提交中击败了15.71%的用户
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode curr = head;
        Stack<ListNode> stack = new Stack<>();
        int size =0;
        // 先把所有链表节点入栈；
        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
            size++;
        }
        ListNode stackHead = null, stackTail = null;
        // 针对栈，把要删除的元素遍历到栈顶
        for(int i=0; i<n-1;i++) {
            ListNode node = stack.pop();
            if (stackHead == null) {
                stackHead = node;
                stackTail = stackHead;
            } else {
                node.next = stackHead;
                stackHead = node;
            }
        }
        // 通过栈删除该元素
        stack.pop();
        // 再把栈中剩余的元素出栈，然后接上链表
        for (int i=0; i < size-n;i++) {
            ListNode node = stack.pop();
            if (stackHead == null) {
                stackHead = node;
                stackTail = stackHead;
            } else {
                node.next = stackHead;
                stackHead = node;
            }
        }
        // 这句不能少，因为链表之间的引用并没有断开
        if (stackTail != null) {
            stackTail.next = null;
        }
        return stackHead;

    }

    // 判断链表是否存在环
    // Leetcode 执行通过
    public static boolean hasCycle(ListNode head) {

        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;

        // 以下也可以通过，有点复杂
        //ListNode fast = head, slow = null;
        //if (head == null || head.next == null) { // 链表为空 或 只有一个节点
        //    return false;
        //}
        //while (fast != null && fast != slow) { // 有环会退出、fast到尾部了也会退出
        //    if (slow == null) {
        //        slow = fast;
        //    }
        //    if(fast.next != null) {
        //        fast = fast.next.next;
        //    } else {
        //        break;
        //    }
        //    slow = slow.next;
        //}
        //if (fast == null || fast.next == null) {
        //    return false;
        //} else {
        //    return true;
        //}
    }



}
