package com.hualr.business.provider;

import com.hualr.api.Service1;
import com.hualr.business.api.Application;
import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Function: 构造器生成类 想要构造T 那么就得将T转化为普通类型<br>
 * Creating Time: 2020/12/27 23:51 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class ServiceProvider implements Provider<Application> {
    //将inject放在这里是没用的
    private final Service1 service1;

    @Inject
    public ServiceProvider(Service1 service1) {
        this.service1 = service1;
    }

    @Override
    public Application get() {
        System.out.println(" 我要拿到一个App类了=> doSomething");
        return new MyApp(service1);
    }
}
