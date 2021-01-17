package compare;

import com.hualr.bean.Dish;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

public class Compare2Test {
    List<Dish> menus = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT, 11),
            new Dish("beef", false, 800, Dish.Type.MEAT, 12),
            new Dish("chicken", false, 400, Dish.Type.MEAT, 11),
            new Dish("french fries", true, 400, Dish.Type.OTHER, 12),
            new Dish("rice", true, 350, Dish.Type.OTHER, 11),
            new Dish("season fruit", true, 120, Dish.Type.OTHER, 12),
            new Dish("pizza", true, 120, Dish.Type.OTHER, 11),
            new Dish("prawns", false, 300, Dish.Type.FISH, 12),
            new Dish("salmon", false, 450, Dish.Type.FISH, 11));

    /**
     * 最基础的list排序
     */
    @Test
    public void test1() {
        menus.sort((Comparator.comparingInt(Dish::getCalories)));
        for (Dish menu : menus) {
            System.out.println(menu);
        }
    }

    /**
     * stream排序
     */
    @Test
    public void test2() {
        final List<Dish> newDishes =
                menus.stream().sorted((Comparator.comparingInt(Dish::getCalories))).collect(Collectors.toList());
        for (Dish dish : newDishes) {
            System.out.println(dish);
        }
    }

    /**
     * stream 排序进阶
     */
    @Test
    public void test3() {
        final List<Dish> newDishes = menus.stream().sorted(
                Comparator.comparingInt(Dish::getCalories)
                        .thenComparingInt(Dish::getWeight)
                        .reversed())
                .collect(Collectors.toList());
        for (Dish dish : newDishes) {
            System.out.println(dish);
        }
    }
}
