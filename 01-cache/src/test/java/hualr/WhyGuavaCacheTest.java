package hualr;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * <h2>为什么用缓存工具</h2>
 * <li> 不需要自己去维护缓存,大多数场景下,维护缓存可能存在问题</li>
 */
public class WhyGuavaCacheTest {

    private final LoadingCache<String, String> cache1 = CacheBuilder.newBuilder()
            // 数据写入1分钟后失效
            .expireAfterWrite(1, TimeUnit.MINUTES)
            // 支持缓存项的数据最大为3个
            .maximumSize(3)
            //设置并发级别为8，并发级别是指可以同时写缓存的线程数
            // 设置并发级别为cpu核心数
            .concurrencyLevel(Runtime.getRuntime().availableProcessors())
            //是否需要统计缓存情况,该操作消耗一定的性能,生产环境应该去除
            .recordStats()
            //设置写缓存后n秒钟过期
            //.expireAfterWrite(60, TimeUnit.SECONDS)
            //设置读写缓存后n秒钟过期,实际很少用到,类似于expireAfterWrite
            //.expireAfterAccess(17, TimeUnit.SECONDS)
            //只阻塞当前数据加载线程，其他线程返回旧值
            //.refreshAfterWrite(13, TimeUnit.SECONDS)
            // 实现CacheLoader，当在缓存中未找到所需的缓存项时，会执行CacheLoader的load方法加载缓存。
            .build(new CacheLoader<String, String>() {
                // 方法的返回值会被缓存，注意，返回null时，会抛异常
                @Override
                public String load(String key) {
                    System.out.println("key：" + key + " 未找到，开始加载....");
                    return key + "-" + key;
                }
            });


    /**
     * 存取和加载
     *
     * @throws ExecutionException
     */
    @Test
    public void testLoadingCache() throws ExecutionException {
        // 加入缓存
        cache1.put("Java", "spring");
        cache1.put("PHP", "swoole");
        // 从缓存中获取值
        String res1 = cache1.get("Java"); // get方法需要抛出ExecutionException异常
        final String res11 = cache1.getUnchecked("Java");// 功能和get一样，但是不会throw异常
        // 输出：spring
        System.out.println(res1);
        System.out.println(res11);
        // 缓存中为存放key为Golang的缓存项，所以进行加载
        String res2 = cache1.get("Golang");
        System.out.println(res2);
        // Golang的缓存项前面已经加载过了，且没有被清除，所以这次直接获取到对应的value
        String res3 = cache1.get("Golang");
        System.out.println(res3);
        // 前面添加了3个缓存项（已经达到设置上限，此时再添加缓存项，将会触发淘汰）
        cache1.put("Node", "KOA");
        System.out.println(cache1.get("PHP")); // PHP在cache中已被淘汰

        // 查询缓存，如果未找到，不会触发加载操作，而是范围null。
        String res4 = cache1.getIfPresent("key");
        System.out.println(res4); // null
    }

    /**
     * 批量处理
     */
    @Test
    public void test2() throws ExecutionException {
        // 批量添加
        Map<String, String> map = new HashMap<>();
        map.put("one", "111111");
        map.put("two", "222222");
        map.put("three", "3333333");
        cache1.putAll(map);

        // 批量获取
        List<String> keys = new ArrayList<>();
        keys.add("one");
        keys.add("two");
        ImmutableMap<String, String> allData = cache1.getAll(keys);
        System.out.println(allData);

        // 批量清除
        cache1.invalidateAll(keys);
        // 全量清除
        cache1.invalidateAll();
    }

    /**
     * 监听器的使用
     */
    @Test
    public void test3() {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                // 绑定"移除监听器"，当元素被清除时，执行onRemoval方法
                .removalListener((RemovalListener<String, String>) removalNotification -> {
                    Object key = removalNotification.getKey();
                    Object val = removalNotification.getValue();
                    System.out.println("被清除的元素，key:" + key + ", val:" + val);
                })
                // 当在缓存中未找到key时，执行load操作。
                .build(new CacheLoader<String, String>() {
                    // 当key未找到时，执行的加载操作
                    @Override
                    public String load(String key) {
                        System.out.println("未找到key:" + key + "，开始加载...");
                        return key + "-" + key;
                    }
                });

        cache.put("one", "111111");
        cache.put("two", "123456");

        // 继续添加元素，会触发清理操作，触发移除监听器
        cache.put("three", "2233333");
        // 输出：被清除的元素，key:one, val:111111

        String res = cache.getUnchecked("four");
        System.out.println(res);
    }

    /**
     * 自动刷新key对应的值-->通常这个data可能会变
     */
    @Test
    public void test4() throws InterruptedException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                // 设置刷新的时机
                .refreshAfterWrite(2, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        System.out.println("key：" + key + " 未找到，开始加载....");
                        return key + "-" + key;
                    }
                });

        cache.put("one", "11111");
        cache.put("two", "22222");

        // 休眠3秒
        Thread.sleep(3000L);

        //并不是休眠3秒这段时间内刷新的,而是再次访问的时候刷新的
        System.out.println(cache.getUnchecked("one"));
    }

    /**
     * 刷新后的监听器操作
     */
    @Test
    public void test5() throws InterruptedException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                // 设置刷新的时机
                .refreshAfterWrite(2, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    // 刷新缓存时，执行的操作
                    @Override
                    public ListenableFuture<String> reload(String key, String oldValue) throws Exception {
                        System.out.println("刷新缓存项，key:" + key + ", oldValue:" + oldValue);
                        return super.reload(key, oldValue);
                    }

                    @Override
                    public String load(String key) {
                        System.out.println("key：" + key + " 未找到，开始加载....");
                        return key + "-" + key;
                    }
                });

        cache.put("hello", "world");
        Thread.sleep(3000L);

        System.out.println(cache.getUnchecked("hello"));
    }

    /**
     * 打印当前所有缓存
     */
    @Test
    public void test6() {
        cache1.put("1", "111");
        cache1.put("2", "111");
        cache1.put("3", "111");

        System.out.println( cache1.asMap().put("4", "111"));
        //由此可见 此时就算转化为了map PUT也是PUT入缓存cache中
        System.out.println( cache1.asMap());
    }
}
