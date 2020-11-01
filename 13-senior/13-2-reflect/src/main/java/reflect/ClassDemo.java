package reflect;


import reflect.bean.Apple;

//获取某个类的class对象的三种方法
public class ClassDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(Apple.class);
        //这个方法需要放入全路径
        System.out.println(Class.forName("com.hualr.API.Apple"));
        System.out.println(new Apple().getClass().getSimpleName());
    }
}
