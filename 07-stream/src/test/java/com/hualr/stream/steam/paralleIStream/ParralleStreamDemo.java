package com.hualr.stream.steam.paralleIStream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;

/**
 * Author: zongqi<br>
 * Function:<br>
 * Creating Time：2020/11/3 12:15<br>
 * Version: 1.0.0<br>
 */

public class ParralleStreamDemo {

    private final List<Integer> listOfIntegers = new ArrayList<>();

    @Before
    public void beforeTest() {
        for (int i = 0; i < 100; i++) {
            listOfIntegers.add(i);
        }
    }


    /**
     * <h2>平行流最值得注意的地方</h2>
     * <li>线程不安全 因此最好的方法就是使用Collection</li>
     */
    @Test
    public void correctTest() {
        List<Integer> parallelStorage = listOfIntegers.parallelStream()
                .filter(i -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i % 2 == 0;
                }).collect(Collectors.toList());
        System.out.println(parallelStorage);
    }

    @Test
    public void badTest() {
        List<Integer> parallelStorage = new ArrayList<>();
        listOfIntegers.parallelStream()
                .filter(i -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i % 2 == 0;
                })
                .forEach(parallelStorage::add);
        System.out.println(parallelStorage);
    }

}
