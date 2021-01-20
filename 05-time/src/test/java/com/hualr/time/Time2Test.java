package com.hualr.time;

import java.time.Duration;
import java.time.Instant;
import org.junit.Test;

public class Time2Test {
    /**
     *  一段代码的运行时间
     */
    @Test
    public void test1() throws InterruptedException {
        Instant start = Instant.now();
        Thread.sleep(3000);
        System.out.println("程序运行的时间为" + Duration.between(start, Instant.now()).getSeconds());
    }
}
