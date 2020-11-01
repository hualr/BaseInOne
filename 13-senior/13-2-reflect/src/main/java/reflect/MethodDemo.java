package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import reflect.bean.Apple;

public class MethodDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //获取类的class对象
        Class clz = Class.forName("com.hualr.API.Apple");
        System.out.println("直接获取的类为:" + clz);

        //通过class对象产生method对象
        Method setWeight = clz.getMethod("setWeight", Integer.class);
        System.out.println("产生的方法是:" + setWeight);

        Apple apple = new Apple();
        //注入方法
        setWeight.invoke(apple, 100);
        System.out.println(apple);

    }
}
