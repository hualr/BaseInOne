package com.hualr.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/11/24 10:51
 * Version: 1.0.0
 */
public class OptionalTest1 {
    @Test
    public void test1() {
        Optional.ofNullable(method(null)).orElseGet(() -> {
            return "1";
        });
        System.out.println("hello");
    }

    String method(String info) {
        return info;
    }


    @Test
    public void test2() {
        System.out.println(test2Inter());
    }

    private boolean test2Inter() {
        try {
            try {
                throw new NullPointerException();
            } catch (NullPointerException e) {
                System.out.println("空");
            } catch (Exception e) {
                System.out.println("e1");
            }
        } catch (Exception e) {
            System.out.println("e2");
            return false;
        }
        System.out.println("lai");
        return true;
    }

    @Test
    public void test4() {
        System.out.println(true ^ false);
        System.out.println(false ^ true);
        System.out.println(true ^ true);
        System.out.println(false ^ false);
    }

    @Test
    public void test5() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.removeIf(s -> s.equals("1"));
        System.out.println(strings);
        System.out.println(strings.get(0));
    }

    @Test
    public void test6() {
        System.out.println(null instanceof List);
    }

    @Test
    public void test7() {
        String str = null;

        switch (Objects.requireNonNull(str)) {
            case "A":
                System.out.println("A");
                break;
            case "B":
                System.out.println("B");
                break;
            default:
                System.out.println("C");

        }
    }

    @Test
    public void test8() {
        boolean flag = test8Inter();
        String str = test8Inter2();
        System.out.println(flag);
        System.out.println(str);
    }

    boolean test8Inter() {
        try {
            int m = 1 / 1;
        } catch (Exception e) {
            System.out.println("捕获异常 1");
            return false;
        }
        return true;

    }

    String test8Inter2() {
        try {
            int m = 1 / 0;
        } catch (Exception e) {
            System.out.println("捕获异常 2");
            //return "ex";
        }
        System.out.println("异常之后的东西");
        return "ok";

    }
}
