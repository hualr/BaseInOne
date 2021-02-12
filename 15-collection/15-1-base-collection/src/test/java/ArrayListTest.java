import java.util.ArrayList;
import org.junit.Test;

/**
 * Function: arrayList API<br>
 * Creating Time: 2021/2/12 <br>
 * Version: 1.0.0
 *
 * @author 宗旗
 */
public class ArrayListTest {
    /**
     * removeof API的使用
     */
    @Test
    public void test1() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.removeIf(s -> s.equals("1"));
        System.out.println(strings);
        System.out.println(strings.get(0));
    }

}
