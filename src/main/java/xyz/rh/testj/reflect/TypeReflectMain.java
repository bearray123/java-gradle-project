package xyz.rh.testj.reflect;

public class TypeReflectMain {


    public static void main(String[] args) {

        System.out.println("fatherClass.isAssignableFrom(father): " + MyFatherClass.class.isAssignableFrom(MyFatherClass.class));

        System.out.println("Object.isAssignableFrom(father): " + Object.class.isAssignableFrom(MyFatherClass.class));


        System.out.println("fatherClass.isAssignableFrom(son): " + MyFatherClass.class.isAssignableFrom(MySonClass.class));

        System.out.println("sonClass.isAssignableFrom(father): " + MySonClass.class.isAssignableFrom(MyFatherClass.class));


    }

}

class MyFatherClass {

}

class MySonClass extends MyFatherClass {

}


