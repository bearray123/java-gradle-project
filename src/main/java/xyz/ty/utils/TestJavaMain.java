/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.ty.utils;

import xyz.ty.kt.utils.GameManager;
import xyz.ty.kt.utils.Player;

import static xyz.ty.kt.utils.UtilsKt.log;

/**
 * Created by xionglei01@baidu.com on 2022/5/5.
 */
class TestJavaMain {

    public static void main(String[] args) {
        log("haha, gradle");
        //fuck(null);

        Player player1 = new Player("dodo");
        player1.setAge(18);
        player1.openGame();

        GameManager instance = GameManager.INSTANCE;
    }



}

