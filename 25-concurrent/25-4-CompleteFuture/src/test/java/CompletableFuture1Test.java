import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

/**
 * Function: <br>
 * Creating Time: 2021/1/3 20:44 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class CompletableFuture1Test {

    /**
     * 使用原因: 当需要返回一个CompletableFuture的场景下 目的只是为了返回一个CompletableFuture
     */
    @Test
    public void test1() {
        CompletableFuture<String> future1 = CompletableFuture.completedFuture("创建");
        CompletableFuture<String> future2 = CompletableFuture.completedFuture(null);
        //完成则返回完成值 否则返回getNow设定的参数
        Assert.assertEquals("符合预期", "创建", future1.getNow("没有创建"));
        Assert.assertEquals("符合预期", "没有创建", future2.getNow("完成了"));
    }

    /**
     * 有返回值的异步调用
     */
    @SneakyThrows
    @Test
    public void test2() {
        //ZMM 创建 未指定线程池则利用ForkJoinPool实现,该线程池中的线程即为守护线程
        //有返回值
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> 1);
        Assert.assertEquals(1, (int) integerCompletableFuture.get());
    }

    /**
     * 无返回值的异步调用
     */
    @SneakyThrows
    @Test
    public void test3() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(
                () -> System.out.println(2));
        //Thread.sleep(1000);
        Assert.assertNull(future.get());
    }

    /**
     * 整合线程池的使用
     */
    @SneakyThrows
    @Test
    public void test4() {
        //整合线程池的使用

        //1. 默认场景: ForkJoinPool线程池来执行任务.且线程为守护线程
        CompletableFuture<String> defaultFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync 是否为守护线程 " + Thread.currentThread().isDaemon());
            return "result1";
        });
        Assert.assertEquals("result1", defaultFuture.get());

        ExecutorService executorService = Executors.newCachedThreadPool();
        //2. 指定线程池的场景
        CompletableFuture<String> specialFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync 使用executorService 时是否为守护线程 : " + Thread.currentThread().isDaemon());
            return "result2";
        }, executorService);
        Assert.assertEquals("result2", specialFuture.get());
        executorService.shutdown();
    }
}
