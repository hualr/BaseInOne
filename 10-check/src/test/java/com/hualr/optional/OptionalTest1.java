package com.hualr.optional;

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
