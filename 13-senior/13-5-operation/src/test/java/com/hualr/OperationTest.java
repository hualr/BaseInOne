package com.hualr;

import org.junit.Assert;
import org.junit.Test;

/**
 * Function: 关于java的计算<br>
 * Creating Time: 2021/2/12 <br>
 * Version: 1.0.0
 *
 * @author 宗旗
 */
public class OperationTest {
    /**
     *  位运算
     */
    @Test
    public void test1(){
        Assert.assertTrue(true ^ false);
        Assert.assertTrue(false ^ true);
        Assert.assertFalse(true ^ true);
        Assert.assertFalse(false ^ false);
    }

}
