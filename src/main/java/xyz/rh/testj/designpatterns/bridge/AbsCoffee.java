/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.designpatterns.bridge;

/**
 * Created by xionglei01@baidu.com on 2022/7/31.
 */
abstract class AbsCoffee implements ICoffee{

   // 组合杯大小
   DefineSizeCoffee sizeCoffee;

   AbsCoffee(DefineSizeCoffee size) {
      this.sizeCoffee = size;
   }


   @Override public void cook() {
      sizeCoffee.cook();
   }

   public static void main(String[] args) {

   }

}
