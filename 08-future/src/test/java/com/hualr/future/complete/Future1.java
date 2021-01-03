package com.hualr.future.complete;


import com.hualr.bean.Shop;

public class Future1 {
    public static void main(String[] args) throws InterruptedException {
        //普通串行操作
        Thread.sleep(1000L);
        System.out.println(1);
        new Shop().getPrice("fewfw");
        System.out.println(2);
    }
}
