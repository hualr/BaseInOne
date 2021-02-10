import java.util.function.BiFunction;
import org.junit.Test;

/**
 * Function: BiFunction的使用<br>
 * Creating Time: 2021/1/20 <br>
 * Version: 1.0.0
 *
 * @author 宗旗
 */
public class BiFunctionTest {
    /**
     *  BiFunction的初级使用
     */
    @Test
    public void test1(){
        BiFunction<Integer,Integer,Integer> A=(a,b)->a+b;
        BiFunction<Integer,Integer,Integer> B=(a,b)->a*b;
        //2+3
        System.out.println(A.apply(2, 3));
        //2*3
        System.out.println(B.apply(2, 3));
    }

    /**
     *  组合使用
     */
    @Test
    public void test2(){
        System.out.println(compute(1, 2d, (a,b)->(a+"*"+b)));
    }

    String compute(Integer inte1,Double dou1,BiFunction<Integer,Double,String> function){
        return function.apply(inte1,dou1);
    }

}
