/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.designpatterns.responsibilitychain;

/**
 * Created by xionglei01@baidu.com on 2022/7/31.
 */
class FirstLayerInterceptor extends InterceptorChain {
   @Override public Response process(Request request) {

      request.intelligence = 120;
      System.out.println(">>>>>> FirstLayerInterceptor handle request, add intelligence =120.....request = " +  request);

      Response response = mChain.process(request);

      response.attachmentA = "filter by FirstLayerInterceptor, modify response.attachmentA";
      System.out.println("<<<<<< filter by FirstLayerInterceptor . set attachmentA = " + response.attachmentA);
      return response;

   }
}
