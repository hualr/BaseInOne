package com.hualr.function;

import hualr.bean.Apple;
import hualr.com.hualr.service.FunctionImpl;
import java.util.Arrays;
import java.util.List;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/31 20:35
 * Version: 1.0.0
 */
public class FunctionDemo {
    public void functionTest(){
        //Function<Apple,Integer> function= (a)->1;

        Apple apple=new Apple("green",32);
        //一般lambda无法识别类型大概率是因为泛型没有赋值
        System.out.println(new FunctionImpl<Apple,Integer>().test(apple,(apple1)->{
            System.out.println("this is a functionTest");
            return apple1.getWeight();
        }));;

        List<Integer> list=FunctionImpl.map(Arrays.asList("Java","8","in","action"), s -> s.length());
        System.out.println(list);
    }
}
