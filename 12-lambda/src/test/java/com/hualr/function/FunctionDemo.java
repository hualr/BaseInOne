package com.hualr.function;

import hualr.bean.Apple;
import hualr.com.hualr.service.FunctionImpl;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/31 20:35
 * Version: 1.0.0
 */
public class FunctionDemo {
    @Test
    public void test() {
        //Function<Apple,Integer> function= (a)->1;

        Apple apple = new Apple("green", 32);
        //一般lambda无法识别类型大概率是因为泛型没有赋值
        /**
         * for function interface
         * find the <> type first
         */
        System.out.println(new FunctionImpl<Apple, Integer>().test(apple, Apple::getWeight));
        ;

        List<Integer> list = FunctionImpl.map(Arrays.asList("Java", "8", "in", "action"), String::length);
        System.out.println(list);
    }
}
