package tPro;


import bean.Apple;
import bean.Fruit;
import bean.GenericHolder;

public class Maine {
    public static void main(String[] args) {
        GenericHolder<Fruit> fruitHolder = new GenericHolder<>();
        GenericHolder<Apple> appleHolder = new GenericHolder<>();
        Fruit fruit = new Fruit("水果");
        Apple apple = new Apple("苹果");

        fruitHolder.setObj(fruit);
        eatFruit1(fruitHolder);

        fruitHolder.setObj(apple);
        eatFruit1(fruitHolder);

        appleHolder.setObj(apple);
        // eatFruit1(appleHolder);

        //eatFruit1只能放fruitHolder 不能放AppleHolder.因为AppleHolder和fruitHolder不是一个类型


        GenericHolder<Apple> appleHolder2 = new GenericHolder<>();
        //这是一个贴了苹果标签的袋子
        //这是一个水果
        Apple apple2 = new Apple("苹果");
        appleHolder2.setObj(apple2);
        eatFruit2(appleHolder2);

    }

    public static void eatFruit1(GenericHolder<Fruit> fruitGenericHolder) {
        System.out.println("我在吃:" + fruitGenericHolder.getObj().getName());
    }

    public static void eatFruit2(GenericHolder<? extends Fruit> fruitGenericHolder) {
        System.out.println("我在吃:" + fruitGenericHolder.getObj().getName());
    }
}
