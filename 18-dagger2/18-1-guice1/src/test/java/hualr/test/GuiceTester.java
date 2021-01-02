package hualr.test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import javax.inject.Inject;
import org.junit.Test;


interface Service {
    void action();
}

/**
 * Function: <br>
 * Creating Time: 2020/12/30 21:37 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class GuiceTester {
    /**
     *
     */
    @Test
    public void test() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
            }

            @Provides
            public Service buildService() {
                String dbUrl = "localhost";
                String user = "user";
                int timeout = 100;
                return new ServiceImpl(dbUrl, user, timeout);
            }
        });
        Application app = injector.getInstance(Application.class);
        app.work();
    }
}

class ServiceImpl implements Service {
    private final String dbUrl;
    private final String user;
    private final Integer timeout;

    // @Inject
    public ServiceImpl(String dbUrl,
                       String user,
                       Integer timeout) {
        this.dbUrl = dbUrl;
        this.user = user;
        this.timeout = timeout;
    }

    @Override
    public void action() {
        System.out.println(dbUrl);
        System.out.println(user);
        System.out.println(timeout);
    }
}


class Application {

    private final Service service;

    @Inject
    public Application(Service service) {
        this.service = service;
    }

    public void work() {
        service.action();
    }
}

