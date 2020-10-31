package com.hualr.methodRef;

import java.util.function.Function;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/31 21:24
 * Version: 1.0.0
 */
public class MethodUse1 {
    Integer test1(String s, Function<String, Integer> function) {
        return function.apply(s);
    }
}
