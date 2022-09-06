/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.annotation.devices;

/**
 * Created by xionglei01@baidu.com on 2022/7/28.
 */
interface IDevice {

    void onCreate();

    void onStateChanged(State state);

    void onFoucsChangled(boolean focusOn);

    void onDestroy();

    enum State {
        CONNECTED,
        CONNECTING,
        DISCONNECT
    }

}
