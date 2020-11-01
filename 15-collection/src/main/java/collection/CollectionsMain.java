package collection;

import static java.util.Arrays.asList;

import java.util.Collections;
import java.util.List;

public class CollectionsMain {
    public static void main(String[] args) {
        List<String> list1 = asList("a", "s", "e", "m", "p");
        System.out.println(list1);
        //按照一定规则排序
        Collections.sort(list1);
        System.out.println(list1);

        //随机排序
        /*Collections.shuffle(list1);
        System.out.println(list1);*/

        // 颠倒下规则
        Collections.reverse(list1);
        System.out.println(list1);

        //字符替换
        Collections.replaceAll(list1, "a", "b");
        System.out.println(list1);

        //替换 把所有元素替换
        Collections.fill(list1, "啊啊啊");
        System.out.println(list1);

        //清空
        list1 = Collections.EMPTY_LIST;
        System.out.println(list1);
    }
}
