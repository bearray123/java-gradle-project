/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.designpatterns.responsibilitychain;

/**
 * Created by xionglei01@baidu.com on 2022/7/31.
 */
public abstract class InterceptorChain {

    protected InterceptorChain mChain;

    public void addChain(InterceptorChain chain) {
        this.mChain = chain;
    }

    /**
     * 前置操作
     * process（interceptor）
     * 后置操作
     * @param request
     */
    public abstract Response process(Request request);

}
