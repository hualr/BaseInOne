import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * Function: <br>
 * Creating Time: 2021/1/2 17:43 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class BaseFuture {
    /**
     * Future用法演示
     */
    @SneakyThrows
    @Test
    public void test1() {
        ExecutorService service = Executors.newFixedThreadPool(20);
        //1. 用list去接收Future封装的信息
        ArrayList<Future> futures = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            //2. submit和execute的区别在于一个接收无返回值 一个接收有返回值(Call)
            Future<Integer> future = service.submit(new CallableTask());
            //3. 关注Future类 这也是接口,注意其方法即可
            futures.add(future);
        }
        Thread.sleep(5000);
        for (int i = 0; i < 20; i++) {
            Future<Integer> future = futures.get(i);
            //阻塞方法获取线程的值
            Integer integer = future.get();
            System.out.println(integer);
        }
    }

    /**
     * <h2>比较Callable和Runnable</h2>
     * <li> Callable和Runnable区别完全可以根据他们的唯一方法判断</li>
     * <li> Callable 有return T,只需要定义T即可 而runnable没有返回值</li>
     * <li> Runnable必须内部处理异常 而Callable可以选择将异常抛出</li>
     * <li> Callable的异常是Get的时候被抛出的 不get就不会感知</li>
     */
    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return 1;
        }
    }    //定义Callable类从而获取值


    static class RunnableTask implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
