package hualr.com.hualr.service;

import hualr.bean.Apple;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author ：zq
 * @description：一个筛选特别的苹果的服务类
 * @date ：2020/10/12 22:41
 */
public class FilterAppleImpl {
    //筛选所有绿色的苹果
    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> greenApples= new ArrayList<>();
        for (Apple apple: inventory){
            if ("green".equals(apple.getColor())){
                greenApples.add(apple);
            }
        }
        return greenApples;
    }

    //根据选择的颜色筛选苹果
    public static List<Apple> filterApplesByColor(List<Apple> inventory,String color){
        List<Apple> colorApples= new ArrayList<>();
        for (Apple apple: inventory){
            if (color.equals(apple.getColor())){
                colorApples.add(apple);
            }
        }
        return colorApples;
    }

    //根据特别的条件筛选苹果
    public static List<Apple> filterApplesByCondition(List<Apple> inventory,ApplePredicate applePredicate){
        List<Apple> conditionApples= new ArrayList<>();
        for (Apple apple: inventory){
            if (applePredicate.test(apple)){
                conditionApples.add(apple);
            }
        }
        return conditionApples;
    }

    //函数式编程 这一串代码为核心所在
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate ){
        List<T> result=new ArrayList<>();
        for (T e:list){
            if (predicate.test(e)){
                result.add(e);
            }
        }
        return result;
    }



}
