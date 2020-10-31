package com.hualr.function;

import hualr.bean.Apple;
import hualr.com.hualr.service.SupplierImpl;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/31 20:34
 * Version: 1.0.0
 */
public class SupplierDemo {
    @Test
    public void test(){
        Apple apple = new Apple();
        System.out.println(new SupplierImpl().test(apple,()->1));
        return;
    }
}
