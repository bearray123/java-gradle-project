/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj;


/**
 * Created by xionglei01@baidu.com on 2022/7/25.
 */
class Main {

    static {
        System.out.println("abc");
    }

    Object obss = new Object();
    int outA = 123;

    public static void main(String[] args) {

        Main s = new Main();
        int a = s.outA + 100;
        System.out.println("this is a ==" + a);
        //a();

        s.c();

        new Father().makeLove();


    }

    //public static void b(Person person) {
    //    person.eat(123);
    //}

    private void c() {
        int a = 100;
        efh();
    }

    private void efh() {
        int b = 888;
        System.out.println(999 + b);
    }

    //public static void a() {
    //    int i = 70000;
    //    int j=3;
    //    int c = i + j;
    //}



}
