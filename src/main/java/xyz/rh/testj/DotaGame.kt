/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj

import xyz.rh.kt.Hero
import xyz.rh.kt.IGame

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