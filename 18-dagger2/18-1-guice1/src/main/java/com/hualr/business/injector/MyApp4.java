package com.hualr.business.injector;

import com.google.inject.Inject;
import com.hualr.api.Service1;
import com.hualr.business.api.Application;
import com.hualr.impl.Service1Impl1;


/**
 * Function: <br>
 * Creating Time: 2020/12/29 21:58 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class MyApp4 implements Application {

    /**
     * 选项注入只有在guava自带的注解中有 表示的是如果没有绑定了 那么默认就是new出来的对象
     */
    @Inject(optional = true)
    private Service1 service1 = new Service1Impl1();

    @Override
    public void work() {
        System.out.println("MyApp4");
        service1.action("hi");
    }
}
