package com.hualr.methodRef;

import hualr.bean.Apple;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import org.junit.Before;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/31 21:21
 * Version: 1.0.0
 */
public class MethodTest {
    List<Apple> apples = new ArrayList<>();

    //初级演示
    @Test
    public void test1() {
        apples.sort(Comparator.comparing(apple -> apple.getWeight()));
        System.out.println(apples);
        apples.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(apples);
    }

    //静态方法引用
    @Test
    public void test2() {
        System.out.println(Integer.parseInt("100"));
        System.out.println(new MethodUse1().test1("50", s -> Integer.parseInt(s)));
        //方法引用一定是根据lambda可以改编的
        System.out.println(new MethodUse1().test1("30", Integer::parseInt));
        //要想让lambda认识s是String 得写入<>指定类型
        System.out.println(new MethodUse2<String, Integer>().test2("20", s -> Integer.parseInt(s)));
        System.out.println(new MethodUse2<String, Integer>().test2("15", Integer::parseInt));
    }

    @Test
    public void test3() {
        //2. 对象的普通引用
        System.out.println(new MethodUse2<String, Integer>().test2("14111", s -> s.length()));
        System.out.println(new MethodUse2<String, Integer>().test2("15", String::length));

        List<String> str = Arrays.asList("a", "b", "A", "B");
        str.sort(String::compareTo);
    }

    @Test
    public void test4() {
        //3.构造函数应用
        // Supplier<Apple> c1=()->new Apple();
        Supplier<Apple> c1 = Apple::new;
        Apple apple7 = c1.get();

        //Function<Integer,Apple> c2=(weight)-> new Apple(weight);
        Function<Integer, Apple> c2 = Apple::new;
        Apple apple8 = c2.apply(100);

        //BiFunction<String,Integer,Apple> c3=(color,weight)->new Apple(color,weight);
        BiFunction<String, Integer, Apple> c3 = Apple::new;
        Apple apple9 = c3.apply("red", 7);
    }

    @Before
    public void before() {
        Apple apple1 = new Apple("green", 15);
        Apple apple2 = new Apple("red", 23);
        Apple apple3 = new Apple("green", 15);
        Apple apple4 = new Apple("green", 7);
        Apple apple5 = new Apple("green", 13);
        Apple apple6 = new Apple("red", 27);
        apples = Arrays.asList(apple1, apple2, apple3, apple4, apple5);

    }
}
