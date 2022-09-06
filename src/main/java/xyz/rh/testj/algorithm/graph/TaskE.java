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
//public class TaskE extends AbsTask {
//
//   @Override List<AbsTask> dependencies(List<AbsTask> inDegreeTask) {
//
//      // taskE 依赖于 C 和 D
//      inDegreeTask.add(new TaskC());
//      inDegreeTask.add(new TaskD());
//
//      return inDegreeTask;
//   }
//
//   @Override void execute() {
//
//      System.out.println("task E execute....");
//
//   }
//}
