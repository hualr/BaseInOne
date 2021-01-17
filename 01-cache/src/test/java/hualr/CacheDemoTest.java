package hualr;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * Author: zongqi<br>
 * Function:Caffeine<br>
 * Creating Time：2020/10/26 22:33 <br>
 * Version: 1.0.0 <br>
 */
public class CacheDemoTest {
    /**
     * 初级guavaCache构造
     */
    @Test
    public void test1() {
        Cache<Object, Object> manualCache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .build();
        System.out.println(manualCache);

    }
}
