/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.algorithm.structure;

/**
 * Created by xionglei01@baidu.com on 2022/7/16.
 */
public class TreeNode {
      public int val;
      public TreeNode left;
      public TreeNode right;
      public TreeNode() {}
      public TreeNode(int val) { this.val = val; }
      public TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }

    @Override public String toString() {
        return "val=" + val;
    }
}
