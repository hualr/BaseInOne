import com.google.common.base.Function;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Before;
import org.junit.Test;

/**
 * Function: <br>
 * Creating Time: 2021/1/21 11:25 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
@Slf4j
public class SettableFutureTest {

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().setDaemon(true).setNameFormat(
            "test-pool-%d").build();
    private static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(
            0,
            Integer.MAX_VALUE,
            60L,
            TimeUnit.SECONDS,
            new SynchronousQueue<>(false), THREAD_FACTORY);
    private static ListeningExecutorService SERVICE;


    @Before
    public void before() {
        SERVICE = MoreExecutors.listeningDecorator(EXECUTOR_SERVICE);
    }

    /**
     * 基础API的使用1
     */
    @SneakyThrows
    @Test
    public void test1() {
        //       SettableFuture<Void> settableFuture = SettableFuture.create();

        ListenableFuture<Boolean> booleanTask = SERVICE.submit(() -> {
            TimeUnit.MINUTES.sleep(1);
            return true;
        });
        System.out.println(booleanTask.get());
        Futures.addCallback(booleanTask, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                System.out.println("BooleanTask: " + result);
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });


    }

    /**
     *
     */
    @Test
    public void test2() throws InterruptedException {
        ListenableFuture<String> stringTask = SERVICE.submit(() -> {
            TimeUnit.SECONDS.sleep(10);
            return "Hello World";
        });

        log.info("继续走");
        Futures.addCallback(stringTask, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                log.info(result);
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
        TimeUnit.SECONDS.sleep(15);
    }


    /**
     * <li>ListeningExecutorService->ExecutorService的扩展，重新包装ExecutorService类中的submit方法，返回ListenableFuture对象。
     * <li>MoreExecutors->该类是final类型的工具类，提供了很多静态方法。比如ListeningDecorator方法初始化ListeningExecutorService方法
     * <li> 首先,异步A不会阻塞B操作
     * <li> 其次,异步A的回调不会阻塞C操作
     */
    @Test
    public void test3() throws InterruptedException {
        //1. 定义线程池
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<String> doSomeThings = service.submit(() -> {
            log.info("开始A操作");
            TimeUnit.SECONDS.sleep(15);
            log.info("完成A操作");
            return "完成A调用";
        });
        log.info("开始B操作");
        TimeUnit.SECONDS.sleep(5);
        log.info("完成B操作");

        //不会阻塞C操作,只是一个监听器
        Futures.addCallback(
                doSomeThings,
                new FutureCallback<String>() {
                    @Override
                    public void onSuccess(String explosion) {
                        log.info("成功" + explosion);
                    }

                    @Override
                    public void onFailure(Throwable thrown) {
                        log.error("失败");
                    }
                },
                service);
        log.info("开始C操作");
        TimeUnit.SECONDS.sleep(5);
        log.info("完成C操作");
        while (!doSomeThings.isDone()) {
            TimeUnit.SECONDS.sleep(15);
        }
    }

    /**
     *
     */
    @Test
    public void test4() throws InterruptedException {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<Integer> future1 = service.submit(() -> {
            log.info("开始A操作");
            TimeUnit.SECONDS.sleep(5);
            log.info("完成A操作");
            return 1;
        });
        ListenableFuture<Integer> future2 = service.submit(() -> {
            log.info("开始B操作");
            TimeUnit.SECONDS.sleep(2);
            log.info("完成B操作");
            return 2;
        });
        ListenableFuture<List<Object>> futures = Futures.allAsList(future1, future2);
        //futures = Futures.successfulAsList(future1, future2);
        Futures.addCallback(futures, new FutureCallback<List<Object>>() {
            @Override
            public void onSuccess(@Nullable List<Object> result) {
                log.info("结束完成所有操作,然后回调:{}", result);
            }

            @Override
            public void onFailure(Throwable t) {
                log.error("结束完成所有操作,然后回调", t);
            }
        }, service);

        while (!futures.isDone()) {
            TimeUnit.SECONDS.sleep(5);
        }
    }

    /**
     *
     */
    @Test
    public void test5() throws InterruptedException {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

        // 原Future
        ListenableFuture<String> future3 = service.submit(() -> {
            log.info("开始异步操作A");
            TimeUnit.SECONDS.sleep(5);
            log.info("开始异步操作A");
            return "hello, future";
        });
        // 同步转换
        log.info("当前线程为: [{}]", Thread.currentThread().getName());
        ListenableFuture<Integer> future5 = Futures.transform(future3, new Function<String, Integer>() {
            @NullableDecl
            @Override
            public Integer apply(@NullableDecl String input) {
                log.error("完成转化:[{}]", input);
                return 1;
            }
        }, service);
/*        // 异步转换
        ListenableFuture<Integer> future6 = Futures.transformAsync(future3,
                input -> Futures.immediateFuture(input.length()), service);*/
        TimeUnit.SECONDS.sleep(30);
    }
}
