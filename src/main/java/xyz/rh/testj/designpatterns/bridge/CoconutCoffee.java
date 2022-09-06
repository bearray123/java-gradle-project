/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.designpatterns.bridge;

/**
 * 椰汁
 * Created by xionglei01@baidu.com on 2022/7/31.
 */
class CoconutCoffee extends AbsCoffee{

    CoconutCoffee(DefineSizeCoffee size) {
        super(size);
    }

    @Override public void cook() {
        System.out.println("add Coconut...");
        super.cook();

    }
}
