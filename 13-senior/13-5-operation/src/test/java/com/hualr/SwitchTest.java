package com.hualr;

import org.junit.Test;

/**
 * Function: switch规范<br>
 * Creating Time: 2021/2/12 <br>
 * Version: 1.0.0
 *
 * @author 宗旗
 */
public class SwitchTest {
    @Test
    public void test7() {
        String str = null;
        switch (str) {
            case "A":
                System.out.println("A");
                break;
            case "B":
                System.out.println("B");
                break;
            default:
                System.out.println("C");

        }
    }
}
