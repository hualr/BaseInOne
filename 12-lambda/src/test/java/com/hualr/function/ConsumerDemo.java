package com.hualr.function;

import hualr.bean.Apple;
import hualr.com.hualr.service.ComsumerImpl;
import java.util.Arrays;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/31 20:33
 * Version: 1.0.0
 */
@Slf4j
public class ConsumerDemo {
    @Test
    public void comsumer() {
        // 直接调用消费接口
        Consumer<Apple> consumer = (Apple a) -> {
            System.out.println("I have a " + a);
        };

        Apple apple = new Apple("red", 12);

        /**
         * 1. 找到使用消费者的方法 如果该方法为接口 直接看其实现类即可
         */
        new ComsumerImpl<Apple>().test(apple, consumer);
        ComsumerImpl.forEach(Arrays.asList(1, 2, 3, 4, 5), System.out::println);
    }
}
