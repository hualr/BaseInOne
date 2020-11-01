public class TMethod1<E> {
/*
 public 和返回之间的<T>是确认是否为泛型方法的唯一手段
*/

    //如果静态方法要使用泛型 必须将静态方法定义为泛型方法.因为静态方法无法访问类上定义的泛型,所以静态方法操作的应用数据类型不确定的时候,得用泛型方法
    public static <E> void hello(E e) {

    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        TMethod1 tMethod1 = new TMethod1();
        System.out.println(tMethod1.getMethod("fe"));

        //虽然说类中是T 方法也是T 但是泛型方法的T实际上可以和普通是不一样的
        TMethod1<Integer> tMethod2 = new TMethod1();
        System.out.println(tMethod2.getMethod("fe"));

        tMethod1.printMsg("111", 222, "e", 43.3);

    }

    public <E> E getMethod(E e) {
        System.out.println(e);
        return e;
    }

    //这不是一个泛型方法
    public E getMethod2(E e) {
        System.out.println(e);
        return e;
    }

    //多个方法
    public <T> void printMsg(T... args) {
        for (T t : args) {
            System.out.println(t);
        }
    }
}
