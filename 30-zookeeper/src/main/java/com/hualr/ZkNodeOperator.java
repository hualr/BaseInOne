package com.hualr;

import com.google.common.base.Strings;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;

/**
 * Function: Zk操作<br>
 * Creating Time: 2021/4/3 <br>
 * Version: 1.0.0
 *
 * @author 宗旗
 */

@Slf4j
@Data
public class ZkNodeOperator implements Watcher {
    private ZooKeeper zooKeeper = null;
    public static final String zkServerPath = "192.168.142.128:33640";
    public static final Integer timeout = 5000;

    public ZkNodeOperator() {

    }

    @SneakyThrows
    public ZkNodeOperator(String connectString) {
        if (Strings.isNullOrEmpty(connectString)) {
            connectString = zkServerPath;
        }
        try {
            zooKeeper = new ZooKeeper(connectString, timeout, new ZkNodeOperator());
            TimeUnit.SECONDS.sleep(30);
        } catch (IOException e) {
            if (zooKeeper != null) {
                try {
                    zooKeeper.close();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }
    }

    public void createZKNode(String path, byte[] data, List<ACL> acls) {
        String result = "";
        try {
            result = zooKeeper.create(path, data, acls, CreateMode.EPHEMERAL);
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
        log.info("创建的结果为:[{}]", result);
    }

    public void createAyncZKNode(String path, byte[] data, List<ACL> acls) {
        String ctx = "{'create':'success'}";
        zooKeeper.create(path, data, acls, CreateMode.PERSISTENT, (i, s, o, s1) -> {
                    log.info("创建成功:[{}],[{}],[{}],[{}]", i, s, o, s1);
                }, ctx);
    }

    @Override
    public void process(final WatchedEvent watchedEvent) {
        if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
          log.info("nodechildrenchanged");
        } else if (watchedEvent.getType() == Event.EventType.NodeCreated) {
            log.info("节点创建");
            //countDown.countDown();
        } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
            log.info("节点数据改变");
            //countDown.countDown();
        } else if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
            log.info("节点删除");
            //countDown.countDown();
        }
    }
}
