/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.designpatterns.responsibilitychain;

/**
 * Created by xionglei01@baidu.com on 2022/7/31.
 */
public class Response {

   boolean isOK;

   String attachmentA;

   String attachmentB;

   String attachmentC;

   @Override public String toString() {
      return "Response{" +
          "isOK=" + isOK +
          ", attachmentA='" + attachmentA + '\'' +
          ", attachmentB='" + attachmentB + '\'' +
          ", attachmentC='" + attachmentC + '\'' +
          '}';
   }
}
