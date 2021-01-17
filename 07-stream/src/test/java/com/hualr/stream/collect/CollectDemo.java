package com.hualr.stream.collect;

import com.hualr.bean.Dish;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollectDemo {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT,11),
                new Dish("beef", false, 800, Dish.Type.MEAT,12),
                new Dish("chicken", false, 400, Dish.Type.MEAT,11),
                new Dish("french fries", true, 400, Dish.Type.OTHER,12),
                new Dish("rice", true, 350, Dish.Type.OTHER,11),
                new Dish("season fruit", true, 120, Dish.Type.OTHER,12),
                new Dish("pizza", true, 120, Dish.Type.OTHER,11),
                new Dish("prawns", false, 300, Dish.Type.FISH,12),
                new Dish("salmon", false, 450, Dish.Type.FISH,11));

        //1. 归约和汇总
        //1.1 统计所有值
        System.out.println(menu.stream().map(Dish::getCalories).count());
        //1.2 查找最大值
        Comparator<Dish> dishComparator = Comparator.comparing(Dish::getCalories);
        Optional<Dish> mostcaloriesDish = menu.stream().
                collect(Collectors.maxBy(dishComparator));

        //1.3 汇总:将所有对象求和为int所需的函数
        int totalCalories = menu.stream().
                collect(Collectors.summingInt(o -> o.getCalories()));


        // 2.1 映射处理
        Map<String, Integer> dishMap1 = menu
                .stream()
                .collect(Collectors.toMap(Dish::getName, Dish::getCalories));
        dishMap1.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });

        List<String> list = menu.stream().map(Dish::getName).collect(Collectors.toList());
        //list.forEach(System.out::print);

        //2.2 string转化
        String joined = menu.stream().map(Dish::getName).collect(Collectors.joining(","));
        System.out.println(joined);

        //3 常用静态工具方法
        List<String> list2 = menu.stream()
                .map(Dish::getName)
                .collect(Collectors
                        .toCollection(ArrayList::new));
        System.out.println(list2);
    }
}
