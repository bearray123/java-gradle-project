/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import xyz.rh.kt.GameManager;
import xyz.rh.kt.Player;

import static xyz.rh.kt.utils.UtilsKt.log;

/**
 * Created by xionglei01@baidu.com on 2022/5/5.
 */
class TestJavaMain {

    public static void main(String[] args) {
        log("haha, gradle");
        //fuck(null);

        Player player1 = new Player("dodo", 7);
        player1.setAge(18);
        player1.openGame();

        GameManager instance = GameManager.INSTANCE;

    }



}

