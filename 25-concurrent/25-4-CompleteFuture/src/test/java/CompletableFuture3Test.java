import java.util.concurrent.CompletableFuture;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * Function: <br>
 * Creating Time: 2021/1/3 21:55 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class CompletableFuture3Test {
    /**
     * 组合使用结构
     */
    @SneakyThrows
    @Test
    public void test() {
        CompletableFuture<String> futureOne = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            return "futureOneResult";
        });

        CompletableFuture<String> futureTwo = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
            }
            return "futureTwoResult";
        });

        //anyof是只要有一个异步返回就返回,返回第一个完成任务的结果
        CompletableFuture completableFuture = CompletableFuture.anyOf(futureOne, futureTwo);
        System.out.println(completableFuture.get());
        //allof需要等到所有的异步结果均返回才能返回,由于所有结果都完成,因此返回的结果是空
        //*CompletableFuture future = CompletableFuture.allOf(futureOne, futureTwo);
    }
}