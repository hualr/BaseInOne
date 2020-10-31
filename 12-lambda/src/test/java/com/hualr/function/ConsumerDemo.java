package com.hualr.function;

import hualr.bean.Apple;
import hualr.com.hualr.service.ComsumerImpl;
import java.util.Arrays;
import java.util.function.Consumer;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/31 20:33
 * Version: 1.0.0
 */
public class ConsumerDemo {
    @Test
    public void comsumer(){
        // 直接调用消费接口
        Consumer<Apple> consumer=(Apple a)->{
            System.out.println("I have a "+a);
        };

        Apple apple=new Apple("red",12);

        // 关键式找到使用消费者的地方 如下都是消费者 但是需要关注的式消费者到底在哪被实现
        new ComsumerImpl<Apple>().test(apple,consumer);
        ComsumerImpl.forEach(Arrays.asList(1,2,3,4,5),(a)-> System.out.println(a));
    }
}
