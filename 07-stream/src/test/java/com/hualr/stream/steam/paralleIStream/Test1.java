package com.hualr.stream.steam.paralleIStream;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/11/3 12:15
 * Version: 1.0.0
 */

public class Test1 {
    @Test
    public void test1() {
        List<Integer> listOfIntegers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            listOfIntegers.add(i);
        }
        List<Integer> parallelStorage = new ArrayList<>();
        listOfIntegers.parallelStream()
                .filter(i -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i % 2 == 0;
                })
                .forEach(parallelStorage::add);
        System.out.println(parallelStorage);

       /* parallelStorage.stream().forEachOrdered(e -> System.out.print(e + " "));

        System.out.println();
        System.out.println("Sleep 5 sec");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        parallelStorage.stream().forEachOrdered(e -> System.out.print(e + " "));*/
    }
}
