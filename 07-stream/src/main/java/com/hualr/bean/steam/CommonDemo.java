package com.hualr.bean.steam;

import com.hualr.bean.Dish;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CommonDemo {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }

        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });

        Collections.sort(lowCaloricDishes, (o1, o2) -> Integer.compare(o1.getCalories(), o2.getCalories()));

        List<String> lowCaloric_Dishes_Name = new ArrayList<>();
        for (Dish dish : lowCaloricDishes) {
            lowCaloric_Dishes_Name.add(dish.getName());
        }
        System.out.println(lowCaloric_Dishes_Name);

        List<String> lowCaloric_Dishes_Name2 = menu
                .parallelStream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());

        //拿到一个List
        System.out.println(menu.stream());
        List<Dish> dishes = menu.stream()
                .collect(Collectors.toList());

        //筛选
        System.out.println(menu.stream());
        List<Dish> dishes2 = menu.stream()
                .filter(Dish::isVegetarian)
                .filter(d -> d.getType() != null)
                .filter(o -> {
                    System.out.println("hehe");
                    return true;
                })
                .collect(Collectors.toList());

        //截断
        System.out.println(menu.stream());
        List<Dish> dishes3 = menu.stream()
                .limit(5)//只保留前m个
                .skip(1) //扔掉前n个
                .collect(Collectors.toList());

        //对剩下的值做遍历 只能遍历一次 走完就没了啊
        menu.stream()
                .distinct() //保证所有筛选均不同
                .forEach((s) -> System.out.print(s + "/"));

        System.out.println();

        //映射
        menu.stream()
                .map(dish -> dish.getType())
                .forEach(s -> System.out.print(s + "/"));

        //计算
        long count = menu.stream()
                .filter(x -> true)
                .count();
        System.out.println(count);


        //reduce归约:将每个值反复结合,直到stream成了一个值
        List<Integer> someNumbers = Arrays.asList(1, 3, 5, 6, 2);

        int sum = 0;
        for (int x : someNumbers) {
            sum += x;
        }

        int sum1 = someNumbers.stream().reduce(0, (a, b) -> a + b);
        int sum2 = someNumbers.stream().reduce(0, (a, b) -> Integer.sum(a, b));
        int sum3 = someNumbers.stream().reduce(0, Integer::sum);

        System.out.println(sum1 + sum2 + sum3);

        //最小值
        Optional<Integer> sum4 = someNumbers.stream().reduce((a, b) -> (a < b ? a : b));
        //比较这两个 主要是后者也参与进去了
        int sum5 = someNumbers.stream().reduce(someNumbers.get(0), (a, b) -> (a < b ? a : b));
        System.out.println(sum4.get());
        System.out.println(sum5);


        //排序
        List<Integer> someNumbers2 = someNumbers.stream()
                .sorted(Integer::compareTo)
                .collect(Collectors.toList());
        System.out.println(someNumbers2);
    }


}
