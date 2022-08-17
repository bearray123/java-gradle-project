/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.algorithm.arrays;

import java.util.Arrays;
import java.util.HashMap;
import xyz.rh.testj.algorithm.sort.CommonMain;

/**
 * Created by xionglei01@baidu.com on 2022/8/10.
 */
class ArrayMain {

   public static void main(String[] args) {

      //int[] array = {1,4,6,9,1,-1,8,3};
      //System.out.println("选中一个中位数，使得left和right的和相等，这个中位index是：" + findMiddleIndex_ext(array));

      //int[] array2 = {1,4,6,9,1,-1,8,3};
      //System.out.println("找无序数组的两数之和是K的index是：" + Arrays.toString(findTwoSumIndex(array2, 11)));

   }

   // 找无序数组中一个index，使得index左边的和等于它右边的和
   public static int findMiddleIndex_ext(int[] array) {
      int total = 0, sum = 0;
      for(int v: array) {
         total += v;
      }
      for (int i=0; i < array.length; i++) {
         sum = sum + array[i];
         if (total - sum == sum - array[i]) {
            return i;
         }
      }
      return -1;
   }

   // 找无序数组的两数之和是K的值
   public static int[] findTwoSumIndex(int[] array , int k) {
      // 先排序
      CommonMain.bubbleSort(array);
      // 然后利用双指针技巧从两头查找
      int i=0, j=array.length -1;
      while (i < j) {
         if (array[i] + array[j] == k) {
            break;
         } else if (array[i] + array[j] > k) {
            j--;
         } else {
            i++;
         }
      }
      if (i <= j && array[i] + array[j] == k) {
         return new int[] {array[i], array[j]};
      }
      return new int[] {-1,-1};

   }

   /**
    * 找两数之和主要是考察我们对哈希表的使用
    * 哈希表查询时间复杂度是O(1)
    * @param nums
    * @param target
    * @return
    */
   public int[] findTwoSum(int[] nums, int target) {
      // 时间复杂度O(n*n)
      //for (int i = 0; i < nums.length; i++)
      //   for (int j = i + 1; j < nums.length; j++)
      //      if (nums[j] == target - nums[i])
      //         return new int[] { i, j };
      //
      //// 不存在这么两个数
      //return new int[] {-1, -1};

      HashMap<Integer, Integer> map = new HashMap();
      for (int i=0;i<nums.length; i++) {
         map.put(nums[i], i);
      }
      for (int i=0; i<nums.length;i++) {
         if (map.containsKey(target - nums[i]) && map.get(target - nums[i]) != i) {
            return new int[] {map.get(target - nums[i]), i};
         }
      }
      return new int[] {-1, -1};

   }


}
