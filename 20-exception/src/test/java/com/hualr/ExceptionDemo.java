package com.hualr;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * Function: 展示不同的异常<br>
 * Creating Time: 2020/12/30 21:25 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class ExceptionDemo {
    private static final List<String> list = new ArrayList<>();

    /**
     * 堆栈溢出异常
     */
    @Test
    public void test1() {

        try {
            handlerStackOverflow(list);
        } catch (StackOverflowError error) {
            System.out.println("can not handler stackOverFlow");
        }
        try {
            handlerStackOverflow(new ArrayList<>());
        } catch (StackOverflowError error) {
            System.out.println("can not handler stackOverFlow");
        }
    }

    private void handlerStackOverflow(List<String> strings) {
        strings.add("1");
        handlerStackOverflow(strings);
    }
}
