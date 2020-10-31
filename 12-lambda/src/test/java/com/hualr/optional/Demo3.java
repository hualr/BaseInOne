package com.hualr.optional;


import hualr.bean.Box1;
import hualr.bean.Box2;
import hualr.bean.Box3;
import java.util.Optional;
import org.junit.Test;

public class Demo3 {
    @Test
    public void test() {
        Box3 box3 = new Box3();
        Box2 box2 = new Box2();
        Box1 box1 = new Box1();

        box1.setBox2(box2);
        box2.setBox3(box3);
        box3.setWeight(101L);
        Optional<Box1> box11 = Optional.of(box1);

        Long weight = box11.map(Box1::getBox2)
                .map(Box2::getBox3)
                .map(Box3::getWeight)
                .orElse(0L);

        System.out.println(weight);
    }
}
