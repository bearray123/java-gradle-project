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
//public class TaskC extends AbsTask {
//
//   @Override List<AbsTask> dependencies(List<AbsTask> inDegreeTask) {
//      inDegreeTask.add(new TaskA()); // C 依赖于 A
//      return inDegreeTask;
//   }
//
//   @Override void execute() {
//
//      System.out.println("task C execute....");
//
//
//   }
//}