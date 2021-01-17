import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * Function: <br>
 * Creating Time: 2021/1/5 12:19 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class GuavaReTry1Test {

    /**
     * 重试方法
     *
     * @return
     */
    public static boolean retryTask(String param) {
        System.out.println("收到请求参数:" + param);
        int i = new Random().nextInt(3);
        //int i = Random.nextInt(0,11);
        System.out.println("随机生成的数:" + i);
        if (i < 2) {
            System.out.println("为0,抛出参数异常.");
            throw new IllegalArgumentException("参数异常");
        } else if (i < 5) {
            System.out.println("为1,返回ture");
            return true;
        } else if (i < 7) {
            System.out.println("为2,返回false.");
            return false;
        } else {
            //为其他
            System.out.println("大于2,抛出自定义异常.");
            throw new NullPointerException("大于2,抛出自定义异常");
        }
    }

    /**
     *
     */
    @Test
    public void test1() {
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(res -> res == false)  //设置根据结果重试
                .withWaitStrategy(WaitStrategies.fixedWait(3, TimeUnit.SECONDS)) //设置等待间隔时间
                .withStopStrategy(StopStrategies.stopAfterAttempt(3)) //设置最大重试次数
                .build();

        try {
            retryer.call(() ->);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

}
