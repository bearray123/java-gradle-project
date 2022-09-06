/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.reflect;

import com.esotericsoftware.reflectasm.MethodAccess;
import java.lang.reflect.Method;

/**
 *
 * 高性能反射库 测试
 * 官网：https://github.com/EsotericSoftware/reflectasm
 * 底层采用ASM字节码插桩实现
 *
 * Created by xionglei01@baidu.com on 2022/8/14.
 */
class TestReflectASM {

   public static void main(String[] args) throws Exception {

      TestReflectASM sMain = new TestReflectASM();
      sMain.testJdkReflect();
      sMain.testReflectAsm4Name();


   }


   /**
    * JDK反射调用方法
    * @throws Exception
    */
   public void testJdkReflect() throws Exception {
      UserService target = new UserService();
      long start = System.currentTimeMillis();
      Method method = target.getClass().getMethod("update", int.class, String.class);
      for (int i = 0; i < 100000000; i++) {
         method.invoke(target, 1, "zhangsan");
      }
      long end = System.currentTimeMillis();
      System.out.println("timeout=" + (end - start));//341 237 260 315 262
   }

   /**
    * ReflectAsm反射调用方法
    * 用名称定位反射方法
    */
   public void testReflectAsm4Name() {
      UserService target = new UserService();
      MethodAccess access = MethodAccess.get(UserService.class);//生成字节码的方式创建UserServiceMethodAccess
      long start = System.currentTimeMillis();
      for (int i = 0; i < 100000000; i++) {
         access.invoke(target, "update", 1, "zhangsan");
      }
      long end = System.currentTimeMillis();
      System.out.println("timeout=" + (end - start));//16 14 14 13 15
   }


}
