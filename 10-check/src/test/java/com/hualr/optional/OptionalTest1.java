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
}
