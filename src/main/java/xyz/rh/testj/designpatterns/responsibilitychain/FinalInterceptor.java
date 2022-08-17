/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.designpatterns.responsibilitychain;

/**
 * Created by xionglei01@baidu.com on 2022/7/31.
 */
public class FinalInterceptor extends InterceptorChain{
   @Override public Response process(Request request) {
      request.output = 9999;
      System.out.println(">>>>>> FinalInterceptor handle request, add output =9999.....request = " +  request);
      Response response = new Response();
      response.isOK = true;

      System.out.println();

      System.out.println("<<<<<< filter by FinalInterceptor . GET the response ------ new Response(), set isOK");


      return response;
   }
}
