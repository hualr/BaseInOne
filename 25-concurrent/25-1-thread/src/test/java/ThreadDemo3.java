import java.util.concurrent.atomic.AtomicInteger;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * Function: 线程的方法<br>
 * Creating Time: 2021/1/2 15:03 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class ThreadDemo3 {
    AtomicInteger i = new AtomicInteger(0);

    /**
     *
     */
    @SneakyThrows
    @Test
    public void test() {
        //ZNN:sleep意味着只是会休息500S.休息之后继续投入竞争
        //testSleep();
        //ZNN: yeild意味着忽然拦住这个线程一下,然后让他继续和其他线程竞争
        testYield();
        //ZNN join实际上主要用于设置线程的运行顺序
        //testJoin();
        Thread.sleep(100000);
    }

    void testYield() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if (i % 10 == 0) {
                    System.out.println("B" + i);
                    Thread.yield();
                }
            }
        }).start();
    }

    void testJoin() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                System.out.println("prepare t1");
//                ZNN 当前线程会等待t1线程完全结束之后才能继续运行
                t1.join();
                System.out.println("continue t2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }


    void testSleep() {
        for (int m = 0; m < 10; m++) {
            int j = m;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + j);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }, "sleepTest" + i.getAndIncrement()).start();
        }

    }
}
