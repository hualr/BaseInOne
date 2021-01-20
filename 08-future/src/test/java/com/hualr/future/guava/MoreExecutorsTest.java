package com.hualr.future.guava;

import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

public class MoreExecutorsTest {
    /**
     * 给线程池增加一个关闭钩子
     * 在线程池中的线程是守护线程(daemon thread)时有用，用户线程(user thread)
     * 执行完后，jvm不会立即关闭，而是等待一定时间。
     * 正常情况下，只要用户线程一结束，jvm就会立即关闭，而不管守护线程任务是否执行完毕。
     * 从这里也可以看出，尽量不要把自定义线程搞成守护线程，不然是作死。
     */
    @Test
    public void test1(){
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                                    .setDaemon(true)
                                    .setNameFormat("async-pool-%d")
                                    .build();
        ExecutorService executorService = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MINUTES, new LinkedBlockingQueue<>(3000), threadFactory);
        executorService.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "@666");
        });
        System.out.println(Thread.currentThread().getName() + "@888");
        MoreExecutors.addDelayedShutdownHook(executorService, 3000, TimeUnit.MILLISECONDS);
        System.out.println(Thread.currentThread().getName() + "@999");
    }

    /**
     *
     */
    @Test
    public void test2(){
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("async-pool-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MINUTES, new LinkedBlockingQueue<>(3000), threadFactory);
        ExecutorService executorService = MoreExecutors.getExitingExecutorService(threadPoolExecutor);
        executorService.submit(() -> {
            try {
                Thread.sleep(110000);
                System.out.println(Thread.currentThread().getName() + "@666");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(Thread.currentThread().getName() + "@888");
    }
}
