package com.hualr.api;

import com.google.inject.ImplementedBy;
import com.hualr.impl.Service3Impl;

/**
 * Function:  第三个服务接口 和前面不同的是,改接口直接确定了一旦要注入,那么就默认注入的实现类未注解中声明的,一般不用,因为在写接口的时候,其实是无法判断到底其实现是什么的<br>
 * Creating Time: 2020/12/27 22:56 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
@ImplementedBy(Service3Impl.class)
public interface Service3 {
    void action(String str);
}
