package com.hualr.base;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/12/2 21:37
 * Version: 1.0.0
 */
public class Demo1 {
    @Test
    public void test1() {
        //创建一个上游 Observable：
        Observable<Integer> observable = Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onComplete();
        });
        //创建一个下游 Observer
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("subscribe" + d);
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("onNext:" + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("erroris :" + e);
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        };
        //建立连接
        //只有当上游和下游建立连接之后, 上游才会开始发送事件. 也就是调用了subscribe() 方法之后才开始发送事件.
        observable.subscribe(observer);
    }

    @Test
    public void test2() {
        //通过调用emitter的onNext(T value)、onComplete()
// 和onError(Throwable error)就可以分别发出next事件、complete事件和error事件。
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            //上游可以发送无限个onNext, 下游也可以接收无限个onNext.
            //当上游发送了一个onComplete后, 上游onComplete之后的事件将会继续发送, 而下游收到onComplete事件之后将不再继续接收事件.
            //当上游发送了一个onError后, 上游onError之后的事件将继续发送, 而下游收到onError事件之后将不再继续接收事件.
            //只能有一个onComplete或者onERROR
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onComplete();
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("subscribe" + d);
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("onNext:" + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("erroris :" + e);
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        });
    }

    @Test
    public void test3() {
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
        }).subscribe(new Observer<Integer>() {
            private Disposable mDisposable;
            private int i;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("subscribe" + d);
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull Integer value) {
                System.out.println("onNext:" + value);
                i++;
                //第二次后,上游继续发送 下游不在接收
                if (i == 2) {
                    System.out.println("dispose");
                    mDisposable.dispose();
                    System.out.println("isDisposed:" + mDisposable.isDisposed());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("erroris :" + e);
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        });
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
}
