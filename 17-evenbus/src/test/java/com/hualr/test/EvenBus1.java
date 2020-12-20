package com.hualr.test;

import com.google.common.eventbus.EventBus;
import com.hualr.LogoutEvent;
import com.hualr.SignInEvent;
import com.hualr.SignInProcessor;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/11/27 20:37
 * Version: 1.0.0
 */
public class EvenBus1 {
    /*  @Inject
      EventBus signInEventBus;
      @Inject
      SignInProcessor processor;
  */
    @Test
    public void eventBusTest() {
        EventBus signInEventBus = new EventBus("SignInEventBus");
        //收到消息之后的处理类
        SignInProcessor processor = new SignInProcessor();
        //处理类和消息总线保持关联
        signInEventBus.register(processor);
        // 处理类收到消息后
        /**
         * 1. 定义一个消息总线
         * 2. 某个类订阅这个消息总线
         * 3. 往消息总线里投递事件 从而所有订阅该消息总线的对象都受到信息
         */
        signInEventBus.post(new SignInEvent(2));
        signInEventBus.post(new LogoutEvent("王明"));

    }

}


