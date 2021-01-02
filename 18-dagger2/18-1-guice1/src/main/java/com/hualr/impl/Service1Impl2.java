package com.hualr.impl;

import com.hualr.api.Service1;

/**
 * Function: <br>
 * Creating Time: 2020/12/27 17:11 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class Service1Impl2 implements Service1 {

    @Override
    public void action(String msg) {
        System.out.println("接口1的第二类实现:" + msg);
    }
}
