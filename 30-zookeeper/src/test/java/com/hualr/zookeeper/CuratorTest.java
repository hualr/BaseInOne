package com.hualr.zookeeper;

import com.hualr.CuratorUtils;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Function: curator test<br>
 * Creating Time: 2021/4/4 <br>
 * Version: 1.0.0
 *
 * @author 宗旗
 */
@Slf4j
public class CuratorTest {
    private CuratorUtils curatorUtils;
    private String nodePath = "/super/imooc";
    private byte[] data = "superme".getBytes();
    private final static String ADD_PATH = "/super/imooc/d";


    @Before
    public void beforeTest(){
        curatorUtils = new CuratorUtils();

    }

    /**
     *
     */
    @Test
    public void test1(){
        // 实例化
        boolean isZkCuratorStarted=curatorUtils.getClient().isStarted();
        log.info("当前客户的状态:[{}]",isZkCuratorStarted );
    }

    /**
     *
     */
    @Test
    public void test2() throws Exception {
        // 创建节点
        curatorUtils.getClient().create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath(nodePath, data);
    }

    /**
     * 读取版本号
     */

    @Test
    public void test3() throws Exception {
        Stat stat = new Stat();
        data = curatorUtils.getClient().getData().storingStatIn(stat).forPath(nodePath);
        log.info("节点:[{}]的数据为:[{}],版本为:[{}]",nodePath,new String(data),stat.getVersion());
    }
    /**
     * update 数据
     */
    @Test
    public void test4() throws Exception {
        // 更新节点数据
        Stat stat = new Stat();
        curatorUtils.getClient().getData().storingStatIn(stat).forPath(nodePath);
        byte[] newData = "batman".getBytes();
        curatorUtils.getClient().setData().withVersion(stat.getVersion()).forPath(nodePath, newData);
    }

    /**
     * 删除节点
     */
    @Test
    public void test5() throws Exception {
        Stat stat = new Stat();
        curatorUtils.getClient().getData().storingStatIn(stat).forPath(nodePath);
        curatorUtils.getClient().delete()
                .guaranteed()					// 如果删除失败，那么在后端还是继续会删除，直到成功
                .deletingChildrenIfNeeded()	// 如果有子节点，就删除
                .withVersion(stat.getVersion())
                .forPath(nodePath);
    }


    /**
     *  查询子节点
     */
    @Test
    public void test6() throws Exception {
        List<String> childNodes = curatorUtils.getClient().getChildren()
                .forPath("/");
        log.info("开始打印子节点：");
        childNodes.forEach(log::info);
    }
    
    /**
     *  
     */
    @Test
    public void test7() throws Exception {
        // 判断节点是否存在,如果不存在则为空
        Stat statExist = curatorUtils.getClient().checkExists().forPath(nodePath + "/abc");
        log.info(statExist.toString());
    }
    
    /**
     *   只能监听一次 监听到第一次之后监听功能被销毁
     */
    @Test
    public void test8() throws Exception {
        // watcher 事件  当使用usingWatcher的时候，监听只会触发一次，监听完毕后就销毁
        curatorUtils.getClient().getData()
                .usingWatcher((Watcher) watchedEvent -> log.info("my event is :[{}]",watchedEvent.getPath()))
                .forPath(nodePath);
        Thread.sleep(100000);
    }

    /**
     * 可以持续监听的监听器
     */
    @Test
    public void test9() throws Exception {
        final NodeCache nodeCache = new NodeCache(curatorUtils.getClient(), nodePath);
        // buildInitial : 初始化的时候获取node的值并且缓存 如果不是true 那么就默认不缓存
        nodeCache.start(true);
        if (nodeCache.getCurrentData() != null) {
            log.info("节点初始化数据为：:[{}]",new String(nodeCache.getCurrentData().getData()));
        } else {
            log.info("节点初始化数据为空...");
        }
    }

    /**
     * 添加监听器
     */
    @Test
    public void test10() throws Exception {
        final NodeCache nodeCache = new NodeCache(curatorUtils.getClient(), nodePath);
        nodeCache.start(true);
        nodeCache.getListenable().addListener(() -> {
            String data = nodeCache.getCurrentData() == null
                    ? null : new String(nodeCache.getCurrentData().getData());
            if (data == null) {
                return;
            }
            log.info("节点路径：[{}] ,数据为:[{}]", nodeCache.getCurrentData().getPath(), data);
        });


    }

    /**
     *
     */
    @Test
    public void test11() throws Exception {
        // 为子节点添加watcher
        // PathChildrenCache: 监听数据节点的增删改，会触发事件
        String childNodePathCache =  nodePath;
        // cacheData: 设置缓存节点的数据状态
        final PathChildrenCache childrenCache = new PathChildrenCache(curatorUtils.getClient(), childNodePathCache, true);
        /**
         * StartMode: 初始化方式
         * POST_INITIALIZED_EVENT：异步初始化，初始化之后会触发事件
         * NORMAL：异步初始化
         * BUILD_INITIAL_CACHE：同步初始化
         */
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        List<ChildData> childDataList = childrenCache.getCurrentData();
        System.out.println("当前数据节点的子节点数据列表：");
        for (ChildData cd : childDataList) {
            String childData = new String(cd.getData());
            log.info(childData);
        }

    }

    /**
     *
     */
    @Test
    public void test12() throws Exception {
        String childNodePathCache =  nodePath;
        final PathChildrenCache childrenCache = new PathChildrenCache(curatorUtils.getClient(), childNodePathCache, true);
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        childrenCache.getListenable().addListener((client, event) -> {
            if(event.getType().equals(PathChildrenCacheEvent.Type.INITIALIZED)){
                log.info("子节点初始化ok...");
                return;
            }
            if(event.getType().equals(PathChildrenCacheEvent.Type.CHILD_ADDED)){
                String path = event.getData().getPath();
                if (path.equals(ADD_PATH)) {
                    System.out.println("添加子节点:" + event.getData().getPath());
                    System.out.println("子节点数据:" + new String(event.getData().getData()));
                } else if (path.equals("/super/imooc/e")) {
                    System.out.println("添加不正确...");
                }

            }else if(event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)){
                System.out.println("删除子节点:" + event.getData().getPath());
            }else if(event.getType().equals(PathChildrenCacheEvent.Type.CHILD_UPDATED)){
                System.out.println("修改子节点路径:" + event.getData().getPath());
                System.out.println("修改子节点数据:" + new String(event.getData().getData()));
            }
        });
        Thread.sleep(100000000);
    }

    @After
    public void close(){
        curatorUtils.closeZKClient();
        boolean isZkCuratorStarted2 = curatorUtils.getClient().isStarted();
        System.out.println("当前客户的状态：" + (isZkCuratorStarted2 ? "连接中" : "已关闭"));

    }
}
