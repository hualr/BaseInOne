package com.hualr.impl;


import com.hualr.api.Service1;

/**
 * Function: 第一个演示服务的实现体<br>
 * Creating Time: 2020/12/24 22:51 <br>
 * Version: 1.0.0 <br>
 *
 * @author zongqi
 */
public class Service1Impl1 implements Service1 {

    @Override
    public void action(String msg) {
        System.out.println("seviceImp11" + msg);
    }
}
