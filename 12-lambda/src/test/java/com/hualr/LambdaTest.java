package com.hualr;

import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/31 21:21
 * Version: 1.0.0
 */
public class LambdaTest {
    @Test
    public void test() {
        Runnable r1 = () -> System.out.println("hello world1");
        Runnable r2 = () -> {
        };

        Thread thread1 = new Thread(() -> System.out.println("hello world"));
        //实际上就是在Thread中放入runnable
        Thread thread2 = new Thread(() -> System.out.println("hello world"));
        Thread thread3 = new Thread(r2);

        /**
         * 破解lambda的几个关键
         * 1. 找到接口是谁.根据接口中唯一的方法判断参数类型
         * 2. 根据方法返回确认return的是谁
         * 3. 判断到底是谁在用接口的方法 这个方法在哪里被使用
         */

        /* 在lambda表达式中无法使用可重新赋值的局部变量,一定得确保该局部变量为final类型*/
        final int portNumber = 1;
        Runnable runnable3 = () -> {
            System.out.println(portNumber);
        };
    }
}
