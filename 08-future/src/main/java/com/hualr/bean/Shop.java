package com.hualr.bean;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import lombok.Data;

@Data
public class Shop {
    private String name;

    public Shop() {

    }

    public Shop(String name) {
        this.name = name;
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public double getPrice(String product) {
        System.out.println("开始查询价格");
        double price = calculatePrice(product);
        System.out.println("查询完成价格");
        return price;
    }

    public Future<Double> getPriceAsync(String product) {
        System.out.println("正在计算");
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        //直接fork一个线程 然后继续往后走,这个线程的处理主要看自己
        new Thread(() -> {
            //该线程正在完成一个计算工作
            double price = calculatePrice(product);

            futurePrice.complete(price);
            System.out.print("完成计算 返回结果--");
            System.out.printf("price is %.2f%n", price);
        }).start();

        System.out.println("已经加入计算器");
        return futurePrice;
    }

    public Future<Double> getPriceAsynclite(String product) {
        System.out.println("正在计算");
        return CompletableFuture.supplyAsync(() -> {
            double price = calculatePrice(product);
            System.out.print("完成计算 返回结果--");
            System.out.printf("price is %.2f%n", price);
            return price;
        });
    }

    private double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }


}
