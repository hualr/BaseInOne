package hualr;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.hualr.api.Service1;
import com.hualr.api.Service2;
import com.hualr.business.api.Application;
import com.hualr.business.injector.MyApp1;
import com.hualr.business.injector.MyApp2;
import com.hualr.impl.Service1Impl1;
import com.hualr.impl.Service2Impl;
import javax.inject.Singleton;
import org.junit.Test;

/**
 * Function:  演示的是Single的范围使用<br>
 * Creating Time: 2020/12/27 23:24 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class SingleGuiceDemo {
    /**
     * 直接在bind中声明使用的是单例 这意味着每次new 都是一个单独的
     */
    @Test
    public void test() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(Service1.class).to(Service1Impl1.class);
                bind(Service2.class).to(Service2Impl.class);
                bind(Application.class).to(MyApp2.class).in(Singleton.class);
            }
        });

        //连续调用两次 虽然是两个对象 但是在单例的限制上两次对象实际为一个
        Application myApp1 = injector.getInstance(Application.class);
        myApp1.work();

        Application myApp2 = injector.getInstance(Application.class);
        myApp2.work();

        System.out.println(myApp1 == myApp2);
    }

    /**
     * 实现类直接利用@Singleton标注
     */
    @Test
    public void test2() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(Service1.class).to(Service1Impl1.class);
                bind(Service2.class).to(Service2Impl.class);
                bind(Application.class).to(MyApp1.class);
            }
        });

        //连续调用两次 虽然是两个对象 但是在单例的限制上两次对象实际为一个
        Application myApp1 = injector.getInstance(Application.class);
        myApp1.work();

        Application myApp2 = injector.getInstance(Application.class);
        myApp2.work();

        System.out.println(myApp1 == myApp2);
    }
}
