/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.annotation.devices;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xionglei01@baidu.com on 2022/7/28.
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface DeviceAbility {

    AbiType[] name();

}
