import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * Function: <br>
 * Creating Time: 2021/1/2 16:20 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class ExecutorDemo2 implements Runnable {

    /**
     *
     */
    @SneakyThrows
    @Test
    public void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100000; i++) {
            executorService.execute(new ExecutorDemo2());
        }
        //Thread.sleep(1500);
        //立即停止
        //List<Runnable> runnableList = executorService.shutdownNow();

        executorService.shutdown();
        //被拒绝
        //  executorService.execute(new ShutDownTask());

        //判断一段时间内是否会执行完成 只是检测作用
        System.out.println("线程执行完了?" + executorService.awaitTermination(7L, TimeUnit.SECONDS));
        //判断是否进入停滞状态 false
        System.out.println("线程被关了?" + executorService.isShutdown());
        executorService.shutdown();
        System.out.println("线程被关了?" + executorService.isShutdown());
        //判断是否完全停止
        System.out.println("线程terminate了?" + executorService.isTerminated());
        Thread.sleep(10000);
        System.out.println("线程terminate了?" + executorService.isTerminated());
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断了");
        }
    }
}

