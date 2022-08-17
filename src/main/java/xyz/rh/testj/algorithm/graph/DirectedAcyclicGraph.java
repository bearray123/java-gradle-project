/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.algorithm.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * DAG：有向无环图
 *
 *  有向无环图的两种解法，入度表法(BFS)和 DFS 法。其中，入度表法很重要，必须掌握
 *
 * Created by xionglei01@baidu.com on 2022/8/17.
 */
class DirectedAcyclicGraph {

    public static void main(String[] args) {

        int n = 6;
        int[][] prerequisites =  { {3, 0}, {3, 1}, {4, 1}, {4, 2}, {5, 3}, {5, 4} };

        int[] res = bfs(6, prerequisites);

        for (int i: res) {
            System.out.println(i);
        }

    }


    /*如何构建一个有向无环图
    这里我们采用 BFS 方法实现，算法思想大概是这样的

        1. 建立入度表，入度为 0 的节点先入队
        2. 当队列不为空，进行循环判断
            1)节点出队，添加到结果 list 当中
            2)将该节点的邻居入度减 1
            3)若邻居课程入度为 0，加入队列

        3. 若结果 list 与所有节点数量相等，则证明不存在环。否则，存在环*/


    /**
     * 拓扑排序，入度表法，BFS，广度优先搜索
     * @param num
     * @param prerequisites
     * @return
     */
    public static int[] bfs(int num, int[][] prerequisites) {

        // 计算所有节点的入度，这里用数组代表哈希表，key 是 index， value 是 inDegree[index].实际开发当中，用 HashMap 比较灵活
        int[] inDegree = new int[num];
        for (int[] array : prerequisites) {
            inDegree[array[0]]++;
        }

        // 找出所有入度为 0 的点，加入到队列当中
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer key = queue.poll();
            result.add(key);
            // 遍历所有课程
            for (int[] p : prerequisites) {
                // 改课程依赖于当前课程 key
                if (key == p[1]) {
                    // 入度减一
                    inDegree[p[0]]--;
                    if (inDegree[p[0]] == 0) {
                        queue.offer(p[0]); // 加入到队列当中
                    }
                }
            }
        }

        // 数量不相等，说明存在环
        if (result.size() != num) {
            return new int[0];
        }

        int[] array = new int[num];
        int index = 0;
        for (int i : result) {
            array[index++] = i;
        }

        return array;
    }
}
