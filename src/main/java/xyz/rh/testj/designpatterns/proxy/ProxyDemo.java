/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.designpatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 *
 * 动态代理
 * Created by xionglei01@baidu.com on 2022/7/19.
 */
class ProxyDemo {


   //程序的入口方法
   public static void main(String[] args) {

      GoodsSeller logif = new LogiFactory();

      // 查看动态代理在内存中生成的class文件？
      // 不过在debug时 这个运行时生成的动态代理对象是可以导出到文件的，方便查看，方法有两种
      //在代码中加入System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
      //在运行时加入jvm 参数 -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true

      // 动态代理类
      GoodsSeller proxy = (GoodsSeller) Proxy.newProxyInstance(
          GoodsSeller.class.getClassLoader(),
          new Class[]{GoodsSeller.class},
          new ProxyInvocationHandler(logif)
          );

      //System.out.println("外层new出来的GitHubApiService = " + apiService);
      //System.out.println(proxy.getClass());
      //调用 listRepos 方法
      proxy.sell("keybord", 200);
      System.out.println("proxy = " + proxy);

   }

   static class ProxyInvocationHandler implements InvocationHandler {

      Object target;
      public ProxyInvocationHandler(Object t) {
         this.target = t;
      }


      /**
       *
       * @param proxy 代表的是代理对象
       * @param method 代理对象执行的方法
       * @param args 代理对象执行的方法参数
       * @return
       * @throws Throwable
       */
      @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         System.out.println("method = " + method.getName() + "   args = " + Arrays.toString(args));

         // 这行对proxy的打印会导致栈溢出，原因是任何对proxy的调用都会再次走到invoke方法，打印proxy其实就是调用proxy.toString()，所以会循环无限调用最终导致栈溢出
         //System.out.println("InvocationHandler#invoke() proxy=" + proxy);

         ///
         System.out.println("invoke before");
         Object ret = method.invoke(target, args);
         System.out.println("invoke after --- ret=" + ret);
         ///

         // 这里的ret就是被代理对象执行后的返回值，如果ret是null，那么在外层执行proxy.sell时就会报空指针
         return ret;
      }
   }

   public interface GoodsSeller {
        void sell(String product, int price);
   }

   public static class LogiFactory implements GoodsSeller {
      @Override public void sell(String product, int price) {
         System.out.println("Logi factory sell product: " + product + " : price=" + price);
      }
   }

}
