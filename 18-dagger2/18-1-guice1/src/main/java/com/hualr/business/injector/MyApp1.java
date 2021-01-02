package com.hualr.business.injector;

import com.hualr.api.Service1;
import com.hualr.api.Service2;
import com.hualr.business.api.Application;
import javax.inject.Inject;
import javax.inject.Singleton;

// ZNN 这个注解可以用google和java自带的都行

/**
 * Function: 这个类将同时使用Service1 和Service2 传统方式是new 现在采用构造器注入的方式,关注的重点是构造器<br>
 * Creating Time: 2020/12/24 22:54 <br>
 * Version: 1.0.0 <br>
 *
 * @author zongqi
 */
@Singleton
public class MyApp1 implements Application {
    private final Service1 service1;
    private final Service2 service2;


    /**
     * <h2>注入构造器 当自动注入的时候,只能有一个构造器处于此状态</h2>
     */
    @Inject
    public MyApp1(Service1 service1, Service2 service2) {
        this.service1 = service1;
        this.service2 = service2;
    }
    // 注入构造器

    /**
     * 该构造器并不能和第一个构造器一起注入,有1就没有2 有2就没有1.且在当前状态下,无法直接注入flag 原因是目前flag无法被直接注入
     *
     * @param service1 注入服务1
     * @param service2 注入服务2
     * @param flag     某个boolean变量,以此搞事情
     */
    public MyApp1(Service1 service1, Service2 service2, boolean flag) {
        this.service1 = service1;
        this.service2 = service2;
        System.out.println("构造器注入的值为" + flag);
    }

    /**
     * 在new 该对象的时候,调用work方法从而观察现象
     */
    @Override
    public void work() {
        service1.action("service1 action");
        service2.action("service2 action");
    }
}