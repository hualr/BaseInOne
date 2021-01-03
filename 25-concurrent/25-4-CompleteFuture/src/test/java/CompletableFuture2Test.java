import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

/**
 * Function: <br>
 * Creating Time: 2021/1/3 21:16 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class CompletableFuture2Test {
    /**
     * <li> 不管是thenApply还是thenApplyAsync 均是线性串行的</li>
     * <li> 区别在于后者的任务可以提交到其他线程池中</li>
     */
    @SneakyThrows
    @Test
    public void test1() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "|" + System.currentTimeMillis());
            return 2;
        }, executorService)
                //就在此线程中=>拿到结果后处理
                .thenApply(i -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "第一次处理的结果:" + i + "|" + System.currentTimeMillis());
                    return i * i;
                })
                //新线程池中=>继续拿到处理后的结果处理
                .thenApplyAsync(i -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "第二次处理的结果:" + i + "|" + System.currentTimeMillis());
                    return i + 1;
                }, executorService)
                //直到处理完成
                .whenComplete((r, e) -> System.out.println(r));
        Assert.assertEquals(5, (int) future1.get());
    }

    /**
     * thenAccept的使用 单纯的消费者
     */
    @SneakyThrows
    @Test
    public void test2() {
        //02 直接消费掉收到的结果 future中不会有值的产生
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> 2)
                .thenAccept(System.out::println);
        Assert.assertNull(future.get());

    }

    /**
     * thenRun的使用 单纯的做某件事而不管之前发生了什么
     */
    @SneakyThrows
    @Test
    public void test3() {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> 2)
                .thenRun(() -> System.out.println(3));
        Assert.assertNull(future.get());
    }

    /**
     * thenCombine的使用=> 任务的连接
     */
    @Test
    public void test4() {
        CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(String::toUpperCase)
                .thenCombine(CompletableFuture.completedFuture("WORLD"), (s1, s2) -> s1 + s2)
                .thenAccept(System.out::println);

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(String::toUpperCase);
        future1.thenCombine(future1, (s1, s2) -> s1 + s2).thenAccept(System.out::println);
    }

    /**
     * whenComplete的使用
     */
    @Test
    public void test5() {
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("this is the first task");
            int a = 1 / 0;
            return "first";
        });

        //关注whenComplete的实现方法即可 这是一个阻塞方法
        task1.whenComplete((s, e) -> {
            System.out.println("正确的结果为" + s);
            System.out.println(e);
        });
    }

}
