/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.algorithm.sort;

import xyz.rh.testj.algorithm.structure.ListNode;

/**
 * Created by xionglei01@baidu.com on 2022/7/15.
 */
public class CommonMain {


   // 二分查找， copy from SparseArray, Arrays
   public static int binarySearch(int[] array, int size, int value) {
      int lo = 0;
      int hi = size - 1;

      while (lo <= hi) {
         final int mid = (lo + hi) >>> 1;
         final int midVal = array[mid];

         if (midVal < value) {
            lo = mid + 1;
         } else if (midVal > value) {
            hi = mid - 1;
         } else {
            return mid;  // value found
         }
      }
      return ~lo;  // value not present
   }

   //2, 8, 3, 0, 16, 4, 1, 60, 7
   //i                         j
   // 9, 2, 18, 89, 10, 56, 3, 4, 99, 17, 78
   // 9, 2, '4', 89, 10, 56, 3, '18', 99, 17, 78
   // 9, 2, '4', "3", 10, 56, "89", '18', 99, 17, 78

   // '''10''', 2, '4', "3", '''9''', 56, "89", '18', 99, 17, 78

   // 快速排序：分治思想，递归
   public static void quickSort(int[] array, int start, int end) {
      // 基点从开始点算，最初是0
      int base = start;
      if (start > end ) {
         return;
      }
      int i = start, j = end;
      while (i < j) {
         // 必须从右边先遍历
         // 找到right比base小的点
         while (array[j] >= array[base] && i < j) {
            j--;
         }
         // 找到left比base大的点
         while(array[i] <= array[base] && i < j) {
            i++;
         }
         // 此时如果i<j，说明找到了左右需要交换的点
         if (i < j) {
            // 交换left与right节点
            swap(array, i, j);
         }
      }
      // 当调出外层while循环后，需要把j和基点进行交换
      swap(array, j, base);
      // 在base和j交换后，对base的左边进行分治
      quickSort(array, start, j - 1);
      // 对base的右边进行分治
      quickSort(array, j + 1, end);
   }

   public static void swap(int[] arr, int start, int end) {
      // 交换left与right节点
      int temp;
      temp = arr[start];
      arr[start] = arr[end];
      arr[end] = temp;
   }

   /**
    * 冒泡排序
    * @param arr
    * @return
    */
   public static void bubbleSort(int[] arr) {
      for(int i=0; i<arr.length-1; i++) {
         boolean isSorted = true;
         for(int j=0; j<arr.length-i-1; j++) {
            if (arr[j] > arr[j+1]) {
               swap(arr,j, j+1);
               isSorted = false;
            }
         }
         // 一轮下来如果是有序的，说明根本不需要再排序了，直接退出
         if (isSorted) {
            break;
         }
      }
   }

   /**
    * 找出给定数组中最大的k个数： 可根据冒泡排序的思想来做，一轮冒泡后最大的数就跑到了最末尾
    * @param arr
    * @param k
    * @return
    */
   public static int[] numberMaxPeek(int[] arr, int k) {

      // 冒k次泡泡后，最大的k个数其实已经在数组末尾了
      for(int i=0; i<k; i++) {
         boolean isSorted = true;
         for(int j=0; j<arr.length-i-1; j++) {
            if (arr[j] > arr[j+1]) {
               swap(arr,j, j+1);
               isSorted = false;
            }
         }
         if (isSorted) {
            break;
         }
      }
      return arr;

   }

   // 链表排序
   public static void quickSortList(ListNode head) {

   }

   public static void main(String[] args) {

      int[] array = { 5, 6, 8, 4, 3, 9, 17, 2, 10};
      // 冒泡
      //bubbleSort(array);

      // 快排
      quickSort(array, 0, array.length-1);
      for (int i : array) {
         System.out.print(" " + i);
      }


   }

}
