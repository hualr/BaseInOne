package com.hualr.function;

import hualr.bean.Apple;
import hualr.com.hualr.service.ApplePredicate;
import hualr.com.hualr.service.FilterAppleImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/31 20:35
 * Version: 1.0.0
 */
public class PredicateDemo {
    List<Apple> apples=new ArrayList<>();

    @Before
    public void before(){
        apples= getApples();
    }

    @Test
    public void test(){
        // 1. 单纯选择绿色的苹果
        List<Apple> greenApples= FilterAppleImpl.filterGreenApples(apples);
        System.out.println(greenApples);
    }

    @Test
    public void test2(){
        // 1. 单纯选择绿色的苹果
        List<Apple> greenApples= FilterAppleImpl.filterGreenApples(apples);
        System.out.println(greenApples);

    }

    public void test3(){
        /**根据指定的条件筛选
         * 直接将接口作为参数 然后创建一个匿名类 实现该匿名类即可
         */
        List<Apple> conditionApples=FilterAppleImpl.filterApplesByCondition(apples, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return (apple.getColor().equals("red"))&& (apple.getWeight()>25);
            }
        });
        System.out.println(conditionApples);
    }

    @Test
    public void test4(){
        //直接替换为lambda表达式.这里我清楚的是,传入的参数是根据方法声明判断的
        List<Apple> conditionApples2=FilterAppleImpl.filterApplesByCondition (apples,
                (apple)->(apple.getColor().equals("red")&&apple.getWeight()>26));

        System.out.println(conditionApples2);
    }

    @Test
    public void test5(){
        List<Apple> condictionApples3=FilterAppleImpl.filter(apples, apple -> apple.getWeight()>10);

        System.out.println(condictionApples3);
    }

    private List<Apple> getApples(){
        Apple apple1=new Apple("green",15);
        Apple apple2=new Apple("red",23);
        Apple apple3=new Apple("green",15);
        Apple apple4=new Apple("green",7);
        Apple apple5=new Apple("green",13);
        Apple apple6=new Apple("red",27);
        return Arrays.asList(apple1,apple2,apple3,apple4,apple5,apple6);
    }
}
