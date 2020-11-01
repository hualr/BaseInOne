package com.hualr.future.complete;


import com.hualr.bean.Shop;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class future2 {
    public static void main(String[] args) throws InterruptedException {
        Shop shop = new Shop();
        long start = System.nanoTime();

        //Future<Double> futurePrice=shop.getPriceAsync("my favorite product");

        Future<Double> futurePrice = shop.getPriceAsynclite("my favorite product");

        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("invocation returned after " + invocationTime + " msecs");

        System.out.println("准备统计其他的事情");
        Thread.sleep(3000L);
        System.out.println("完成统计其他的事情");
        try {
            //如果get不到,会一直堵着 所以用重载方法把
            double price = futurePrice.get();
            System.out.printf("price is %.2f%n", price);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        long retrievalTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }
}
