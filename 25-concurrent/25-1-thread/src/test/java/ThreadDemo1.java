import lombok.SneakyThrows;
import org.junit.Test;

/**
 * Function: <br>
 * Creating Time: 2021/1/2 14:15 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class ThreadDemo1 {
    /**
     * 线程的创建方式1: 继承Thread
     */
    @SneakyThrows
    @Test
    public void test1() {
        Thread t = new Thread1();
        t.start();
        t.join();
    }

    /**
     * 线程的创建2: 实现runnable接口
     * <p>
     * 其实,Thread是一个抽象类,其继承于Runnable 也就是说,不论是方法1还是方法2都只是实现run方法而已
     * 而Runnable本身实际上只是一个接口,里面并没有其他属性
     * 这就意味着,所有需要关注的地方都在于Thread的抽象类中
     */
    @SneakyThrows
    @Test
    public void test2() {
        Thread thread2 = new Thread(new MyRun());
        thread2.start();
        thread2.join();
    }

    /**
     * <h2>任何线程都需要自定义名称 否则很难查问题!!!</h2>
     */
    @Test
    public void test() {
        new Thread(() -> System.out.println("is me"), "logThread");
    }

    private static class Thread1 extends Thread {
        @SneakyThrows
        @Override
        public void run() {
            Thread.sleep(1000);
        }
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println("MyRun");
        }
    }
}
