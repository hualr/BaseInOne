package com.hualr.business.name;

import com.hualr.api.Service1;
import com.hualr.business.api.Application;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Function: <br>
 * Creating Time: 2020/12/29 22:26 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class MyApp2 implements Application {

    private Service1 service1;

    @Inject
    public MyApp2(@Named("service1") Service1 service1) {
        this.service1 = service1;
    }

    @Override
    public void work() {
        service1.action("hei");
    }
}
