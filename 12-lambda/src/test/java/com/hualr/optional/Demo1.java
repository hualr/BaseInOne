package com.hualr.optional;

import hualr.bean.Apple;
import java.util.Optional;
import org.junit.Test;

public class Demo1 {

    //存入方法是否设置允许为空
    @Test
    public void test1(){
        // 1、创建包装对象值非空的Optional对象
        Optional<String> optStr1 = Optional.of("optional");
        // 2、创建包装对象值允许为空的Optional对象 为空则返回empty 基本只用这个
        Optional<String> optStr2 = Optional.ofNullable(null);
    }

    @Test
    public void test2(){
        Apple appleQ=new Apple();
        appleQ.setColor("blue");
        appleQ.setWeight(100);
        Optional<Apple> optionalApple=Optional.of(appleQ);
        // 1. 筛选
        optionalApple.filter(apple -> apple.getColor().equals("blue"))
                .ifPresent(System.out::println);

        /* 2. 映射 先返回的是值 再把它封装!!! */
        Optional<String> color1= optionalApple
                .map(Apple::getColor);

        //这是map和flatmap的区别
        Optional<String> color2= optionalApple
                .flatMap(apple -> Optional.ofNullable(apple.getColor()));
    }

    @Test
    public void test3(){
        Optional<Apple> appleOptional= Optional.ofNullable(new Apple());
        //ZNN 1 ifPresent
        appleOptional.ifPresent(apple -> System.out.println(apple.getColor()));

        //ZNN 2 orElse
        String color1=appleOptional
                .map(Apple::getColor)
                .orElse("UNKOWN");

        System.out.println(color1);

        //ZNN 3 比orElse多一些功能
        String color2 =appleOptional
                .map(apple -> apple.getColor())
                .orElseGet(()->{
                    System.out.println("苹果的颜色是未知的");
                    return "UnKown";
                });
    }
}
