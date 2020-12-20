package com.hualr;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/11/27 20:59
 * Version: 1.0.0
 */
public class LogoutEvent {
    private String userName;

    public LogoutEvent(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }


}
