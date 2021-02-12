package com.hualr;

import java.util.List;
import org.junit.Test;

/**
 * Function: instanceof demo<br>
 * Creating Time: 2021/2/12 <br>
 * Version: 1.0.0
 *
 * @author 宗旗
 */
public class InstanceofTest {
    //instanceOf无法靠近为null
    @Test
    public void test1() {
        System.out.println(null instanceof List);
    }


}
