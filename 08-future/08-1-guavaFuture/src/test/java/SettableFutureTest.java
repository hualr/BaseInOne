import com.google.common.util.concurrent.SettableFuture;
import org.junit.Test;

/**
 * Function: <br>
 * Creating Time: 2021/1/21 11:25 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class SettableFutureTest {
    /**
     * 基础API的使用1
     */
    @Test
    public void test1() {
        SettableFuture<Void> settableFuture = SettableFuture.create();
    }
}
