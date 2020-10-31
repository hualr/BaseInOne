package hualr.com.hualr.service;


import hualr.bean.Apple;

//这个接口实际就一个方法
@FunctionalInterface
public interface ApplePredicate {
    boolean test(Apple apple);
}
