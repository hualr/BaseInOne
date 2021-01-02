package com.hualr.business.provider;

import com.hualr.api.Service1;
import com.hualr.business.api.Application;
import java.util.Objects;
import javax.inject.Inject;

/**
 * Function: <br>
 * Creating Time: 2020/12/28 0:18 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class MyApp implements Application {


    private Service1 service1;
    private String index;

    @Inject
    public MyApp(Service1 service1) {
        this.service1 = service1;
    }


    public MyApp(String index) {
        this.index = index;
    }


    @Override
    public void work() {
        System.out.println("provider使用,index=" + index);
        if (Objects.nonNull(service1)) {
            service1.action("hi");
        }
    }

}
