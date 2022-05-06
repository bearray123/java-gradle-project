/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.ty.kt.utils

/**
 * Created by xionglei01@baidu.com on 2022/5/5.
 */
class LittleFish : Hero() {


    override fun makeKillerAction() {
        log("先E，然后C，过了2秒，然后放大，然后继续C，C完再E他，过1秒然后再C")
    }


}