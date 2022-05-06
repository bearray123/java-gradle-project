/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.kt

/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
/**
 * Created by xionglei01@baidu.com on 2022/5/5.
 */
object GameManager {

    val onLinePlayers  = HashMap<String, Player>()

    fun checkPlayer(pname: String) {
        val player = onLinePlayers[pname]
    }

}