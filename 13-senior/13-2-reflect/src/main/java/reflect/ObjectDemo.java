package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import reflect.bean.Egg;

//利用反射创建对象
public class ObjectDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        //获取类的class对象
        Class clz = Class.forName("com.hualr.API.Apple");
        System.out.println("直接获取的类为:" + clz);

        //利用class对象创建构造器
        Constructor appleConstructor = clz.getConstructor();
        System.out.println("构造器为:" + appleConstructor);

        //利用构造器创建对象
        Object appleObj1 = appleConstructor.newInstance();
        //由于此时是Object类,所以不能执行Apple的方法
        System.out.println("构造器产生的对象为:" + appleObj1);

        Egg eggObj = (Egg) appleConstructor.newInstance("blue", 100);
        System.out.println("构造器产生的对象为:" + eggObj
                + "Color:" + eggObj.getColor()
                + "Weight:" + eggObj.getWeight());

    }
}
