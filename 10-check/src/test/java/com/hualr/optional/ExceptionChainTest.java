package com.hualr.optional;

import java.util.Objects;
import org.junit.Test;

/**
 * Function: 异常链打印方法<br>
 * Creating Time: 2021/2/12 <br>
 * Version: 1.0.0
 *
 * @author 宗旗
 */
public class ExceptionChainTest {
    /**
     * 异常链打印方法
     */
    @Test
    public void test() {
        try {
            String a = chain1();
           /* if (a == null) {
                throw new NullPointerException();
            }*/
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String chain1() {
        Objects.requireNonNull(null);
        return chain2();
    }

    private String chain2() {
        return null;
    }

}
