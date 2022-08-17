/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.algorithm.tree;

import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.locks.LockSupport;
import xyz.rh.testj.algorithm.structure.TreeNode;

/**
 * Created by xionglei01@baidu.com on 2022/7/16.
 */
class TreeMain {


    public static void arrayToTreeNode(TreeNode node, int index, int[] treeArray, int n) {
        if (index >= n || node == null) return;
        int left = 2 * index + 1, right = left + 1;
        if (left < n)
            node.left = treeArray[left] == -1 ? null : new TreeNode(treeArray[left]);
        if (right < n)
            node.right = treeArray[right] == -1 ? null : new TreeNode(treeArray[right]);
        arrayToTreeNode(node.left, left, treeArray, n);
        arrayToTreeNode(node.right, right, treeArray, n);
    }


   public static void main(String[] args) {

            //       0
            //   1         2
            //3     4   5     6
            //     7      8
            //          9   10
       //前序遍历： 0 ，1， 3， 4， 7,  2， 5， 8， 9， 6
       //中序遍历： 3， 1， 7，4， 0， 5， 8， 9， 2， 6
       //后序遍历： 3， 7， 4， 1， 9，8， 5， 6， 2， 0

      TreeNode root = new TreeNode(0);

      TreeNode node1 = new TreeNode(1);
      TreeNode node2 = new TreeNode(2);
      root.left = node1;
      root.right = node2;

      TreeNode node3 = new TreeNode(3);
      TreeNode node4 = new TreeNode(4);
      node1.left = node3;
      node1.right = node4;

      TreeNode node5 = new TreeNode(5);
      TreeNode node6 = new TreeNode(6);
      node2.left = node5;
      node2.right = node6;

      TreeNode node7 = new TreeNode(7);
      TreeNode node8 = new TreeNode(8);
      TreeNode node9 = new TreeNode(9);
      TreeNode node10 = new TreeNode(10);

      node4.left = node7;
      node5.right = node8;
      node8.left = node9;
      node8.right = node10;


      int[] a = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

      TreeNode rootfroma = new TreeNode(1);
      arrayToTreeNode(rootfroma,0, a, a.length);
       //btreeToLinkList(root);
       //bfs_ext1(root);
       //bfs_ext2(root);
       bfs_ext2_opt(root);



       //breadthTravasal(rootfroma);

      //int[] attr = {1,2,3,4,5,6,7,8};

       //System.out.println("先序遍历：");
       //firstOrderTraverse(root);
       //System.out.println();
       //firstOrderTraverseByStack(root);


       //System.out.println("\n中序遍历：");
       //middleOrderTraverse(root);
       //System.out.println();
       //middleOrderTraverseByStack(root);


       //System.out.println("\n后序遍历：");
       //afterOrderTraverse(root);
       //System.out.println();
       //afterOrderTraverseByStack(root);


       //breadthTravasal(root);
       //
       //int maxDeepth = maxDeepth(root);
       //System.out.println("最大深度： " + maxDeepth);

   }

   // 先序遍历：递归实现
   public static void firstOrderTraverse(TreeNode root) {
       if (root == null) {
           return;
       }
       System.out.print(root.val + ", ");
       firstOrderTraverse(root.left);
       firstOrderTraverse(root.right);

   }

   // 中序遍历：递归实现
    public static void middleOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        middleOrderTraverse(root.left);
        System.out.print(root.val + ", ");
        middleOrderTraverse(root.right);
    }

    // 后序遍历：递归实现
   public static void afterOrderTraverse(TreeNode root) {
       if (root == null) {
           return;
       }
       afterOrderTraverse(root.left);
       afterOrderTraverse(root.right);
       System.out.print(root.val + ", ");
   }

    // 先序遍历：借用栈中转实现
   public static void firstOrderTraverseByStack(TreeNode root) {
       Stack<TreeNode> stack = new Stack();
       stack.push(root);
       while(!stack.empty()) {
            TreeNode curr = stack.pop();
            System.out.println(curr.val);
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
       }
   }

    /**
     * 中序遍历二分搜索树（非递归），用的是栈
     * @param root
     */
   public static void middleOrderTraverseByStack(TreeNode root) {
      Stack<TreeNode> stack = new Stack<>();
       TreeNode currentNode = root;
          while (currentNode != null || !stack.isEmpty()) {
               // 一直循环到二叉排序树最左端的叶子结点（currentNode是null）
               while (currentNode != null) {
                       stack.push(currentNode);
                       currentNode = currentNode.left;
                   }
               currentNode = stack.pop();
               System.out.print(currentNode.val + " ");
               currentNode = currentNode.right;
          }
   }

    /**
      * 后序遍历二分搜索树（非递归），用的是栈
      * @param root
      */
    public static void afterOrderTraverseByStack(TreeNode root) {
        //LinkedList<Node<E>> stack = new LinkedList<Node<E>>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = root;
        TreeNode rightNode = null;
        while (currentNode != null || !stack.isEmpty()) {
             // 一直循环到二叉排序树最左端的叶子结点（currentNode是null）
             while (currentNode != null) {
                     stack.push(currentNode);
                     currentNode = currentNode.left;
             }
             currentNode = stack.pop();
             // 当前结点没有右结点或上一个结点（已经输出的结点）是当前结点的右结点，则输出当前结点
             while (currentNode.right == null || currentNode.right == rightNode) {
                System.out.print(currentNode.val + " ");
                 rightNode = currentNode;
                 if (stack.isEmpty()) {
                     return; //root以输出，则遍历结束
                 }
                 currentNode = stack.pop();
             }
            stack.push(currentNode); //还有右结点没有遍历
            currentNode = currentNode.right;
        }
   }


    /**
     * 广度优先遍历 非递归（自己写出来的）
     * 队列实现
     * LinkedList 即可实现栈也可实现队列
     *  LinkedList当作栈来用 ，就用push和pop等方法
     *  LinkedList当作队列来用，就用offer和poll等方法
     * @param root
     */
   public static void breadthTravasal(TreeNode root) {

       LinkedList<TreeNode> queue = new LinkedList();

       if (root != null) {
           queue.offer(root);
       }
       while (!queue.isEmpty()) {
           TreeNode node = queue.poll();
           System.out.println(node.val);
           if (node.left != null) {
                queue.offer(node.left);
           }
           if (node.right != null) {
               queue.offer(node.right);
           }
       }

   }

    /**
     * 深度优先遍历
     * 使用栈
     * @param root
     */
   public static void deepthTravasal(TreeNode root) {
       LinkedList<TreeNode> stack = new LinkedList<>();
       if (root != null) {
           stack.push(root);
       }
       while (!stack.isEmpty()) {
           TreeNode node = stack.pop();
           System.out.println(node.val);
           if (node.left != null) {
               stack.push(node.left);
           }
           if (node.right != null) {
               stack.push(node.right);
           }
       }
   }

    /**
     * 求最大深度
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        int deep_l = 0;
        int deep_r = 0;
        if (root == null) {
            return 0;
        }
        deep_l = maxDepth(root.left);
        deep_r = maxDepth(root.right);
        return (deep_l > deep_r ? deep_l : deep_r) + 1;
    }


    public static int maxDeepth(TreeNode root) {
        // 以下代码copy网上，目前还没完全看懂
        if (root == null)
            return 0;

        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int depth = 0;  //表示二叉树的深度
        int count = 0;  //表示已从队列中取出当前层的个数
        int amount = 1; //表示当前节点的兄弟节点个数

        while (queue.size() != 0) {
            TreeNode top = queue.poll();
            count++;
            if (top.left != null) {
                queue.add(top.left);
            }

            if (top.right != null) {
                queue.add(top.right);
            }

            if (count == amount) {
                amount = queue.size();
                count = 0;
                depth++;
            }
        }
        return depth;
    }


    //        0
    //   1          2
    //3     4    5     6
    //     7      8
    //              9
    public static void btreeToLinkList(TreeNode root) {

        if (root == null) {
            return;
        }
        // 首先是先序遍历，那么就得把先序遍历时的节点缓存下来，存到栈帧里，以供后面出栈时使用
        // 递归之前先赋值保存，逻辑上就先序遍历
        TreeNode p = root;
        TreeNode left = root.left;
        TreeNode right = root.right;

        btreeToLinkList(root.left);

        btreeToLinkList(root.right);

        // JVM虚拟机栈：开始出栈

        if (left != null) {
            p = root.left;
            while (p.right != null) {
                p = p.right;
            }
            root.left = null;
            root.right = left;
            p.right = right;
        }

    }


    ///
    //          1
    //     2         3
    //  4     5    6     7
    //8   9      10  11
    /////
    //队列  1
    //栈
    //
    //1    -》 1
    //2 3   -》2
    //3 4 5  -》 3
    //4 5 6 7  -》 4
    //5 6 7 8 9  -》 5
    //6 7 8 9  -》 6
    //7 8 9 10 11  -》7 ，8 ，9 ，10， 11

    /**
     * 把二叉树按照层序遍历，每一层打印完毕后换行
     * @自己写的
     * @param root
     */
    public static void bfs_ext1(TreeNode root) {
        //       0
        //   1         2
        //3     4   5     6
        //     7      8
        //          9   10
        LinkedList<TreeNode> queueList = new LinkedList<>();
        queueList.offer(root);
        int nextLayerSize = 0;
        int currentLayerRemainSize = 1;
        while (!queueList.isEmpty()) {

            TreeNode node = queueList.poll();
            currentLayerRemainSize--;
            System.out.print(node.val + "  ");
            if (node.left != null) {
                queueList.offer(node.left);
                nextLayerSize++;
            }
            if (node.right != null) {
                queueList.offer(node.right);
                nextLayerSize++;
            }
            if (currentLayerRemainSize == 0) { // size = 0 说明本层所有节点处理完毕
                System.out.println();
                currentLayerRemainSize = nextLayerSize;
                nextLayerSize = 0;
            }
        }
    }


    /**
     * 把二叉树按照层序遍历，分别按照一层正序，一层逆序打印出来，打印一层完毕后换行
     * @自己写的
     * @param root
     */
    public static void bfs_ext2(TreeNode root) {
        //       0
        //   1         2
        //3     4   5     6
        //     7      8
        //          9   10
        LinkedList<TreeNode> queueList = new LinkedList<>();
        LinkedList<TreeNode> printList = new LinkedList<>();
        boolean order = true; // true 表示当前轮是按正序，false表示当前轮是按逆序
        queueList.offer(root);
        int nextLayerSize = 0;
        int currentLayerRemainSize = 1;
        while (!queueList.isEmpty()) {

            TreeNode node = queueList.poll();
            currentLayerRemainSize--;

            if (order) { // 如果是正序，入队列，后面出队列打印
                printList.offer(node);
            } else { // 如果是逆序，入栈，后面统一出栈打印
                printList.push(node);
            }
            //System.out.print(node.val + "  ");
            if (node.left != null) {
                queueList.offer(node.left);
                nextLayerSize++;
            }
            if (node.right != null) {
                queueList.offer(node.right);
                nextLayerSize++;
            }
            if (currentLayerRemainSize == 0) { // size = 0 说明本层所有节点处理完毕
                if (order) {
                    while (!printList.isEmpty()) {
                        TreeNode printNode = printList.poll();
                        System.out.print(printNode.val + " ");
                    }
                    System.out.println(); // 本层打印完毕，换行
                } else {
                    while (!printList.isEmpty()) {
                        TreeNode printNode = printList.pop();
                        System.out.print(printNode.val + " ");
                    }
                    System.out.println(); // 本层打印完毕，换行
                }
                order = !order;
                currentLayerRemainSize = nextLayerSize;
                nextLayerSize = 0;
            }
        }
    }

    public static void bfs_ext2_opt(TreeNode root) {
        //       0
        //   1         2
        //3     4   5     6
        //     7      8
        //          9   10
        LinkedList<TreeNode> queueList = new LinkedList<>();
        //LinkedList<TreeNode> printList = new LinkedList<>();
        boolean order = true; // true 表示当前轮是按正序，false表示当前轮是按逆序
        queueList.offer(root);
        int nextLayerSize = 0;
        int currentLayerRemainSize = 1;
        while (!queueList.isEmpty()) {

            TreeNode node = queueList.poll();
            System.out.print(node.val + " ");
            currentLayerRemainSize--;

            if (order) { // 如果当前层是顺序打印，则下层节点需要按照逆序来添加
                if (node.right != null) {
                    queueList.offer(node.right);
                    nextLayerSize++;
                }
                if (node.left != null) {
                    queueList.offer(node.left);
                    nextLayerSize++;
                }
            } else {
                if (node.left != null) {
                    queueList.offer(node.left);
                    nextLayerSize++;
                }
                if (node.right != null) {
                    queueList.offer(node.right);
                    nextLayerSize++;
                }
            }

            if (currentLayerRemainSize == 0) { // size = 0 说明本层所有节点处理完毕
                order = !order;
                currentLayerRemainSize = nextLayerSize;
                nextLayerSize = 0;
                System.out.println();
            }
        }
    }


}
