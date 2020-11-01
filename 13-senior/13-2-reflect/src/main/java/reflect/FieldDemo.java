package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import reflect.bean.Apple;

public class FieldDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        //这个方法得首先产生class
        Class clz2 = Apple.class;
        //获取类的所有属性==public属性

        Field[] fields1 = clz2.getFields();
        for (Field field : fields1) {
            System.out.println("属性名称:" + field.getName() +
                    "属性类型:" + field.getType());
        }

        System.out.println("------------------------------");
        Field[] fields2 = clz2.getDeclaredFields();
        for (Field field : fields2) {
            System.out.println("属性名称:" + field.getName() +
                    "属性类型:" + field.getType());
        }


    }
}
