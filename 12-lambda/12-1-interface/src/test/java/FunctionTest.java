import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * Function: 函数Function接口基础使用<br>
 * Creating Time: 2021/1/19 <br>
 * Version: 1.0.0
 *
 * @author 宗旗
 */
public class FunctionTest {
    /**
     * 基本使用1
     */
    @Test
    public void test1() {
        Function<Integer, Integer> test = i -> i + 1;
        System.out.println(test.apply(5));;
    }

    /**
     * 基本使用2
     */
    @Test
    public void test2(){
        Function<Integer,Integer> test1=i->i+1;
        Function<Integer,Integer> test2=i->i*i;
        System.out.println(calculate(test1,5));
        System.out.println(calculate(test2,5));
    }
    public static Integer calculate(Function<Integer,Integer> test,Integer number){
        return test.apply(number);
    }

    /**
     *  基本使用3
     */
    @Test
    public void test3(){
        Function<Integer,Integer> A=i->i+1;
        Function<Integer,Integer> B=i->i*i;
        //(1+5)2
        System.out.println("F1:"+B.apply(A.apply(5)));
        //从右往左处理
        System.out.println("F1:"+B.compose(A).apply(5));
        //1+(5*5)
        System.out.println("F2:"+A.apply(B.apply(5)));
        //从左往右处理
        System.out.println("F2:"+B.andThen(A).apply(5));
        System.out.println(B.compose(A).compose(A).andThen(A).apply(5));
    }


    /**
     *  基本使用4
     */
    @Test
    public void test4(){
        System.out.println(calculate2(a->a*a,a->a-1, 5));
        System.out.println(calculate3(a->a*a,a->a-1, 5));
    }

    public static Integer calculate2(Function<Integer,Integer> test1,Function<Integer, Integer> test2,Integer number){
        return test1.compose(test2).apply(number);
    }

    public static Integer calculate3(Function<Integer,Integer> test1,Function<Integer, Integer> test2,Integer number){
        return test1.andThen(test2).apply(number);
    }

    /**
     *  identify
     */
    @Test
    public void test5(){
        Function<Integer,Integer> A=i->i*i;
        final List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        final Map<Integer, Integer> map1 = integers.stream().collect(Collectors.toMap(A, A));
        System.out.println(map1);
        final Map<Integer, String> map2 = integers.stream().collect(Collectors.toMap((i)->i*i, i->i+"-"+i));
        System.out.println(map2);
        final Map<Integer, String> map31 = integers.stream().collect(Collectors.toMap(i->i, i->i+"-"+i));
        final Map<Integer, String> map32 = integers.stream().collect(Collectors.toMap(Function.identity(), i->i+"-"+i));
        System.out.println(map31);
        System.out.println(map32);
    }
}
