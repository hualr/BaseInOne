package com.hualr.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/11/12 13:41
 * Version: 1.0.0
 */
public class ListTest {
    @Test
    public void test() {
        System.out.println(Collections.emptyList().size());
    }

    @Test
    public void test2() {
        List<Object> a = Collections.emptyList();
        a.stream().forEach(o -> System.out.println(o));
        System.out.println(1);
    }

    @Test
    public void test3() {
        List<String> a1 = new ArrayList<>();
        a1.add("1");
        a1.add("2");
        List<List<String>> lists = Collections.singletonList(a1);
    }

    @Test
    public void test4() {
        List<Integer> a1 = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(a1.subList(0, 2));
        List<Integer> a2 = Arrays.asList(1, 2, 3, 4, 5);
        //a2.add(null);
        int i = 0;
        System.out.println(++i);
    }

    @Test
    public void test5() {
        List a = Collections.emptyList();
        System.out.println(a.isEmpty());
    }
}
