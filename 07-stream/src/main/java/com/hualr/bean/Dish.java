package com.hualr.bean;

import lombok.Builder;
import lombok.Data;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/31 21:31
 * Version: 1.0.0
 */

@Data
@Builder
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;
    private int weight;

    public Dish(String name, boolean vegetarian, int calories, Type type,int weight) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
        this.weight=weight;
    }

    public Dish setweight(int weight){
        this.weight=weight;
        return this;
    }


    public enum Type {MEAT, FISH, OTHER}
}