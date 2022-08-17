/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.algorithm.tree;

import xyz.rh.testj.algorithm.structure.TreeNode;
import xyz.rh.testj.algorithm.tree.TreeMain;

/**
 *
 * // - 将二叉树拆成链表,先序遍历,用右指针作为next指针;
 * Created by xionglei01@baidu.com on 2022/8/7.
 */
class FlattenTree {

    // 上一操作结点，全局变量
    static TreeNode lastNode = null;


         //         0
         //    1         2
         //3      4    5    6

    // 先序：0  1  3  4  2  5  6
    // 先序假链表：
    //     0
    //        1
    //           3
    //              4
    //                 2
    //                    5
    //                       6

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;

        flatten(root);

        // 先序遍历打印节点
        TreeMain.firstOrderTraverseByStack(root);

    }


    ///////////自己写的，还是错的
    public static void flatten(TreeNode root) {
        // root为空时直接返回，不做任何操作
        if (root == null) {
            return;
        }
        // 缓存leftright节点
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (left != null) {
            root.left = null;
            root.right = left;

        }


        flatten(left);
        flatten(right);


    }


    //public static void flatten(TreeNode root) {
    //    // root为空时直接返回，不做任何操作
    //    if (root == null) {
    //        return;
    //    }
    //
    //    // 这个 if 条件是进入左子树和右子树才开始执行
    //    if (lastNode != null) {
    //        lastNode.left = null;
    //        //  这个 root 为当前递归层的根结点
    //        lastNode.right = root;
    //    }
    //
    //    // 移动 lastNode，此时问题转化为下一轮递归
    //    lastNode = root;
    //    TreeNode right = root.right;
    //    // 多层递归把左边完全拆完才开始右边
    //    flatten(root.left);
    //    /* 下面这个地方不能写成 root.right ,比如一开始 lastnode 为根结点
    //     * 然后对根结点左子树递归，在下一层递归调用中
    //     * 如果满足 `if (lastNode != null)`将 lastnode 的左右孩子都重新赋值
    //     * 那么递归结束回到当前层的时候，root.right 已经不是原来的右子树了
    //     */
    //    flatten(right);
    //}

}
