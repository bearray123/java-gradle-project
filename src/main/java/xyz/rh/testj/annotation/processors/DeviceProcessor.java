/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.annotation.processors;

import com.google.auto.service.AutoService;
import java.util.Arrays;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import xyz.rh.testj.annotation.api.Assemble;
import xyz.rh.testj.annotation.api.BindDevice;

/**
 * Created by xionglei01@baidu.com on 2022/7/30.
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes({"xyz.rh.testj.annotation.api.BindDevice", "xyz.rh.testj.annotation.api.Assemble"})
public class DeviceProcessor extends AbstractProcessor {

    private Messager mMessager = null;

    @Override public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mMessager = processingEnv.getMessager();
        print("000000000 DeviceProcessor:: init");

    }

    @Override public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        print("111111111111 DeviceProcessor:: process");

        for (Element element : roundEnv.getElementsAnnotatedWith(BindDevice.class)) {
            parseBindDevice(element);
        }

        for (Element element : roundEnv.getElementsAnnotatedWith(Assemble.class)) {
            parseAssemble(element);
        }

        return false;
    }


    private void parseBindDevice(Element element) {
        BindDevice anno = element.getAnnotation(BindDevice.class);
        print("parseBindDevice:: productid = " + anno.productId());
    }


    private void parseAssemble(Element element) {
        Assemble anno = element.getAnnotation(Assemble.class);
        print("parseAssemble:: fetures = " + Arrays.toString(anno.fetures()));
    }


    private void print(String msg) {
        mMessager.printMessage(Diagnostic.Kind.NOTE, msg);
    }

}
