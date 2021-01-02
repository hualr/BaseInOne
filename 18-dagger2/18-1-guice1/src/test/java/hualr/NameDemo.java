package hualr;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.hualr.api.Service1;
import com.hualr.business.api.Application;
import com.hualr.business.name.MyApp1;
import com.hualr.business.name.MyApp2;
import com.hualr.impl.Service1Impl1;
import com.hualr.impl.Service1Impl2;
import org.junit.Test;

/**
 * Function: <br>
 * Creating Time: 2020/12/27 23:42 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class NameDemo {

    /**
     * <h2>属性注解name</h2>
     */
    @Test
    public void testName1() {

        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                //下面只能绑定一个接口的一个实现类 但是如果有多个实现 那么单纯的方法是无法被实现的
                //bind(Service1.class).to(Service1Impl1.class);
                bind(Service1.class).annotatedWith(Names.named("service1_1")).to(Service1Impl1.class);
                bind(Service1.class).annotatedWith(Names.named("service1_2")).to(Service1Impl2.class);
                bind(Application.class).to(MyApp1.class);
            }
        });
        Application myApp = injector.getInstance(Application.class);
        myApp.work();
    }

    /**
     * <h2>构造器注入name</h2>
     */
    @Test
    public void testMyApp3WithModule2() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(Service1.class).annotatedWith(Names.named("service1")).to(Service1Impl1.class);
                bind(Application.class).to(MyApp2.class);
            }
        });
        Application instance = injector.getInstance(Application.class);
        instance.work();

    }
}
