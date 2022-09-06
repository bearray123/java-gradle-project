/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.annotation;

import java.lang.reflect.Method;
import xyz.rh.testj.annotation.api.HelloClassBiology;
import xyz.rh.testj.annotation.api.HelloRuntimeBiology;

/**
 * Created by xionglei01@baidu.com on 2022/7/26.
 */

class AnnotationMain {

    public static void main(String[] args) {

        // ---------------------RUNTIME 运行时注解------------------------
        // 运行时注解就是在代码运行时可以获取到的注解，所以通过Class.getAnnotation和Class.isAnnotationPresent是可以拿到注解信息的
        // 运行时注解是程序在运行时jvm生成注解的动态代理类的对象$Proxy1，该代理对象是在内存中动态生成的class，注意是在内存中，不存在真实的class文件；
        // 不过在debug时 这个运行时生成的动态代理对象是可以导出到文件的，方便查看，方法有两种
        //在代码中加入System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //在运行时加入jvm 参数 -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true

        boolean hasRuntimeAnno = RuntimeAnnoModel.class.isAnnotationPresent(HelloRuntimeBiology.class);
        System.out.println("has Runtime Annotation = " + hasRuntimeAnno);
        HelloRuntimeBiology runtimeAnnotation = null;
        if (hasRuntimeAnno) {
            runtimeAnnotation = RuntimeAnnoModel.class.getAnnotation(HelloRuntimeBiology.class);
            System.out.println("runtimeAnnotation = " + runtimeAnnotation.getClass());  // 这里可以拿到注解对象：class com.sun.proxy.$Proxy1
        }
        System.out.println(runtimeAnnotation.say());//调用注解对象的say属性，并打印到控制台
        System.out.println();


        // ---------------------CLASS 编译时注解------------------------
        boolean hasClassAnno = ClassAnnoModel.class.isAnnotationPresent(HelloClassBiology.class);
        System.out.println("ClassAnnoModel.class.getAnnotation = " + ClassAnnoModel.class.getAnnotation(
            HelloClassBiology.class));
        System.out.println("has Class Annotation = " + hasClassAnno);
        HelloClassBiology classAnnotation = null;
        if (hasClassAnno) {
            classAnnotation = ClassAnnoModel.class.getAnnotation(HelloClassBiology.class);
            System.out.println("classAnnotation = " + classAnnotation);
        }

        Method metho = null;

        // 该行一定会报空指针异常：因为是编译时注解，在运行时是无法通过isAnnotationPresent和getAnnotation拿到数据的，所以肯定是false和null
        //System.out.println(classAnnotation.say());//调用注解对象的say属性，并打印到控制台


    }

}

/////// 编译时注解
@HelloClassBiology(say = "nice to meet you from class annotation!")
class ClassAnnoModel {

}

////// 运行时注解
@HelloRuntimeBiology(say = "nice to meet you from runtime annotation!")
class RuntimeAnnoModel {

}




