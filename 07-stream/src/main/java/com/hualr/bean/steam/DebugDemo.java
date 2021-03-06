package com.hualr.bean.steam;

import com.hualr.bean.Dish;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DebugDemo {
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

        //直接使用idea自带的stream debug
        List<String> lowCaloric_Dishes_Name2 = menu
                .parallelStream()
                .peek(x -> System.out.println(x))
                .filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }
}
