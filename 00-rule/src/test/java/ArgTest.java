import org.junit.Test;

/**
 * Function: 变长方法的几个注意点<br>
 * Creating Time: 2021/2/10 <br>
 * Version: 1.0.0
 *
 * @author 宗旗
 */
public class ArgTest {
    //原则: 尽可能不用边长方法

    /**
     *  变长参数的影响:  不要让编译器去猜测
     */
    @Test
    public void test(){
        System.out.println(add1(1,2,3));
        //不要让编译器去猜测
        System.out.println(add1(1,2));
        System.out.println(add1(3,6));
    }

    int add1(int ...arg){
        int sum1=0;
        for (int i:arg){
            sum1+=i;
        }
        return sum1;
    }

    int add1(int arg1,int arg2){
        return arg1*arg2;
    }

    /**
     *  就算传入null也得指定类型
     *  简单来说 如果想要避免可变参数的问题 最有效的方法就是避免参数重载和参数重写
     */
    @Test
    public void test2(){
        method1("11");
        method1("11", (Integer) null);
    }


    void method1(String string, Integer ... is){
    }
    /*void method1(String string, String ... str){
    }*/
}
