/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.algorithm.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

        //0   -->  3
        //1   -->  3  -->  5
        //1   -->  4  --> 5
        //2   -->  4  --> 5

        // 边，也可理解为先决条件。
        //int[][] prerequisites =  { {3, 0}, {3, 1}, {4, 1}, {4, 2}, {5, 3}, {5, 4}};

        String[][] edges = {{"C", "A"}, {"B", "A"},/* {"D", "C"},*/ {"D", "B"}, {"E", "C"}, {"E", "D"}, {"C", "B"}};

        //int[] res = bfs(6, prerequisites);
        //
        //for (int i: res) {
        //    System.out.println(i);
        //}

        //System.out.println("can finish : " + canFinish(6, prerequisites));
        //System.out.println("can finish : " + canFinish2(5, edges));

        Task taskA = new Task("A", 1000);
        Task taskB = new Task("B", 2000);
        Task taskC = new Task("C", 3000);
        Task taskD = new Task("D", 4000);
        Task taskE = new Task("E", 5000);



        // 任务依赖关系：{taskC，taskA} 表示 C依赖于A执行完
        Task[][] edges_task = {{taskC, taskA}, {taskB, taskA}, {taskD, taskC}, {taskD, taskB}, {taskE, taskC}, {taskE, taskD}/*, {"C", "B"}*/};
        List<Task> resultList = topoSorted_DAG(5, edges_task);
        for (Task task : resultList) {
            System.out.println("<<<<<<" + task + ">>>>>>");
            task.run();
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
     * 网上copy的。难懂，用数组来做的，真实场景不实用！
     *
     * 拓扑排序，入度表法，BFS，广度优先搜索
     * 邻接表的表示方式
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

    /**
     *  这个是视频里copy出来的，易懂但不太实用
     *  BFS入度表法，拓扑排序
     * @param N 顶点个数
     * @param edges  边数
     * @return
     */
    public static boolean canFinish(int N, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegree = new int[N];    // 1. 建图
        for (int[] edge: edges) {       // 2. 建indegree，入度数
            int end = edge[0], start = edge[1];   // 二维数组中{1, 3}代表3依赖于1， 也就是edge[1]依赖于edge[0]
            graph.computeIfAbsent(start, x -> new ArrayList<>()).add(end);
            indegree[end]++;        // [0, 1] 图的方向是从1指向0，0的入度有所增加 ， end <- start
        }
        Queue<Integer> queue = new LinkedList<>();    // 找到有向图的入口，入度数为0的点
        for (int i=0; i < N; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int count = 0;                   // BFS 拓扑排序
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            count++;
            for (int nei : graph.getOrDefault(cur, new ArrayList<>())) {
                if (--indegree[nei] == 0) {
                    queue.offer(nei);
                }
            }
        }
        return count == N;
    }


    // 自己用初步改造的
    // 自己写出来的，把task改成了string，不再用int，容易跟index搞混淆，string更容易理解
    // 真实应用时任务模型应该是一个Task类，并且入度数应该在task里的dependecy里的list中封装好了
    // 所以在项目中表示任务关系的edges二维数组很多时候是封装成一个 Task list，每个Task中封装了dependencies：List<Class<? extends Task>>
    // 这里只是常规算法题目中的函数签名，一般都是通过入参传递二维数组{{1,2} {2,4}}来自己构建有向无环图
    // 返回值： true代表是有向图无环，该任务链可行； false代表有向图有环，该任务链不可行；
    public static boolean canFinish2(int N, String[][] edges) {
        Map<String, List<String>> graph = new HashMap<>();   // 1. 建图
        for (String[] edge: edges) {       // 2. 建indegree，入度数
            String end = edge[0], start = edge[1];   // 二维数组中{1, 3}代表3依赖于1， 也就是edge[1]依赖于edge[0]
            graph.computeIfAbsent(end, x -> new ArrayList<>()).add(start);
            if (!graph.containsKey(start)) {
                graph.put(start, new ArrayList<>());
            }
        }
        // 截止目前为止，已经把graph中的所有任务节点的入度数（依赖）整合完毕，封装到了<String, List<String>>中的List里，List的size就是入度数
        // 找到有向图的入口，入度数为0的点，放入队列
        Queue<String> queue = new LinkedList<>();
        for (Map.Entry<String, List<String>> set : graph.entrySet()) {
            String key = set.getKey();
            if (set.getValue().size() == 0) { // 如果入度是0
                queue.offer(key);
            }
        }
        int count = 0;                   // BFS 拓扑排序
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            // 遍历到cur节点，把该节点删除，算是消费掉；
            graph.remove(cur);
            System.out.print(" " + cur + " ");  //////////////这里打印出来的就是BFS入度表法拓扑排序
            count++;
            for (Map.Entry<String, List<String>> entrySet : graph.entrySet()) {
                List<String> inDegrees = entrySet.getValue();
                String key = entrySet.getKey();
                // 找cur的下级节点（相邻节点），如果遍历出来的节点的入度包含cur，
                // 说明就是当前节点的下级节点，则把对应节点的入度-1。也就是把入度是cur的给remove掉
                if (inDegrees.contains(cur)) {
                    inDegrees.remove(cur);
                    if (inDegrees.size() == 0) {
                        queue.offer(key);
                    }
                }
            }
        }
        return count == N;
    }

    /**
     * 自己写的
     * DAG采用入度表法进行拓扑排序，采用真实场景的Task
     * @param N
     * @param edges
     * @return  返回拓扑排序之后的list任务列表，如果组装的任务edges存在环，则会抛出运行时异常
     */
    public static List<Task> topoSorted_DAG(int N, Task[][] edges) {
        ArrayList<Task> topoSortedList = new ArrayList(); // 保存排序后的结果
        Map<String, Task> graph = new HashMap<>();   // 保存所有task节点
        Map<String, List<String>> indegreeTable = new HashMap<>();  // 入度表: 最重要的一个数据结构

        for (Task[] edge: edges) {
            Task end = edge[0], start = edge[1];   // 二维数组中{1, 3}代表3依赖于1， 也就是edge[1]依赖于edge[0]
            // 遍历所有节点，存放到map
            if (!graph.containsKey(start)) {
                graph.put(start.getUniqueId(), start);
            }
            if (!graph.containsKey(end)) {
                graph.put(end.getUniqueId(), end);
            }
            // 建indegree，入度数  {C, A} 代表C依赖于A
            // 计算入度
            // 针对start如果之前未加入到入度表，则只做初始化即可
            if (!indegreeTable.containsKey(start.getUniqueId())) {
                indegreeTable.put(start.getUniqueId(), new ArrayList<>());
            }
            // 针对end，则需要把对应的indegree计算出来并更新
            //indegreeTable.compute(end.getUniqueId(), (s, list) -> {
            //    if (list == null) {
            //        list = new ArrayList<String>();
            //    }
            //    list.add(start.getUniqueId());
            //    return list;
            //});
            // 以下是上面注释部分的lambda写法: compute相关方法是1.8新引入的，computeIfAbsent表示如果缺席的话就执行对应的action
            indegreeTable.computeIfAbsent(end.getUniqueId(), s -> new ArrayList<>()).add(start.getUniqueId());
        }
        // 截止目前为止，已经把graph中的所有任务节点的入度数（依赖）整合完毕，封装到了<String, List<String>>中的List里，List的size就是入度数
        // 找到有向图的入口，入度数为0的点，放入队列
        Queue<String> queue = new LinkedList<>();
        for (Map.Entry<String, List<String>> set : indegreeTable.entrySet()) {
            String key = set.getKey();
            if (set.getValue().size() == 0) { // 如果入度是0
                queue.offer(key);
            }
        }
        int count = 0;                   // BFS 拓扑排序
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            // 遍历到cur节点，把该节点删除，算是消费掉；
            Task runTask = graph.remove(cur);
            topoSortedList.add(runTask); // 加入到排序结果列表
            //System.out.println("<<<<" + runTask + ">>>>");  //////////////这里打印出来的就是BFS入度表法拓扑排序
            //runTask.run();
            count++;
            for (Map.Entry<String, List<String>> tableEntrySet : indegreeTable.entrySet()) {
                List<String> indegreeList = tableEntrySet.getValue();
                String key = tableEntrySet.getKey();
                // 找cur的下级节点（相邻节点），如果遍历出来的节点的入度包含cur，
                // 说明就是当前节点的下级节点，则把对应节点的入度-1。也就是把入度是cur的给remove掉
                if (indegreeList.contains(cur)) {
                    indegreeList.remove(cur);
                    if (indegreeList.size() == 0) {
                        queue.offer(key);
                    }
                }
            }
        }
        if (count != N) {
            throw new RuntimeException("DAG has cycle!!!");
        }
        return topoSortedList;
    }





    public static List<Task> topoSorted_DAG2(int N, Task[][] edges) {
        ArrayList<Task> topoSortedList = new ArrayList(); // 保存排序后的结果
        Map<String, Task> graph = new HashMap<>();   // 保存所有task节点
        Map<String, Integer> indegreeTable = new HashMap<>();  // 入度表: 最重要的一个数据结构

        for (Task[] edge: edges) {
            Task end = edge[0], start = edge[1];   // 二维数组中{1, 3}代表3依赖于1， 也就是edge[1]依赖于edge[0]
            // 遍历所有节点，存放到map
            if (!graph.containsKey(start)) {
                graph.put(start.getUniqueId(), start);
            }
            if (!graph.containsKey(end)) {
                graph.put(end.getUniqueId(), end);
            }
            // 建indegree，入度数  {C, A} 代表C依赖于A
            // 计算入度
            // 针对start如果之前未加入到入度表，则只做初始化即可
            if (!indegreeTable.containsKey(start.getUniqueId())) {
                indegreeTable.put(start.getUniqueId(), 0);
            }
            // 针对end，则需要把对应的indegree计算出来并更新
            indegreeTable.compute(end.getUniqueId(), (s, inde) -> {
                if (inde == null) {
                    inde = new Integer(0);
                }
                inde = inde + 1;
                return inde;
            });

        }
        // 截止目前为止，已经把graph中的所有任务节点的入度数（依赖）整合完毕，封装到了<String, List<String>>中的List里，List的size就是入度数
        // 找到有向图的入口，入度数为0的点，放入队列
        Queue<String> queue = new LinkedList<>();
        for (Map.Entry<String, Integer> set : indegreeTable.entrySet()) {
            String key = set.getKey();
            if (set.getValue() == 0) { // 如果入度是0
                queue.offer(key);
            }
        }
        int count = 0;                   // BFS 拓扑排序
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            // 遍历到cur节点，把该节点删除，算是消费掉；
            Task runTask = graph.remove(cur);
            topoSortedList.add(runTask); // 加入到排序结果列表
            //System.out.println("<<<<" + runTask + ">>>>");  //////////////这里打印出来的就是BFS入度表法拓扑排序
            //runTask.run();
            count++;
            for (Map.Entry<String, Integer> tableEntrySet : indegreeTable.entrySet()) {
                Integer indegreeSize = tableEntrySet.getValue();
                String key = tableEntrySet.getKey();
                // 找cur的下级节点（相邻节点），如果遍历出来的节点的入度包含cur，
                // 说明就是当前节点的下级节点，则把对应节点的入度-1。也就是把入度是cur的给remove掉
                if (key == cur) {
                    
                    //indegreeSize.remove(cur);
                    if (indegreeSize == 0) {
                        queue.offer(key);
                    }
                }
            }
        }
        if (count != N) {
            throw new RuntimeException("DAG has cycle!!!");
        }
        return topoSortedList;
    }





}
