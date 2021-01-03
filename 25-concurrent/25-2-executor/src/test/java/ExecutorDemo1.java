import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * Function:  演示常见线程池的使用<br>
 * Creating Time: 2021/1/2 15:34 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class ExecutorDemo1 {
    /**
     * 固定线程池和非线程池
     */
    @SneakyThrows
    @Test
    public void test1() {
        //1. Executor是一个接口 其中只定义了一个方法 即为execute
        //Executor executorService;

        //2. ExecutorService是Executor的子类,主要定义了shutdown等管理线程池的方法
        ExecutorService executorService;


        /* 固定线程的特点
        1. 保活性为0
        2. 队列为无界
        3. 核心线程即为最大线程*/

        //3. Executors是一个工具类 用于生成自动线程池
        executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 1000; i++) {
            //1. execute返回时void 因此只能放Runnable
            executorService.execute(() -> System.out.println("线程池任务" + Thread.currentThread().getName()));
        }

        //单线程线程池 最大的区别在于外面封装了一层 以此重写finalize 实现了在GC的时候会关闭线程.功能: 线程的串行执行
        /*executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> System.out.println("线程池任务" + Thread.currentThread().getName()));
        }*/

        //可缓存线程池 可以缓存 关注线程数量 核心数量 这里最大的特点是此时核心线程为0 最大线程为无限
        /*executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> System.out.println("线程池任务" + Thread.currentThread().getName()));
        }*/

    }

    /**
     * 计划线程的学习
     */
    @Test
    public void test2() {
        //计划线程池
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);
        //延迟
        threadPool.schedule(() -> System.out.println(Thread.currentThread().getName()), 5, TimeUnit.SECONDS);
        //频率
        threadPool.scheduleAtFixedRate(() -> System.out.println(Thread.currentThread().getName()),
                1, 3, TimeUnit.SECONDS);
    }
}
