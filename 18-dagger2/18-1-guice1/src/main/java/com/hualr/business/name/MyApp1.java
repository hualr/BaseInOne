package com.hualr.business.name;

import com.hualr.api.Service1;
import com.hualr.business.api.Application;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Function: <br>
 * Creating Time: 2020/12/27 17:09 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class MyApp1 implements Application {
    @Inject
    @Named("service1_1")
    private Service1 service11;
    @Inject
    @Named("service1_2")
    private Service1 service12;

    @Override
    public void work() {
        System.out.println("默认构造器注入");
        service11.action("service1 action");
        service12.action("service1 action");
    }
}
