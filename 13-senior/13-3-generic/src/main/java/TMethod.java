import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import service.Demo1;
import service.Demo2;

public class TMethod {
    public static void main(String[] args) {
        //之所以这么写是因为无法直接通过方法名+方法使用的参数来获取m1
        Method[] methods = TMethod.class.getDeclaredMethods();
        Method m1 = null;
        for (Method method : methods) {
            if (method.getName().equals("m1")) {
                m1 = method;
                break;
            }
        }


        System.out.println("------------m1方法参数类型列表信息:----------");
        Type[] genericParameterTypes = m1.getGenericParameterTypes();
        for (Type genericParameterType : genericParameterTypes) {
            //3个参数都是泛型变量类型的，对应java中的TypeVariable
            if (genericParameterType instanceof TypeVariable) {
                TypeVariable pt = (TypeVariable) genericParameterType;
                System.out.println("变量类型名称:" + pt.getTypeName());
                System.out.println("变量名称:" + pt.getName());
                System.out.println("这个变量在哪声明的:" + pt.getGenericDeclaration());
                Type[] bounds = pt.getBounds();
                System.out.println("这个变量上边界数量:" + bounds.length);
                System.out.println("这个变量上边界清单:");
                for (Type bound : bounds) {
                    System.out.println(bound.getTypeName());
                }
            } else if (genericParameterType instanceof Class) {
                Class pt = (Class) genericParameterType;
                System.out.println("参数类型名称:" + pt.getTypeName());
                System.out.println("参数类名:" + pt.getName());
            }
            System.out.println("--------------------");
        }

        //获取方法的返回值，也是一个泛型变量
        System.out.println("----------m1方法返回值类型信息:----------");
        Type genericReturnType = m1.getGenericReturnType();
        if (genericReturnType instanceof TypeVariable) {
            TypeVariable pt = (TypeVariable) genericReturnType;
            System.out.println("变量名称:" + pt.getName());
            System.out.println("这个变量在哪声明的:" + pt.getGenericDeclaration());
            Type[] bounds = pt.getBounds();
            System.out.println("这个变量上边界数量:" + bounds.length);
            System.out.println("这个变量上边界清单:");
            for (Type bound : bounds) {
                System.out.println(bound.getTypeName());
            }
            System.out.println("--------------------");
        }

        //获取方法中声明的泛型参数列表
        System.out.println("------------m1方法中声明的泛型变量类型列表:----------");
        TypeVariable<Method>[] typeParameters = m1.getTypeParameters();
        for (TypeVariable<Method> pt : typeParameters) {
            System.out.println("变量类型名称:" + pt.getTypeName());
            System.out.println("变量名称:" + pt.getName());
            System.out.println("这个变量在哪声明的:" + pt.getGenericDeclaration());
            Type[] bounds = pt.getBounds();
            System.out.println("这个变量上边界数量:" + bounds.length);
            System.out.println("这个变量上边界清单:");
            for (Type bound : bounds) {
                System.out.println(bound.getTypeName());
            }
            System.out.println("--------------------");
        }

    }

    /*定义了这样一个方法
     * 方法声明出现了几种泛型就意味着在修饰符号得加上几个泛型*/
    public <T1, T2 extends Integer, T3 extends Demo1 & Demo2> T3 m1(T1 t1, T2 t2, T3 t3, String s) {
        return t3;
    }
}
