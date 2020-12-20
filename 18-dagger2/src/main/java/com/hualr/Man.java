package com.hualr;

import javax.inject.Inject;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/11/27 22:08
 * Version: 1.0.0
 */
public class Man {
    @Inject
    Apple apple;

    @Inject
    Water water;


    public String eat() {
        StringBuilder sb = new StringBuilder();
        sb.append("我吃的是 ");
        if (apple != null) {
            sb.append(apple.toString());
        }

        if (water != null) {
            sb.append("  ");
            sb.append(water.toString());
        }
        return sb.toString();
    }
}
