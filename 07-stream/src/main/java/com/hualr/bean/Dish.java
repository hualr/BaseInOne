package com.hualr.bean;

import lombok.Data;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/31 21:31
 * Version: 1.0.0
 */

@Data
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }


    public enum Type {MEAT, FISH, OTHER}
}