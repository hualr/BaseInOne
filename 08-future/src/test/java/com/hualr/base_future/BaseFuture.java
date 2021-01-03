package com.hualr.base_future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.Test;

/**
 * Function: 关于Future需要知道的一切<br>
 * Creating Time: 2021/1/2 12:05 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class BaseFuture {

    /**
     * Future最基础的使用演示 这里实际上四一个同步阻塞的过程
     */
    @Test
    public void test1() {
        ExecutorService service = Executors.newFixedThreadPool(10);
        //submit和excute的比较
        Future<String> future = service.submit(() -> {
            Thread.sleep(1000);
            System.out.println("线程" + Thread.currentThread().getName());
            return "完成另外一个线程的调用";
        });
        try {
            System.out.println("线程" + Thread.currentThread().getName());
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e);
        }
        service.shutdown();
    }
}
