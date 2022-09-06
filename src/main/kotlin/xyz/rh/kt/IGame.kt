/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.kt

import xyz.rh.kt.utils.xlog

/**
 * Created by xionglei01@baidu.com on 2022/5/5.
 */
interface IGame {

    fun checkPlayerAge(age: Int) {
        if (age < 18) {
            xlog("您未满18岁....")
            throw IllegalArgumentException("未满18，不能完游戏")
        }
        xlog("验证通过")

    }

}