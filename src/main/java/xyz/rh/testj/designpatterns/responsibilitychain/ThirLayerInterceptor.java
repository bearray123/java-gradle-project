/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.designpatterns.responsibilitychain;

/**
 * Created by xionglei01@baidu.com on 2022/7/31.
 */
class ThirLayerInterceptor extends InterceptorChain {
    @Override public Response process(Request request) {
        request.ownerAge = 35;
        System.out.println(">>>>>> ThirLayerInterceptor handle request, add ownerAge =35.....request = " +  request);

        Response response = mChain.process(request);

        response.attachmentC = "filter by ThirLayerInterceptor, modify response.attachmentC";
        System.out.println("<<<<<< filter by ThirLayerInterceptor . set attachmentC = " + response.attachmentC);
        return response;
    }
}
