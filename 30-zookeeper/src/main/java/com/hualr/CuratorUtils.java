package com.hualr;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;

/**
 * Function: curator工具类<br>
 * Creating Time: 2021/4/4 <br>
 * Version: 1.0.0
 *
 * @author 宗旗
 */
@Data
@Slf4j
public class CuratorUtils {

    private CuratorFramework client;
    private static final String zkServerPath = "192.168.142.128:33640";
    private static final Integer timeout = 5000;
    private final static String ADD_PATH = "/super/imooc/d";


    /**
     * 实例化zk客户端
     *  同步创建zk示例，原生api是异步的
     * curator链接zookeeper的策略:ExponentialBackoffRetry
     * baseSleepTimeMs：初始sleep的时间
     * maxRetries：最大重试次数
     * maxSleepMs：最大重试时间
     */
    public CuratorUtils() {
        RetryPolicy retryPolicy = new RetryNTimes(3, 5000);
        client = CuratorFrameworkFactory.builder()
                .connectString(zkServerPath)
                .sessionTimeoutMs(10000).retryPolicy(retryPolicy)
                .namespace("workspace").build();
        client.start();
    }

    public void closeZKClient() {
        if (client != null) {
            this.client.close();
        }
    }



}
