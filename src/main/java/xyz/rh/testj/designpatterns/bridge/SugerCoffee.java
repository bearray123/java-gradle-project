/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.designpatterns.bridge;

/**
 * Created by xionglei01@baidu.com on 2022/7/31.
 */
class SugerCoffee extends AbsCoffee {

    SugerCoffee(DefineSizeCoffee size) {
        super(size);
    }

    @Override public void cook() {
        System.out.println("add Suger...");
        super.cook();
   }
}
