package collection;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorDemo {
    public static void main(String[] args) {
        Apple apple1 = new Apple("green", 15);
        Apple apple2 = new Apple("red", 23);
        Apple apple3 = new Apple("green", 15);
        Apple apple4 = new Apple("green", 7);
        Apple apple5 = new Apple("green", 13);
        Apple apple6 = new Apple("red", 27);


        List<Apple> apples = Arrays.asList(apple1, apple2, apple3, apple4, apple5);


        //这里引入了一个事情:a1.getWeight().compareTo(a2.getWeight())注意到,前后都是Integer才能有compareTo 而非Integer

        //-------------------通过list的sort方法实现-------------------------------------
        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });

        apples.sort((a, b) -> b.getWeight().compareTo(a.getWeight()));
        //----------------------使用Collections自带的方法-------------------------------------

        //这个和List对象的sort实际上是一个
        Collections.sort(apples, new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        //------------------------------实用Comparator自带的类----------------------------
        //这是一个Comparator的一个类
        Comparator<Apple> c = Comparator.comparing((Apple a) -> a.getWeight());
        apples.sort(c);
        apples.sort(Comparator.comparing(a -> a.getWeight()));
        apples.sort(Comparator.comparing(Apple::getWeight));

        //比较器链
        apples.sort(Comparator.comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));


    }
}
