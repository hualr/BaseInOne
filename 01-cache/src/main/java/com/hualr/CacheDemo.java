package com.hualr;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/26 22:33
 * Version: 1.0.0
 */
public class CacheDemo {
    public static void main(String[] args){
         Cache<Object, Object> manualCache =  Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .build();

    }
}
