///*
// * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
// */
//package xyz.rh.testj.algorithm.graph;
//
//import java.util.List;
//
///**
// * Created by xionglei01@baidu.com on 2022/8/19.
// */
//public class TaskD extends AbsTask {
//
//   @Override List<AbsTask> dependencies(List<AbsTask> inDegreeTask) {
//
//      // taskD 依赖于 B 和 C
//      inDegreeTask.add(new TaskB());
//      inDegreeTask.add(new TaskC());
//
//      return inDegreeTask;
//   }
//
//   @Override void execute() {
//
//      System.out.println("task D execute....");
//
//
//   }
//}