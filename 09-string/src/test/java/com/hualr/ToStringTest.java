package com.hualr;

import com.google.common.base.MoreObjects;
import org.junit.Test;

/**
 * Function: <br>
 * Creating Time: 2021/1/21 10:31 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class ToStringTest {
    /**
     * 自定义打印自己想要的信息
     */
    @Test
    public void test1() {
        System.out.println(MoreObjects.toStringHelper(this).toString());
        System.out.println(MoreObjects.toStringHelper(this)
                .add("x", 1)
                .add("x", 1)
                .add("m", 1)
                .add("m", 2)
                .toString());

    }
}
