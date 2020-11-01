import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import service.Demo1;
import service.Demo2;

class TValue<T1, T2 extends Integer, T3 extends Demo1 & Demo2> {
    public static void main(String[] args) {
        //1 调用这个方法来获取Class对象的泛型变量信息
        //Class类和Method方法均实现了GenericDeclaration接口.所以可以直接调用getTypeParameters
        TypeVariable<Class<TValue>>[] typeVariables = TValue.class.getTypeParameters();
        for (TypeVariable<Class<TValue>> typeVariable : typeVariables) {
            System.out.println("变量名称:" + typeVariable.getName());
            System.out.println("变量的声明处为:" + typeVariable.getGenericDeclaration());
            Type[] bounds = typeVariable.getBounds();
            System.out.println("该变量的上边界数量为" + bounds.length);
            System.out.println("这个变量上边界清单:");
            for (Type bound : bounds) {
                System.out.println(bound.getTypeName());
            }
            System.out.println("---------------------------------------------");
        }

    }

}
