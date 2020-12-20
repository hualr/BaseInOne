package com.hualr.stream.steam;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/12/11 14:02
 * Version: 1.0.0
 */
public class CompareDemo {
    @Test
    public void test() {
        //List<Integer> sum = Arrays.asList(6, 1);
        List<Integer> sum = Arrays.asList(1, 6);
        sum.sort((o1, o2) -> {
            if (o1 == 1) {
                return -1;
            }
            return 0;
        });
        System.out.println(sum);
    }
}
