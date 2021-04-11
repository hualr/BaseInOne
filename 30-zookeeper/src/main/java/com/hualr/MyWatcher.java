package com.hualr;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * Function: zookeeper demo<br>
 * Creating Time: 2021/4/3 <br>
 * Version: 1.0.0
 *
 * @author 宗旗
 */
@Slf4j
public class MyWatcher implements Watcher{

    @Override
    public void process(final WatchedEvent watchedEvent) {
        log.info("接收到watch的通知:[{}]",watchedEvent);
    }
}
