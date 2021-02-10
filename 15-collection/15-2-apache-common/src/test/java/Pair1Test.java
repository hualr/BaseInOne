import bean.Apple;
import bean.Egg;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

/**
 * Function: Pair的使用<br>
 * Creating Time: 2021/1/23 <br>
 * Version: 1.0.0
 *
 * @author 宗旗
 */
public class Pair1Test {
    private final Apple greenApple = new Apple("green", 12);
    private final Apple redApple = new Apple("red", 11);
    private final Egg redEgg = new Egg("red", 11);
    /**
     * 有的时候我们不需要返回Map 只是单纯的想返回两个值的时候可以使用PAIR
     */
    @Test
    public void test1(){
        final Pair pair = doSomething();
        System.out.println(pair.getLeft());
        System.out.println(pair.getKey());
        System.out.println(pair.getRight());
        System.out.println(pair.getValue());
    }

    private Pair<Apple, Egg> doSomething(){
        return Pair.of(greenApple,redEgg);
    }
    
    /**
     *  其他有效API
     */
    @Test
    public void test2(){
        
    }
}
