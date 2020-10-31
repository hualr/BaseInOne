package com.hualr.methodRef;

import java.util.function.Function;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/31 21:24
 * Version: 1.0.0
 */
public class MethodUse2<R, T> {
    Integer test1(String s, Function<String, Integer> function) {
        return function.apply(s);
    }

    T test2(R r, Function<R, T> function) {
        return function.apply(r);
    }
}
