package com.hualr;

import com.google.common.eventbus.Subscribe;
import dagger.Module;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/11/27 20:39
 * Version: 1.0.0
 */

@Module
public class SignInProcessor {
    //订阅者
    @Subscribe
    public void signIn(SignInEvent event) {
        int count = event.getCount();
        // doSomething
        System.out.println("签到" + count + "天");
    }

    @Subscribe
    public void logout(LogoutEvent event) {
        // 获取登出的时间
        String userName = event.getUserName();
        System.out.println(userName);
    }

}