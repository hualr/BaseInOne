package com.hualr.map;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/11/2 12:52
 * Version: 1.0.0
 */
public class MapIteratorTest {
    @Test
    public void test1() {
        /**
         * 最常见也是大多数情况下用的最多的，一般在键值对都需要使用
         */
        Map<String, String> map = new HashMap<String, String>();
        map.put("熊大", "棕色");
        map.put("熊二", "黄色");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String mapKey = entry.getKey();
            String mapValue = entry.getValue();
            System.out.println(mapKey + ":" + mapValue);
        }
    }

    @Test
    public void test2() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("熊大", "棕色");
        map.put("熊二", "黄色");
        //key
        for (String key : map.keySet()) {
            System.out.println(key);
        }
        //value
        for (String value : map.values()) {
            System.out.println(value);
        }
    }
}
