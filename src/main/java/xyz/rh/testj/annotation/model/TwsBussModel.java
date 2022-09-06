/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.annotation.model;

import xyz.rh.testj.annotation.api.BindDevice;

/**
 * Created by xionglei01@baidu.com on 2022/7/30.
 */
public class TwsBussModel {

    @BindDevice(productId = "JJFfdkkeiociEI23JCfnfeiakj")
    private BaseDevice mDevice;


    public void doTranslate() {

    }

    public void doAsr() {

        System.out.println("doAsr , device=" + mDevice);

    }

    public void doNaviMap() {

    }

    public void doPlayingMusic() {

    }

    public static void main(String[] args) {
        TwsBussModel twsBussModel = new TwsBussModel();
        twsBussModel.doAsr();
    }


}
