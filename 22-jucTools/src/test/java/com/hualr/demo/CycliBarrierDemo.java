package com.hualr.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import org.junit.Test;

/**
 * Author: zongqi.<br>
 * Function: <br>
 * Creating Time: 2020/12/21 21:38 <br>
 * Version: 1.0.0 <br>
 */
public class CycliBarrierDemo {
    /**
     * <h2>理解屏障的功能</h2>
     * <li>每个线程执行完某些事情后,因为await而等着</li>
     * <li>当到达屏障确定的发车时间后,屏障解开,先执行屏障的线程</li>
     * <li> 我目前只能理解确定多少个人就发车的行为 对于其他我目前理解很少</li>
     */
    @Test
    public void test() throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(5, () -> {
            try {
                System.out.println("5个了,满人");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("完成就坐,接下来等着");
                    barrier.await();
                    //System.out.println("出发了");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.currentThread().join();
    }


}
