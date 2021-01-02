package com.hualr.business.injector;

import com.hualr.api.Service1;
import com.hualr.business.api.Application;
import javax.inject.Inject;

/**
 * Function: <br>
 * Creating Time: 2021/1/2 11:07 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class MyApp6 implements Application {
    private static String str;
    @Inject
    private Service1 service1;

    @Override
    public void work() {
        System.out.println(str);
        service1.action("service1 启动");
    }
}
