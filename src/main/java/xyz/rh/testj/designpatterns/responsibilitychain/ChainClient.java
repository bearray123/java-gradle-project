/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.designpatterns.responsibilitychain;

import java.util.ArrayList;

/**
 * Created by xionglei01@baidu.com on 2022/7/31.
 */
public class ChainClient {

    InterceptorChain currentChain, startChain;


    //ArrayList<InterceptorChain> chainList = new ArrayList<>();

    public ChainClient addChain(InterceptorChain chain) {

        //chainList.add(chain);
        if (startChain == null) {
            startChain = chain;
            currentChain = chain;
        } else {
            currentChain.addChain(chain);
            currentChain = chain;
        }
        return this;

    }

    private Response getRespWithInterceptorChains(Request request) {
        return startChain.process(request);
    }


    public Response doWork(Request request) {
        // 按照责任链添加的顺序执行各个interceptor
        addChain(new FirstLayerInterceptor());
        addChain(new SecLayerInterceptor());
        addChain(new ThirLayerInterceptor());
        addChain(new FinalInterceptor());

        return getRespWithInterceptorChains(request);
    }

    public static void main(String[] args) {

        ChainClient client = new ChainClient();
        //client.addChain(new InterceptorChain() {
        //    @Override public Response process(Request request) {
        //        return null;
        //    }
        //});
        Response response = client.doWork(new Request());
        System.out.println("response ====== " + response);

    }


}
