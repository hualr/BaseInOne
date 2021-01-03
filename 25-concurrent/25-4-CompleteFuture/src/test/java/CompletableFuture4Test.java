import java.util.concurrent.CompletableFuture;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * Function: <br>
 * Creating Time: 2021/1/3 22:00 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class CompletableFuture4Test {
    static Double fetchPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        if (Math.random() < 30) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }

    /**
     * 理解回调的使用
     */
    @SneakyThrows
    @Test
    public void test1() {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CompletableFuture4Test::fetchPrice);
        // 如果执行成功:
        cf.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 如果执行异常:
        cf.exceptionally((e) -> {
            System.out.println(e);
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);


        //优化  =>  类似于listenableFuture中的回调
        cf.handle((Double ok, Throwable ex) -> {
            if (ok == null) {
                System.out.println(ex);
                return null;
            }
            return Integer.parseInt(String.valueOf(ok));
        });
    }
}
