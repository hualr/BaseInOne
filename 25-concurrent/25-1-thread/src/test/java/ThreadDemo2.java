import lombok.SneakyThrows;
import org.junit.Test;

/**
 * Function:  关注Thread抽象类的属性<br>
 * Creating Time: 2021/1/2 14:43 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class ThreadDemo2 {

    /**
     * <li> 观察线程的状态
     * <li> 自己给线程命名 可以观察到线程的命名方式默认未Thread+num 具体在源码中可以看的很清楚
     * <li> 观察isAlive属性判断线程是否结束
     */

    @SneakyThrows
    @Test
    public void test2() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "sonThread");
        System.out.println(thread.getName() + "|" + thread.getId() + "|" + thread.getThreadGroup() + "|" + thread.getPriority());
        System.out.println("|" + thread.getState() + "|" + thread.isAlive() + "|" + thread.isInterrupted());
        thread.start();
        System.out.println("|" + thread.getState() + "|" + thread.isAlive() + "|" + thread.isInterrupted());
        thread.join();
        System.out.println("|" + thread.getState() + "|" + thread.isAlive() + "|" + thread.isInterrupted());


    }

}
