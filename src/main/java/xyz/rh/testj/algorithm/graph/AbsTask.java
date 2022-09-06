///*
// * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
// */
//package xyz.rh.testj.algorithm.graph;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by xionglei01@baidu.com on 2022/8/19.
// */
//public abstract class AbsTask {
//
//    // 入度task集合
//    protected List<AbsTask> inDegreeList = new ArrayList<>();
//
//    ///**s
//    // * 添加前置任务
//    // * @param preTask
//    // */
//    //protected void addDependency(AbsTask preTask) {
//    //    inDegreeList.add(preTask);
//    //}
//
//    // 入度task集合，依赖的前置task
//    abstract List<Class<? extends AbsTask>> dependencies(List<Class<? extends AbsTask>> inDegreeTask);
//
//    /**
//     * 获取执行该task所依赖的前置任务个数，也就是入度数
//     * @return
//     */
//    protected int getIndegreeDependencies() {
//        return inDegreeList.size();
//    }
//
//    // 执行该task
//    abstract void execute();
//
//}
