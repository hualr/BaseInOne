import bean.Apple;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.Bag;
import org.apache.commons.collections.bag.HashBag;
import org.apache.commons.collections.bag.PredicatedBag;
import org.junit.Test;

/**
 * Function: 背包工具类使用<br>
 * Creating Time: 2021/1/23 <br>
 * Version: 1.0.0
 *
 * @author 宗旗
 */
@Slf4j
public class Bag1Test {
    private final Apple greenApple = new Apple("green", 12);
    private final Apple redApple = new Apple("red", 11);

    /**
     *  <h2>初级使用 Bag API 基本使用<h2/>
     *  这个BAG主要是用来计算某个变量出现的次数而使用的
     */
    @Test
    public void test1(){
        HashBag bag=new HashBag();
        bag.add(greenApple,2);
        bag.add(greenApple);
        bag.add(redApple,4);
        System.out.println(bag.getCount(greenApple));
        System.out.println(bag.getCount(redApple));

        //ZNN 一般来说,如果不是全部移除,用这个api
        bag.remove(redApple,2);
        System.out.println(bag.getCount(redApple));

        //ZNN 移除是移除这个key了
        bag.remove(redApple);
        System.out.println(bag.getCount(redApple));
    }

    /**
     *  <h2> 常用场景: 统计变量 </h2>
     */
    private final Bag bag = new HashBag();
    @Test
    public void test2(){
        bag.add(greenApple,2);
        bag.add(greenApple);
        bag.add(redApple,4);
        final Bag decorate = PredicatedBag.decorate(bag, o -> o.equals(greenApple));
        System.out.println(decorate.getCount(greenApple));
        System.out.println(decorate.getCount(redApple));
    }
}
