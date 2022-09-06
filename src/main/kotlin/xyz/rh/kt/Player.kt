/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.kt

import xyz.rh.kt.utils.xlog
import xyz.rh.testj.DotaGame

/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
/**
 * Created by xionglei01@baidu.com on 2022/5/5.
 */
class Player(var name: String, var age : Int) {

    lateinit var gameToPlay: IGame


    fun openGame() {
        xlog("正在打开游戏")
        gameToPlay = DotaGame(79, age)
    }

    override fun toString(): String {
        return "{name: $name, age: $age" +
                "}"
    }

}