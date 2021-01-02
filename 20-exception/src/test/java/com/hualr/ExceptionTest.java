package com.hualr;

import java.util.ArrayList;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/12/9 23:05
 * Version: 1.0.0
 */
public class ExceptionTest {
    @Test
    public void test1() {
        //throwException();
        boolean b = catchException();
        System.out.println(b);
        System.out.println(1);
    }


    private boolean catchException() {
        //如果异常被捕获,那么程序继续往后执行
        boolean flag = false;
        ArrayList<String> list = new ArrayList<>();
        try {
            list.add("网民");
            flag = true;
            throwException1();
        } catch (NullPointerException e) {
            System.out.println("捕获到一个异常信息");
        }
        System.out.println(list);
        System.out.println(flag);
        return false;
    }

    private void throwException1() {
        String s = null;
        if (s.equals("b")) {

        }
        System.out.println("抛出异常不会打印后面的语句");
    }

    /**
     *
     */
    @Test
    public void test() {

    }
}
