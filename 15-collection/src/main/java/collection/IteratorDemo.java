package collection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class IteratorDemo {
    public static void main(String[] args) {
        Apple apple1 = new Apple("green", 15);
        Apple apple2 = new Apple("red", 23);
        Apple apple3 = new Apple("green", 15);
        Apple apple4 = new Apple("green", 7);
        Apple apple5 = new Apple("green", 13);
        Apple apple6 = new Apple("red", 27);
        List<Apple> apples = Arrays.asList(apple1, apple2, apple3, apple4, apple5, apple6);

        Iterator<Apple> iterator = apples.iterator();

        //方法1 hasnext和next结合,这个我感觉用不到
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        //方法2:直接用forEach
        apples.forEach(System.out::println);

        //基本不用吧 这个都没简写
        apples.forEach(System.out::println);

        Map<Integer, Apple> map = new HashMap<>();
        map.put(1, apple1);
        map.put(2, apple2);
        map.put(3, apple3);
        map.put(4, apple4);
        map.put(5, apple5);
        map.put(6, apple6);


        map.forEach((key, value) -> System.out.println("item:" + key + "   count: " + value));

    }
}
