package com.hualr.impl;


import com.hualr.api.Service2;

/**
 * Function: 第二个演示服务的实现体 <br>
 * Creating Time: 2020/12/24 22:52 <br>
 * Version: 1.0.0 <br>
 *
 * @author zongqi
 */
public class Service2Impl implements Service2 {
    @Override
    public void action(String msg) {
        System.out.println(msg);
    }
}