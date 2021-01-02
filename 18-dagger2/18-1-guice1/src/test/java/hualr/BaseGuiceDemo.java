package hualr;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.hualr.MyApp1Module1;
import com.hualr.api.Service1;
import com.hualr.api.Service2;
import com.hualr.api.Service3;
import com.hualr.business.api.Application;
import com.hualr.business.injector.MyApp1;
import com.hualr.business.injector.MyApp2;
import com.hualr.business.injector.MyApp3;
import com.hualr.business.injector.MyApp4;
import com.hualr.business.injector.MyApp5;
import com.hualr.business.injector.MyApp6;
import com.hualr.impl.Service1Impl1;
import com.hualr.impl.Service2Impl;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * Function: <br>
 * Creating Time: 2020/12/24 22:56 <br>
 * Version: 1.0.0 <br>
 *
 * @author zongqi
 */
public class BaseGuiceDemo {

    @Inject
    @Named("str1")
    private String baseStr;

    /**
     * <h2>方法1:=> 构造器注入 默认使用默认的构造器,此时构造器必须注入</h2>
     * <li>当我们需要使用Application类的时候,自动会深藏MyApp对象,同时将默认构造器中的参数注入</li>
     * <li>构造器必须声明@inject 且为唯一注入构造器 否则会出现注入失败的问题</li>
     */
    @Test
    public void testConstructor() {

        Injector injector = Guice.createInjector(new MyApp1Module1());
        Application myApp = injector.getInstance(Application.class);
        myApp.work();
    }

    /**
     * <h2> 方法1:=> 属性注入 这种方法同样适用于默认构造器 将所有注入的成员变量都注入到方法中</h2>
     * <li> 此时不是构造器被注入 而是成员变量被注入 </li>
     */
    @Test
    public void testField() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(Service1.class).to(Service1Impl1.class);
                bind(Service2.class).to(Service2Impl.class);
                bind(Application.class).to(MyApp2.class);
            }
        });
        Application myApp = injector.getInstance(Application.class);
        myApp.work();
    }

    /**
     * <h2> 方法1:=> setter注入</h2>
     * <li> 此时不是构造器被注入 而是setter被注入 </li>
     */
    @Test
    public void testSetterField() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(Service1.class).to(Service1Impl1.class);
                bind(Service2.class).to(Service2Impl.class);
                bind(Application.class).to(MyApp3.class);
            }
        });
        Application myApp = injector.getInstance(Application.class);
        myApp.work();
    }

    /**
     * <h2> 方法2:=> 接口注入 直接在service接口上进行绑定 就不需要另外的bind方法了</h2>
     * <li> 注意到,这个使用方式我认为没多大用处</li>
     */
    @Test
    public void testInterface() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                //nothing here
            }
        });
        Service3 instance = injector.getInstance(Service3.class);
        instance.action("inject by sevice");
    }

    /**
     * <h2> 方法3: 直接指定new 对象</h2>
     * <li> 在这种场景下,注入的类并不需要自动注入构造器 存在多个构造器的时候,用这种方式是最保险的</li>
     */
    @Test
    public void testMyApp2() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(Service1.class).to(Service1Impl1.class);
                bind(Service2.class).to(Service2Impl.class);
                bind(Application.class).toInstance(new MyApp1(new Service1Impl1(), new Service2Impl(), true));
            }
        });
        Application myApp = injector.getInstance(Application.class);
        myApp.work();

    }

    /**
     * <h2>方法四: 选项注入 如果之后没有绑定 那么就默认了</h2>
     */
    @Test
    public void testMyApp4() {

        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                //开关
                //bind(Service1.class).to(Service1Impl2.class);
                bind(Application.class).toInstance(new MyApp4());
            }
        });
        Application myApp = injector.getInstance(Application.class);
        myApp.work();
    }

    /**
     * <h2> 方法五: 静态属性注入</h2>
     */
    @Test
    public void testMyApp5() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                //首先 如果想要注入Service1 那么就new 一个Service1Impl
                bind(Service1.class).to(Service1Impl1.class);
                //其次,如果想要静态注入 得利用这个方法将对象类注入进去
                requestStaticInjection(Service1Impl1.class);
                bind(Application.class).to(MyApp6.class);
            }
        });
        Application myApp = injector.getInstance(Application.class);
        myApp.work();
    }

    /**
     * <h2> 方法六; 构造器的使用</h2>
     */
    @Test
    public void testMyApp6() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @SneakyThrows
            @Override
            protected void configure() {
                bind(String.class).annotatedWith(Names.named("str")).toInstance("hahah");
                bind(MyApp5.class).toConstructor(MyApp5.class.getConstructor(String.class));
                bind(Application.class).to(MyApp5.class);
            }
        });
        Application myApp = injector.getInstance(Application.class);
        myApp.work();
    }

    /**
     * <h2> 基本类型注入</h2>
     */
    @Test
    public void testStr() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                //1. 找到注解 2.绑定常量
                bindConstant().annotatedWith(Names.named("str1")).to("Zongqi");
            }
        });
        BaseGuiceDemo instance = injector.getInstance(BaseGuiceDemo.class);
        System.out.println(instance.baseStr);
    }


}
