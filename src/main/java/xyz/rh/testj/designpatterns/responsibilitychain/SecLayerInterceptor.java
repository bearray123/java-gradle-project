/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.designpatterns.responsibilitychain;

/**
 * Created by xionglei01@baidu.com on 2022/7/31.
 */
class SecLayerInterceptor extends InterceptorChain {
    @Override public Response process(Request request) {

        request.height = 100;
        System.out.println(">>>>>> SecLayerInterceptor handle request, add height =100.....request = " +  request);

        Response response = mChain.process(request);

        response.attachmentB = "filter by SecLayerInterceptor, modify response.attachmentB";
        System.out.println("<<<<<< filter by SecLayerInterceptor . set attachmentB = " + response.attachmentB);
        return response;

    }
}
