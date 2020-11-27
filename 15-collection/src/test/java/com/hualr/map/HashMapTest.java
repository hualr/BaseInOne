package com.hualr.map;

import collection.Apple;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/11/2 12:13
 * Version: 1.0.0
 */
public class HashMapTest {
    List<Apple> apples;

    /**
     * 演示merge的使用
     * 1. merge(key,value,function) 如果key取出来是空,那么存入value.否则将value和原值进行处理,处理的具体内容通过function定义
     */
    @Test
    public void test1() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        apples.forEach(apple -> hashMap.merge(apple.getColor(), apple.getWeight(), (a, b) -> Integer.sum(a, b)));
        System.out.println(hashMap);

       /* HashMap<String, ArrayList<Integer>> hashMap2 = new HashMap<>();
        apples.forEach(apple -> {
            hashMap2.merge(apple.getColor(), new ArrayList<>().add(apple.getWeight()),
                    (oldValue, newValue) -> oldValue.addAll(newValue.get(0)));
        });
        System.out.println(hashMap2);*/

    }

    /**
     * 演示compute的使用 我更喜欢用这个 具备通用性
     */
    @Test
    public void test2() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        apples.forEach(apple -> hashMap.compute(apple.getColor(), (k, v) -> {
            if (v == null) {
                return apple.getWeight();
            } else {
                return v + apple.getWeight();
            }
        }));
        System.out.println(hashMap);
    }

    /**
     * 后面两种情况对应的key存在/不存在的compute
     */
    @Test
    public void test3() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        apples.forEach(apple -> hashMap.computeIfAbsent(apple.getColor(), (k) -> {
            System.out.println("第一次出现的颜色苹果是" + apple.getWeight());
            return apple.getWeight();
        }));
        System.out.println(hashMap);
    }

    @Test
    public void test4() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        apples.forEach(apple -> hashMap.computeIfPresent(apple.getColor(), (k, v) -> {
            System.out.println("第一次出现的颜色苹果是" + apple.getWeight());
            return apple.getWeight();
        }));
        System.out.println(hashMap);
    }

    @Before
    public void prepareResources() {
        Apple apple1 = new Apple("green", 15);
        Apple apple2 = new Apple("red", 23);
        Apple apple3 = new Apple("green", 15);
        Apple apple4 = new Apple("green", 7);
        Apple apple5 = new Apple("yellow", 13);
        Apple apple6 = new Apple("red", 27);
        apples = Arrays.asList(apple1, apple2, apple3, apple4, apple5, apple6);
    }


}
