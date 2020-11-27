package com.hualr.map.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/11/22 11:33
 * Version: 1.0.0
 */
public class MapTest {
    @Test
    public void test1() {
        System.out.println("-----------------数字相加--------------------");
        Map<String, Integer> map1 = new HashMap<>();
        map1.merge("a", 10, (v1, v2) -> v1 + v2);
        System.out.println(map1);

        System.out.println("===================list合并======================");
        Map<String, List<String>> map2 = new HashMap<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list1.add("1");
        list2.add("2");
        map2.put("a", list1);
        map2.merge("a", list2, (oldvalue, newvalue) -> {
            System.out.println(oldvalue);
            System.out.println(newvalue);
            oldvalue.addAll(newvalue);
            return oldvalue;
        });
        System.out.println(map2);

    }
}
