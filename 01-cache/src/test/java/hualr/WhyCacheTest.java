package hualr;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

/**
 * <h2>为何使用缓存</h2>
 * <li>对内存本身要求不大 可以牺牲内存来换取速度</li>
 * <li>某些资源经常重复访问 而且非常消耗CPU或者IO</li>
 * <li>缓存中存放的数据总量不会超过内存总量</li>
 */
public class WhyCacheTest {
    /**
     *  不使用缓存的场景
     */
    @Test
    public void test1(){
        for (int i = 0; i < 10000; i++)
            System.out.println("f(" + i + ") = " + fibonacci(i));
    }

    static int fibonacci(int i) {
        if (i == 0)
            return i;

        if (i == 1)
            return 1;

        System.out.println("Calculating f(" + i + ")");
        return fibonacci(i - 2) + fibonacci(i - 1);
    }

    private static final Map<Integer, Integer> cache = new HashMap<>();
    /**
     *  使用缓存的场景
     */
    @Test
    public void test2(){
        for (int i = 0; i < 10000; i++)
            System.out.println("f(" + i + ") = " + fibonacci2(i));
    }

    static int fibonacci2(int i) {
        if (i == 0)
            return i;

        if (i == 1)
            return 1;

        System.out.println("Calculating f(" + i + ")");
        return cache.computeIfAbsent(i, (key) -> fibonacci2(i - 2) + fibonacci2(i - 1));
    }

}
