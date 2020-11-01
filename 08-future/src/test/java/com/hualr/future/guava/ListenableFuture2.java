package com.hualr.future.guava;

import static java.util.concurrent.Executors.newCachedThreadPool;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.jupiter.api.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/11/1 10:40
 * Version: 1.0.0
 */
public class ListenableFuture2 {
    // 创建线程池
    final static ListeningExecutorService service = MoreExecutors.listeningDecorator(newCachedThreadPool());


    @Test
    //@RepeatedTest(10)
    public void test1() throws InterruptedException {
        // 某个任务
        ListenableFuture<String> stringTask = service.submit(() -> "Hello World");

        Futures.addCallback(stringTask, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Thread.sleep(200L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("完成addCallback异步操作1");
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
    }

    @Test
    public void test2() throws InterruptedException {
        ListenableFuture<String> stringTask = service.submit(() -> "Hello World");
        stringTask.addListener(() -> {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("完成addListener异步操作1");
        }, newCachedThreadPool());

        Thread.sleep(400L);
    }

}
