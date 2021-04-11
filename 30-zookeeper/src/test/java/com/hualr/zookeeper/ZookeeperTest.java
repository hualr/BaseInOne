package com.hualr.zookeeper;

import com.hualr.MyWatcher;
import com.hualr.ZkNodeOperator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

/**
 * Function: 原生java连接<br>
 * Creating Time: 2021/4/3 <br>
 * Version: 1.0.0
 *
 * @author 宗旗
 */

@Slf4j
public class ZookeeperTest {
    public static final String zkServerPath = "192.168.142.128:33640";
    public static final Integer timeout = 5000;
    public ZkNodeOperator zkServer;

    @Before
    public void beforeTest(){
        zkServer = new ZkNodeOperator(zkServerPath);
    }

    /**
     * 普通链接
     */
    @Test
    @SneakyThrows
    public void test1(){
        ZooKeeper zooKeeper = new ZooKeeper(zkServerPath, timeout, new MyWatcher());
        log.debug("客户端开始连接zookeeper服务器,[{}]",zooKeeper.getState());
        TimeUnit.SECONDS.sleep(60);
        log.debug("连接状态位:[{}]",zooKeeper.getState());
    }

    /**
     * 恢复之前的会话
     */
    @Test
    @SneakyThrows
    public void test2(){
        ZooKeeper zooKeeper = new ZooKeeper(zkServerPath, timeout, new MyWatcher());
        log.debug("客户端开始连接zookeeper服务器,[{}]",zooKeeper.getState());
        final long sessionId = zooKeeper.getSessionId();
        log.info("session id is :[{}]",Long.toHexString(sessionId));
        final byte[] sessionPasswd = zooKeeper.getSessionPasswd();
        TimeUnit.SECONDS.sleep(30);
        log.debug("连接状态位:[{}]",zooKeeper.getState());
        log.info("开始重新连接....");
        final ZooKeeper zkSession = new ZooKeeper(zkServerPath, timeout, new MyWatcher(), sessionId, sessionPasswd);
        log.info("重新连接状态位:[{}]",zkSession.getState());
        TimeUnit.SECONDS.sleep(30);
        log.info("重新连接状态:[{}]",zkSession.getState());
    }

    /**
     *  zookeeper 同步写入
     */
    @Test
    public void test3(){
        ZkNodeOperator zkServer = new ZkNodeOperator(zkServerPath);
        zkServer.createZKNode("/testnode", "testnode".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE);
    }

    @Test
    public void test4() throws InterruptedException {
        ZkNodeOperator zkServer = new ZkNodeOperator(zkServerPath);
        zkServer.createAyncZKNode("/testnode1", "testnode".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE);
        TimeUnit.SECONDS.sleep(10);
    }

    /**
     *
     */
    @Test
    public void test5() throws KeeperException, InterruptedException {
        ZkNodeOperator zkServer = new ZkNodeOperator(zkServerPath);
        //zkServer.createAyncZKNode("/testnode2", "testnode".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE);
        final Stat stat = zkServer.getZooKeeper().setData("/testnode2", "hello".getBytes(), 0);
    }

    /**
     *
     */
    @Test
    public void test6() {
        ZkNodeOperator zkServer = new ZkNodeOperator(zkServerPath);
        // zkServer.createAyncZKNode("/testnode2", "testnode".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE);
        zkServer.getZooKeeper().delete("/testnode2", 2, (i, s, o) ->
                log.info("i:[{}],s:[{}],o:[{}]",i,s,o),"over");
    }

    CountDownLatch countDownLatch=new CountDownLatch(1);
    /**
     *  
     */
    @Test
    public void test7() throws KeeperException, InterruptedException {
        ZkNodeOperator zkServer = new ZkNodeOperator(zkServerPath);

        final Stat exists = zkServer.getZooKeeper().exists("/testnode", true);
        if (exists!=null){
            log.info("当前节点的版本为:[{}]",exists.getVersion());
        }else{
            log.info("该节点不存在");
        }
        countDownLatch.await();
    }

    /**
     * 同步获取节点信息
     */
    @Test
    @SneakyThrows
    public void test8(){
        final List<String> children = zkServer.getZooKeeper().getChildren("/", true);
        for (String child:children){
            log.info(child);
        }
    }
    @Test
    @SneakyThrows
    public void test9(){
        String ctx = "{'callback':'ChildrenCallback'}";
        zkServer.getZooKeeper().getChildren("/", true, (rc, path, ctx1, list, stat) -> {
            for (String s : list) {
                System.out.println(s);
            }
            System.out.println("ChildrenCallback:" + path);
            System.out.println((String) ctx1);
            System.out.println(stat.toString());
        }, ctx);

    }


}
