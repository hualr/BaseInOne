package com.hualr.future.guava;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Executors;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/11/1 10:38
 * Version: 1.0.0
 */
public class ListenableFuture1 {
    @Test
    public void test1() throws InterruptedException {
        /**
         * ListeningExecutorService->ExecutorService的扩展，重新ExecutorService类中的submit方法，返回ListenableFuture对象。
         *  MoreExecutors->该类是final类型的工具类，提供了很多静态方法。比如ListeningDecorator方法初始化ListeningExecutorService方法
         */
        //1. 定义线程池
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<String> explosion = service.submit(() -> {
            try {
                System.out.println("开始A操作");
                Thread.sleep(10000L);
                System.out.println("完成A操作");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "完成A调用";
        });
        System.out.println("开始B操作");
        Thread.sleep(1000L);
        System.out.println("完成B操作");

        //不会阻塞C操作,只是一个监听器
        Futures.addCallback(
                explosion,
                new FutureCallback<String>() {
                    // we want this handler to run immediately after we push the big red button!
                    @Override
                    public void onSuccess(String explosion) {
                        System.out.println("成功" + explosion);
                    }

                    @Override
                    public void onFailure(Throwable thrown) {
                        System.out.println("失败"); // escaped the explosion!
                    }
                },
                service);

        System.out.println("开始C操作");
    }
}
