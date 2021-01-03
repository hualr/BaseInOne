package com.hualr.future.complete;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CommonFuture {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Double> future = executorService.submit(() -> {
            System.out.println("我在进行异步操作");
            return 1.0;
        });
        //异步提交了 但是我们这里会优先执行一个1;
        //且我们发现这是一个死循环 永远到不到get
        for (int i = 0; i <= 100000000; i++) {
            if (i == 1) {
                System.out.println("一项耗时的操作");
            }
         /*   if (i==100000000){
                i=-100000000;
            }*/

        }
        System.out.println("完成计算");
        try {
            Double result = future.get(1, TimeUnit.SECONDS);
            System.out.println("result:" + result);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("完成异步调用");
    }
}
