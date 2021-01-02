package com.hualr;

import com.google.inject.AbstractModule;
import com.hualr.api.Service1;
import com.hualr.api.Service2;
import com.hualr.business.api.Application;
import com.hualr.business.injector.MyApp1;
import com.hualr.impl.Service1Impl1;
import com.hualr.impl.Service2Impl;

/**
 * Function: 调用AbstractModule类提供的一些方法来配置依赖关系 实际充当注册中心 <br>
 * Creating Time: 2020/12/24 22:55 <br>
 * Version: 1.0.0 <br>
 *
 * @author zongqi
 */
public class MyApp1Module1 extends AbstractModule {

    @Override
    protected void configure() {
        //使用Service1 注入就new Service1Impl
        bind(Service1.class).to(Service1Impl1.class);
        //使用Service2 注入就new Service2Impl
        bind(Service2.class).to(Service2Impl.class);
        bind(Application.class).to(MyApp1.class);
    }
}
