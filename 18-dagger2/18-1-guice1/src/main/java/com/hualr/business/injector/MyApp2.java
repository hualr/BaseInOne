package com.hualr.business.injector;

import com.hualr.api.Service1;
import com.hualr.api.Service2;
import com.hualr.business.api.Application;
import javax.inject.Inject;

/**
 * Function: 默认无参构造器注入<br>
 * Creating Time: 2020/12/27 12:48 <br>
 *
 * @author zongqi
 * @version: 1.0.0 <br>
 */
public class MyApp2 implements Application {
    private static int Index;

    @Inject
    private Service1 service1;
    @Inject
    private Service2 service2;

    @Override
    public void work() {
        System.out.println("默认构造器注入" + Index);
        service1.action("service1 action");
        service2.action("service2 action");
    }
}
