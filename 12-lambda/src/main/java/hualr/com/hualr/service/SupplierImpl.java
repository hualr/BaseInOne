package hualr.com.hualr.service;

import hualr.bean.Apple;
import java.util.function.Supplier;

/**
 * @author ：zq
 * @description：TODO
 * @date ：2020/10/12 23:06
 */
public class SupplierImpl {
        public Apple test(Apple apple, Supplier<Integer> supply){
            apple.setWeight(supply.get());
            return apple;
        }
}
