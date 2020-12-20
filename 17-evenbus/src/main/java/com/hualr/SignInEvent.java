package com.hualr;

import dagger.Component;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/11/27 20:38
 * Version: 1.0.0
 */
@Component
public class SignInEvent {
    // 签到天数
    private int count;

    public SignInEvent(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}