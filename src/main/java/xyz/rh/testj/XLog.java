/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xionglei01@baidu.com on 2022/8/5.
 */
public final class XLog {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static void log(Object msg) {
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date) + ":[" + Thread.currentThread().getName() + "]:" + msg);
    }

}
