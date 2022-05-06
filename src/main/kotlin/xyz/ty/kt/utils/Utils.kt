/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.ty.kt.utils


/**
 * Created by xionglei01@baidu.com on 2022/5/5.
 */


fun log(msg: Any?) {
    println("${Thread.currentThread().name}:${msg}")
}

fun System.fuck() {
    println(123456)
}