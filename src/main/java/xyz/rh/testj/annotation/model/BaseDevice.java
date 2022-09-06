/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.annotation.model;

/**
 * Created by xionglei01@baidu.com on 2022/7/30.
 */
abstract class BaseDevice {

    public String productId;

    public State state;


    enum State {
        Connected,
        Connecting,
        Disconnected
    }

    @Override public String toString() {
        return "BaseDevice{" +
            "productId='" + productId + '\'' +
            ", state=" + state +
            '}';
    }
}
