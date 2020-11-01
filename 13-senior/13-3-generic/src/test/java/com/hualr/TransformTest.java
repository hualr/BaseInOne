package com.hualr;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/11/1 11:27
 * Version: 1.0.0
 */

/**
 * 验证泛型的使用目的: 对塞入的使用进行限制 从而在编译阶段就杜绝问题
 */
public class TransformTest {
    @Test
    public void test() {
        List array1 = new ArrayList<>();
        array1.add("aaa");
        array1.add(100);

        array1.forEach(i -> {
            System.out.println(i);
        });

        /*可以放入,可是放入之后编译很容易报错,.因此我们需要限制放入*/
        /*array1.forEach(i-> {
            System.out.println((String) i);
        });*/


        List<String> array2 = new ArrayList<>();
        array2.add("aaa");
        //在这里就会强制改变
        array2.add(String.valueOf(100));
        array2.forEach(i -> {
            System.out.println((String) i);
        });

        //验证是否是只在编译阶段有效
        List<String> array3 = new ArrayList<>();
        List<Integer> array4 = new ArrayList<>();
        Class classArray3 = array3.getClass();
        Class classArray4 = array4.getClass();

        System.out.println(classArray3.equals(classArray4));
    }
}
