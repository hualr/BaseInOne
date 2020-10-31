package hualr.com.hualr.service;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author ：zq
 * @description：这里用于消费调用
 * @date ：2020/10/12 22:50
 */
public class ComsumerImpl<T> {
    public void test(T t, Consumer<T> consumer){
        consumer.accept(t);
        System.out.println("一个消费者");
    }

    public static <T> void  forEach(List<T> list, Consumer<T> consumer){
        for (T t:list){
            consumer.accept(t);
        }
    }
}

