import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * Function: <br>
 * Creating Time: 2021/1/2 18:04 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class FutureTaskDemo {
    /**
     *
     */
    @SneakyThrows
    @Test
    public void test() {
        //FutureTask的构造器中可以同时传入Runnable和Callable 因为它实现了Future和Runnable的接口
        FutureTask<Integer> integerFutureTask = new FutureTask<>(() -> {
            System.out.println("子线程正在计算");
            Thread.sleep(3000);
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        });

        //result表示执行后返回的结果 指定值 runnable+call
        FutureTask<Integer> integerFutureTask2 = new FutureTask<>(() -> System.out.println("子线程正在计算"), 1);
        ExecutorService service = Executors.newCachedThreadPool();
        //service.submit(integerFutureTask);
        service.execute(integerFutureTask);
        service.execute(integerFutureTask2);
        // ZMM 注意到这个结果是直接通过futureTask回调拿到得的 并非通过submit的结果拿到的
        // ZNN 如果没有执行 那么get会处于阻塞状态
        System.out.println("task运行结果：" + integerFutureTask.get());
        System.out.println("task运行结果：" + integerFutureTask2.get());

    }
}
