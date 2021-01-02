package com.hualr.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * Author: zongqi.<br>
 * Function: <br>
 * Creating Time: 2020/12/20 22:27 <br>
 * Version: 1.0.0 <br>
 */
public class CountDownLatchDemo {
    /**
     * <h2>用法1 某个程序需要等前N个线程全部执行完毕后才能执行</h2> <br>
     * <li>将CountDownLatch的计数器初始化为CountDownLatch(N)</li>
     * <li>每个程序执行完毕,九江计数器减1</li>
     * <li>当计数器变为0 目前await的线程就会被唤醒</li>
     * <li>典型场景: 主线程等待所有线程完成后继续</li>
     */
    @Test
    public void test() throws InterruptedException {
        CountDownLatch sonLatch = new CountDownLatch(10);

        for (int i = 0; i < 9; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 运行");
                //即使主线程抛出了异常 子线程也必须try catch 因为这些都不在主线程的掌握之中
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    sonLatch.countDown();
                }
            }).start();
        }

        System.out.println("等待所有子线程运行结束");
        sonLatch.await(10, TimeUnit.SECONDS);
        System.out.println("所有子线程运行结束");
    }


    /**
     * <h2> 使用场景2: 测试最大并发情况</h2>
     * <li> 主线程和子线程都存在一个countdownLatch</li>
     * <li> 子线程全部wait 主线程的countDown</li>
     * <li> 主线程一旦执行完之后就countDown</li>
     * <Li> 子线程完成后也countDown 从而不再阻碍主线程</Li>
     */
    @Test
    public void test2() throws InterruptedException {
        CountDownLatch mainThread = new CountDownLatch(1);
        CountDownLatch sonThread = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    //等待主线程完成后子线程才可以进行下去
                    mainThread.await();
                    System.out.println("子线程" + Thread.currentThread().getName() + "处理自己事情");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    sonThread.countDown();
                }
            }).start();
        }

        System.out.println("主线程处理自己事情 花费30秒钟");
        Thread.sleep(30000);
        mainThread.countDown();
        System.out.println("主线程处理结束 之后所有子线程全部开始自己的事情");
        sonThread.await();
        System.out.println("子线程处理完毕啦");
    }


}
