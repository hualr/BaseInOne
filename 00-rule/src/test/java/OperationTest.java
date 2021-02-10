import org.junit.Test;

/**
 * Function: <br>
 * Creating Time: 2021/2/10 <br>
 * Version: 1.0.0
 *
 * @author 宗旗
 */
public class OperationTest {
    /**
     *  三元操作符一定要保证两边的运算类型一致 否则会出现乱七八糟的结果 如下可以看到出现了一个结果是100.0而非100
     */
    @Test
    public void test(){
        System.out.println((true)?100:110.0);
    }
}
