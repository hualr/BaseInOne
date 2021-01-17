package compare;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.Test;

/**
 * Function: <br>
 * Creating Time: 2021/1/13 14:23 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class Compare1Test {
    /**
     * 基础比较
     */
    @Test
    public void test0() {
        /*
            1. o1-o2 即为升序 否则为降序
            2. 返回为0/1就是不变 -1就是倒过来
         */
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return (Integer) o1 - (Integer) o2;
                //return 1;
            }
        };
        List<Integer> list = Arrays.asList(9, 1, 5, 7, 3, 4);
        list.sort(comparator);
        System.out.println(list);
    }

    @Test
    public void test11() {
        Comparator comparator = (o1, o2) -> {
            return (Integer) o1 - (Integer) o2;
        };
        List<Integer> list = Arrays.asList(9, 1, 5, 7, 3, 4);
        list.sort(comparator);
        System.out.println(list);
    }

    @Test
    public void test12() {
        Comparator comparator = Comparator.comparingInt(o -> (Integer) o);
        List<Integer> list = Arrays.asList(9, 1, 5, 7, 3, 4);
        list.sort(comparator);
        System.out.println(list);
    }

    @Test
    public void test13() {
        List<Integer> list = Arrays.asList(9, 1, 5, 7, 3, 4);
        list.sort(Comparator.comparingInt(o -> (Integer) o));
        System.out.println(list);
    }

    @Test
    public void test14() {
        List<String> list = Arrays.asList("9", "1", "5", "7", "3", "4");
        list.sort(Comparator.comparingInt(o -> Integer.parseInt(o)));
        System.out.println(list);
    }

    @Test
    public void test15() {
        List<String> list = Arrays.asList("9", "1", "5", "7", "3", "4");
        list.sort(Comparator.comparingInt(Integer::parseInt));
        System.out.println(list);
    }

    /**
     * 这里实际上就是最常用的写法
     */
    @Test
    public void test2() {
        List<String> list = Arrays.asList("100", "1", "5", "7", "3", "4");
        Collections.sort(list, (o1, o2) -> Integer.parseInt(o1) - Integer.parseInt(o2));
        System.out.println(list);
    }

    @Test
    public void test21() {
        List<String> list = Arrays.asList("100", "1", "5", "7", "3", "4");
        Collections.sort(list, Comparator.comparingInt(Integer::parseInt));
        System.out.println(list);
    }

    @Test
    public void test22() {
        List<String> list = Arrays.asList("100", "1", "5", "7", "3", "4");
        //list.sort(Comparator.comparingInt(Integer::parseInt));
        Collections.sort(null);
        System.out.println(list);
    }

    @Test
    public void test23() {
        String s = "";
        s = s.replaceAll("[^0-9]*", "");
        s = s.equals("") ? "0" : s;
        System.out.println(s);
    }

}