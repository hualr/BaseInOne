package hualr.com.hualr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author ：zq
 * @description：TODO
 * @date ：2020/10/12 22:56
 */
public class FunctionImpl<T,R> {
    public R test(T t, Function<T, R> function){
        return function.apply(t);
    }

    public static <T,R> List<R> map(List<T> list, Function<T, R> function){
        List<R> result=new ArrayList<>();
        for (T t:list){
            result.add(function.apply(t));
        }
        return result;
    }
}
