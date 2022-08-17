/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.classloader;

import xyz.rh.testj.DotaGame;

/**
 * 测试类加载器
 * Created by xionglei01@baidu.com on 2022/7/27.
 */
class TestClassLoader {

    public static void main(String[] args) {

        System.out.println("----------Object----------------");
        printClassLoader(new Object()); // 如果是Object也是获取不到类加载器的

        System.out.println("-----------String---------------");
        printClassLoader(new String("abc")); // 如果是String也是获取不到类加载器的

        System.out.println("-----------TestClassLoader---------------");
        printClassLoader(new TestClassLoader());

        System.out.println("-----------DotaGame---------------");
        printClassLoader(new DotaGame(1, 20));

    }

    private static void printClassLoader(Object obj) {
        ClassLoader p1 = null ,p2 = null ,p3 = null ,p4 = null;

        ClassLoader p0 = obj.getClass().getClassLoader();
        System.out.println("p0= " + p0);
        if (p0 != null) {
            p1 = p0.getParent();
        }
        System.out.println("p1= " + p1);
        if (p1 != null) {
            p2 = p1.getParent();
        }
        System.out.println("p2= " + p2);
        if (p2 != null) {
            p3 = p2.getParent();
        }
        System.out.println("p3= " + p3);
        if (p3 != null) {
            p4 = p3.getParent();
        }
        System.out.println("p4= " + p4);
    }

}
