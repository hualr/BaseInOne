import java.io.IOError;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Function: 无参数无返回值的函数接口<br>
 * Creating Time: 2021/1/23 <br>
 * Version: 1.0.0
 *
 * @author 宗旗
 */

@Slf4j
public class RunnableFunctionTest {
    boolean flag1 = true;

    /**
     *
     */
    @Test
    public void test1() {
        Runnable runnable = flag1 ? this::action1 : this::action2;
        apply(runnable);
    }

    @Test
    public void test2() {
        apply(flag1 ? this::action1 : this::action2);
    }

    public void apply(Runnable runnable) {
        try {
            runnable.run();
        } catch (IOError e) {
            log.error("error", e);
        }
    }

    private void action1() {
        System.out.println("doing action1");
    }

    private void action2() {
        System.out.println("doing action2");
    }
}
