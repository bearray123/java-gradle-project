/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.kt.utils

import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by xionglei01@baidu.com on 2022/5/5.
 */


fun log(msg: Any?) {
// yyyy-MM-dd HH:mm:ss.SS的大小写的含义
// yyyy：代表年
// M：月份数字。一位数的月份没有前导零
// MM：代表月（MM和M一样，区别就是MM表示从零开始，比如四月份，MM显示04，M显示4，后面的如同）
// dd：代表日
// HH：代表24小时制的小时
// hh：代表12小时制的小时
// mm：代表分钟
// ss：代表秒
// SSS：代表毫秒
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
    val date = Date(System.currentTimeMillis())
    println(formatter.format(date) + ":[" + Thread.currentThread().name + "]:" + msg)

}

fun System.fuck() {
    println(123456)
}