package com.hualr;

import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/11/17 16:16
 * Version: 1.0.0
 */
public class StringTest1 {
    @Test
    public void test1() {
        String m = "1131-back";
        String[] s = m.split("-back");
    }

    @Test
    public void test2() {
        String s = "fwefwefno mdfeeeefef";
        System.out.println(s.contains("no md"));
        System.out.println(s.contains("no1 md"));
        System.out.println(s.contains("no1 md1"));
        System.out.println(s.contains("no md"));
    }

    @Test
    public void test3() {
        System.out.println("".isEmpty());
    }

    @Test
    public void test4() {
        String a = "PORT-1-1-C1";
        int index = a.lastIndexOf("C");
        System.out.println(a.substring(index));
    }
}
