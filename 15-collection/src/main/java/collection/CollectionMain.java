package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

//简单学习Collection接口
public class CollectionMain {
    public static void main(String[] args) {
        Collection<String> collection1 = new ArrayList();
        //1. 添加功能
        collection1.add("1");
        collection1.addAll(Arrays.asList("1", "s", "y"));

        //2. 转化功能
        Object[] arr = collection1.toArray();
        for (Object object : arr) {
            System.out.println(object);
        }
        //3. 判断是否含有
        System.out.println(collection1.contains("1"));
        System.out.println(collection1.containsAll(Arrays.asList("s", "y")));

        //4. 删除功能:是删除所有匹配的 比如这里删除了两个1
        collection1.removeAll(Arrays.asList("1"));
        collection1.forEach(System.out::println);

    }
}
