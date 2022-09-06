/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.designpatterns.bridge;

/**
 * Created by xionglei01@baidu.com on 2022/7/31.
 */
class MediumCup implements DefineSizeCoffee{
   @Override public void cook() {
      System.out.println("assemble Medium cup...");

   }
}
