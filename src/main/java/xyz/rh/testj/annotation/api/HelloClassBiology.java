/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.annotation.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xionglei01@baidu.com on 2022/7/28.szzx
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface HelloClassBiology {

    // say代表注解的属性，String是该属性的值，default可以用来设置属性的默认值
    String say() default "by default: nothing to say";

    // 注解的属性类型只能是8种基本数据类型和String，不能是其他对象类型，自定义的类
    // 会报错：Invalid type 'Object' for annotation member
    //Object field1();
    //Model field2();

}
