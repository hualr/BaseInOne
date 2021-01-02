package hualr;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.hualr.api.Service1;
import com.hualr.business.api.Application;
import com.hualr.business.provider.MyApp;
import com.hualr.business.provider.ServiceProvider;
import com.hualr.impl.Service1Impl1;
import javax.inject.Inject;
import org.junit.Test;

/**
 * Function: <br>
 * Creating Time: 2020/12/27 23:52 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class ProviderDemo {
    @Inject
    ServiceProvider serviceProvider;

    /**
     * <h2>toProvider</h2>
     * 1. 使用provider时,如果需要注入信息,那么只能采用构造器注入 否则就不注入任何信息<br>
     * 2. toProvider中如果注入了一个接口,那么必须提前定义好注入该接口的时候将会new 什么东西
     */
    @Test
    public void test() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(Service1.class).to(Service1Impl1.class);
                bind(Application.class).toProvider(ServiceProvider.class);
            }
        });
        Application instance = injector.getInstance(Application.class);
        instance.work();

    }

    /**
     * <h2>Providers是前者的简化</h2>
     */
    @Test
    public void test2() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                //创建一个Application 则产生MyApp
                //MyAPP 中需要注入一个构造器 但是构造器中的Service需要额外取得
                //方法1 直接在这里绑定即可 方法2 使用@Providers构造一个
                // bind(Service1.class).to(Service1Impl1.class);
                bind(Application.class).to(MyApp.class);
            }

            //构造的是注入的对象啊!!! 你在使用的时候,需要注入谁,那么就得构造谁
            @Provides
            protected Service1 get() {
                System.out.println("要开始构造了噢");
                return new Service1Impl1();
            }
        });
        Application instance = injector.getInstance(Application.class);
        instance.work();
    }
}


//https://iowiki.com/guice/guice_constructor_bindings.html