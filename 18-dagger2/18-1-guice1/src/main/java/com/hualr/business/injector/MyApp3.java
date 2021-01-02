package com.hualr.business.injector;

import com.hualr.api.Service1;
import com.hualr.business.api.Application;
import javax.inject.Inject;

/**
 * Function: <br>
 * Creating Time: 2020/12/27 23:13 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class MyApp3 implements Application {
    Service1 service1;


    @Inject
    public void setService3(Service1 service1) {
        this.service1 = service1;
    }

    @Override
    public void work() {
        System.out.println("setter 注入");
        service1.action("");
    }
}
