package com.hualr.demo;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/12/4 21:58
 * Version: 1.0.0
 */
public class Stu1 {
    /**
     * 1. 单个监听事件是不允许并发执行的 并发并行只能通过多个监听事件组合实现
     * 如下的代码是有问题的
     */
    @Test
    public void test1() throws InterruptedException {
        Observable.create(s -> {
            new Thread(() -> {
                s.onNext("i");
                s.onNext("love");
            });
            new Thread(() -> {
                s.onNext("i");
                s.onNext("love");
            });
        }).subscribe(System.out::println);
    }

    @Test
    public void test2() throws InterruptedException {
        Observable<Object> observable1 = Observable.create(s -> {
            new Thread(() -> {
                s.onNext("1");
                s.onNext("2");
            });
        });
        Observable<Object> observable2 = Observable.create(s -> {
            new Thread(() -> {
                s.onNext("3");
                s.onNext("4");
            });
        });
        Observable<Object> observable3 = observable1.mergeWith(observable2);
        observable3.subscribe(System.out::println);
        Thread.sleep(10000);
    }

    @Test
    public void test4() {
        //只处理netx事件 其他事件 不会处理
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            System.out.println("send: emit 1");
            emitter.onNext(1);
            System.out.println("send: emit 2");
            emitter.onNext(2);
            System.out.println("send: emit 3");
            emitter.onNext(3);
            emitter.onComplete();
            System.out.println("send: emit 4");
            emitter.onNext(4);
        }).subscribe(value -> {
            System.out.println("onNext:" + value);
            //第二次后,上游继续发送 下游不在接收
        });
    }

    @Test
    public void test3() {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            emitter.onNext(1);
        }).subscribe(System.out::println);
    }
}
