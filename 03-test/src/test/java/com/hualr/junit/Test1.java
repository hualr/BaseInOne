package com.hualr.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/29 22:21
 * Version: 1.0.0
 */

/**
 * 一般使用单元测试会新建一个test目录存放测试代码，在生产部署的时候只需要将test目录下代码删除即可
 * 测试代码的包应该和被测试代码包结构保持一致
 * 测试类一般使用Test作为类名的后缀
 */
public class Test1 {
    /**
      * @BeforeClass:这个注解表示这个方法会在所有测试方法执行之前执行
      * 因为是static修饰的静态方法，所有只会执行一次。通常用来进行一些
      * 资源的加载，如通过JUnit测试Spring相关的东西时，可以在这个方法
      * 中加载Spring的配置文件
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
             System.out.println("this is before class");
    }
    @Test
    /**
     * 测试方法必须使用@Test修饰
     * 测试方法必须使用public void进行修饰，不能带参数
     * 测试单元中的每个方法必须可以独立测试，方法间不能有任何依赖
     * 测试方法使一般用test作为方法名的前缀
     */
    public void test1(){
        System.out.println("测试1");
    }

    /**
     * @AfterClass:这个注解表示这个方法会在所有方法执行完毕之后执行，通常用来释放资源
    */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
          System.out.println("this is after class");
    }

    /**
     * @Before:这个注解表示这个方法会在每个测试方法执行之前执行一次
     * 有多少个测试方法就会执行多少次
     */
     @Before
     public void setUp() throws Exception {
        System.out.println("this is before");
     }

     /**
       * @After:这个注解表示这个方法会在每个测试方法执行之后执行一次
       * 有多少个测试方法就会执行多少次
     */
     @After
     public void tearDown() throws Exception {
       System.out.println("this is Down");
     }
}
