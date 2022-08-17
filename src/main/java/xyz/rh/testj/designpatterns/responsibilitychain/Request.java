/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.designpatterns.responsibilitychain;

/**
 * Created by xionglei01@baidu.com on 2022/7/31.
 */
public class Request {

    int ownerAge;
    int height;
    int intelligence; // 智力
    int output;

    @Override public String toString() {
        return "Request{" +
            "ownerAge=" + ownerAge +
            ", height=" + height +
            ", intelligence=" + intelligence +
            ", output=" + output +
            '}';
    }
}
