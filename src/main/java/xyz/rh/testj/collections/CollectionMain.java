/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

/**
 * Created by xionglei01@baidu.com on 2022/7/14.
 */
class CollectionMain {

   public static void main(String[] args) {

      //testHashSet();

      testHashMap();

      System.out.println(new Object().hashCode());
   }

   public static void testHashMap() {
      HashMap<String, Integer> map1 = new HashMap<String, Integer>();

      map1.put("a", 1);
      map1.put("a", 2);

      map1.put("b", 1);
      map1.put("b", 2);

      map1.put("c", 1);
      map1.put("c", 2);
      map1.put("c", 3);
      map1.put("c", 4);

      for(String key : map1.keySet()) {
         System.out.println("map1: key=" + key + ", value=" + map1.get(key));
      }

      System.out.println("===" + map1.hashCode());


      //////////////////// 当hashmap用自定义对象作为key时，为什么要重写equals和hashcode？？？
      Person k1 = new Person("xionglei" ,"1234567");
      Person k2 = new Person("xionglei", "1234567");
      Person k3 = new Person("huyu", "1234568");
      // 业务上k1和k2是同一个人，因为重写的equals里判断只要name和id一样就是同一个人；
      HashMap<Person, String> hm = new HashMap();
      hm.put(k1, "his name is xionglei");
      hm.put(k2, "his name is dodo");
      // k1和k2虽然是两个不同的对象，业务上来看其实就是同一个人，所以在put到map后应该是覆盖的，只有一份，不应该有两份
      // 业务上k1和k2同一个人（在内存里是不同的对象），那么放到hashmap里就应该只有有一份；因为hashmap在key是不能重复的
      // 能否覆盖就看hashcode的生成了，如果不重写hashcode那么他们的hashcode必然不一样，那么肯定不会覆盖了；
      // 当然就算重写了hashcode也k1 k3也可能存在hash冲突，即k1 k3重写hashcode后得到的hashcode也是相同的情况，这时hashmap遇到冲突后就会对比value，是通过链表还是红黑树就看里面的原理了
      // 如果不重新hashcode 这里打印的size是2，重写后size就是正常的1了.
      System.out.println("hashMap.size=" + hm.size() + "  print get:" + hm.get(k2));
      ////////////////////////////////////////////////////////////////////////////

   }


   public static void testHashSet() {
      //HashSet去剔除重复的元素
      HashSet<String> set1 = new HashSet<>();
      HashSet<String> set2 = new HashSet<>();
      HashSet<String> set3 = new HashSet<>();

      set1.add("a");
      set1.add("b");
      set1.add("c");
      set1.add("a");
      set1.add("x");

      set2.add("1");
      set2.add("2");
      set2.add("3");
      set2.add("x");
      set2.add("b");

      set3.add("a");
      set3.add("b");
      set3.add("c");
      set3.add("y");

      set1.addAll(set3);

      //System.out.println("set.size=" + set1.size());
      //// 求set的交集： retainAll，交集放到调用者方
      //set1.retainAll(set2);

      // HashSet的两种遍历方式
      //1
      for (String key: set1) {
         System.out.println("set1.key = " + key);
      }
      //2
      Iterator iterator = set1.iterator();
      while (iterator.hasNext()) {
         System.out.println("    set1.key = " + iterator.next());
      }

   }

}



class Person {
   private String name;
   // 身份证
   private String identityCard;

   public Person(String name, String id) {
      this.name = name;
      this.identityCard = id;
   }

   @Override public boolean equals(Object o) {
      Person obj = null;
      if (o instanceof Person) {
         obj = ((Person) o);
      }
      if (obj.name == this.name && obj.identityCard == this.identityCard) {
         return true;
      }
      return false;
   }

   // 如果一个类重写了equals方法但是没有重写hashCode方法，那么该类无法结合所有基于散列的集合（HashMap，HashSet）一起正常运作。

   //@Override public int hashCode() {
   //   // 借用系统函数Objects.hash重新生产hashCode
   //   return Objects.hash(name, identityCard);
   //}
}