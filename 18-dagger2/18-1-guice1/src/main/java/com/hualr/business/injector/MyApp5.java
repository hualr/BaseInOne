package com.hualr.business.injector;

import com.hualr.business.api.Application;
import javax.inject.Named;

/**
 * Function: <br>
 * Creating Time: 2020/12/30 22:53 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class MyApp5 implements Application {
    private String s;

    public MyApp5(@Named("str") String s) {
        this.s = s;
    }

    @Override
    public void work() {
        System.out.println(s);
    }
}
