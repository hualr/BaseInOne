import java.math.BigDecimal;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/12/19 20:10
 * Version: 1.0.0
 */
public class BigDecimalDemo {
    /**
     * 小数点:声明精度 同时一定得写清楚万一出现和约定的情况不匹配场景下的解决方法
     */
    @Test
    public void test1() {
        BigDecimal decimal = new BigDecimal("17.77");
        System.out.println(decimal.setScale(3, BigDecimal.ROUND_HALF_UP));
    }

    /**
     * 除法运算过程中舍入 指定小数点几位
     */
    @Test
    public void test2() {
        System.out.println(new BigDecimal(10).divide(new BigDecimal(3), 2, BigDecimal.ROUND_HALF_UP));
    }

    /**
     * 精度问题导致比较结果和预期不一致
     * equal由于存在重写 导致出现进度不一致就会直接返回空的情况
     */
    @Test
    public void test3() {
        System.out.println(new BigDecimal("1").equals(new BigDecimal("1.0")));
        System.out.println(new BigDecimal("1").compareTo(new BigDecimal("1.0")));
    }
}














