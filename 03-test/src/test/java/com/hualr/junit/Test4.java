package com.hualr.junit;
import static junit.framework.TestCase.assertEquals;

import com.hualr.Claculate;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * 批量数据验证生效情况
 *  1、更改测试运行器为RunWith(Parameterized.class)
 */
@RunWith(Parameterized.class)
public class Test4 {

    /**
     * 2、声明变量用来存放预期值与结果值
     */
    int except;  //用来存储预期结果
    int input1;  //用来存储第一个输入参数
    int input2;  //用来存储第二个输入参数

    /**
     *3、声明一个返回值为Collection的公共静态方法，并使用@Parameters进行修饰
     */
    @Parameterized.Parameters
    public static Collection<Object[]> initTestData(){
        return Arrays.asList(new Object[][]{{3,1,2}, {10,5,5}, {6,4,2}, {7,3,4}});
    }

    public Test4(int except,int input1,int input2){
        this.except = except;
        this.input1 = input1;
        this.input2 = input2;
    }

    /**
     * 4、位测试类声明一个带有参数的公共构造方法，并在其中为声明变量赋值
     */
    @Test
    public void testAdd() {
        assertEquals(except, new Claculate().add(input1, input2));
    }

}