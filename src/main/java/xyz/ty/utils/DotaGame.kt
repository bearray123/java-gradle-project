/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.ty.utils

import xyz.ty.kt.utils.Hero
import xyz.ty.kt.utils.IGame

/**
 * Created by xionglei01@baidu.com on 2022/5/5.
 */
class DotaGame(var timeLimit: Long, var playerAge: Int) : IGame {

    init {
        checkPlayerAge(playerAge)
    }

    fun bandHero(hero: ArrayList<Hero>){
        hero.forEach {
            it.makeKillerAction()
        }
    }

}