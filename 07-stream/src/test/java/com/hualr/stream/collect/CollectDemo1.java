package com.hualr.stream.collect;

import com.hualr.bean.Dish;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * Author: zongqi.<br>
 * Function: <br>
 * Creating Time: 2020/12/21 22:50 <br>
 * Version: 1.0.0 <br>
 */
public class CollectDemo1 {
    private final List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT,11),
            new Dish("beef", false, 800, Dish.Type.MEAT,12),
            new Dish("chicken", false, 400, Dish.Type.MEAT,11),
            new Dish("french fries", true, 400, Dish.Type.OTHER,12),
            new Dish("rice", true, 350, Dish.Type.OTHER,11),
            new Dish("season fruit", true, 120, Dish.Type.OTHER,12),
            new Dish("pizza", true, 120, Dish.Type.OTHER,11),
            new Dish("prawns", false, 300, Dish.Type.FISH,12),
            new Dish("salmon", false, 450, Dish.Type.FISH,11));

    /**
     * 计算平均值
     */
    @Test
    public void test1() {
        System.out.println(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)));
        System.out.println(menu.stream().collect(Collectors.averagingInt(Dish::getCalories)));
    }

    /**
     * 统计总数
     */
    @Test
    public void test2() {
        System.out.println((Integer) menu.stream().mapToInt(Dish::getCalories).sum());
    }

    /**
     * 对收集到的值进一步处理
     */
    @Test
    public void test3() {
        List<Dish> collect1 = menu.stream().collect(Collectors.collectingAndThen(Collectors.toList(),
                Collections::unmodifiableList));
        String collect2 = menu.stream().collect(
                Collectors.collectingAndThen(Collectors.averagingInt(Dish::getCalories), a -> "平均卡路里为" + a));
        System.out.println(collect2);
    }
}
