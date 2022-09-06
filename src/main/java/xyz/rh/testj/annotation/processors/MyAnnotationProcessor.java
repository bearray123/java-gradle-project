/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.annotation.processors;

import com.google.auto.service.AutoService;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * Created by xionglei01@baidu.com on 2022/7/29.
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes({"xyz.rh.testj.annotation.HelloClassBiology"})
public class MyAnnotationProcessor extends AbstractProcessor {

    Messager mMessager = null;

    @Override public synchronized void init(ProcessingEnvironment processingEnv) {
        System.out.println("1111111111111111111111111 test log print in init()" + processingEnv);
        mMessager = processingEnv.getMessager();
        mMessager.printMessage(Diagnostic.Kind.NOTE,"1111111111111111111111111 test log--------------------------init----------processingEnv=" + processingEnv);
        super.init(processingEnv);
    }

    @Override public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("222222222222222222222222 test log print in MyAnnotationProcessor.process" + roundEnv);
        mMessager.printMessage(Diagnostic.Kind.NOTE,"222222222222222222222222  test log--------------------------process----------roundEnv=" + roundEnv);
        return false;
    }

}
