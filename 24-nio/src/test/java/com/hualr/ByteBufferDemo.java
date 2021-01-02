package com.hualr;

import java.nio.ByteBuffer;
import org.junit.Test;

/**
 * Function: <br>
 * Creating Time: 2020/12/24 20:03 <br>
 * Version: 1.0.0 <br>
 *
 * @Author: zongqi
 */
public class ByteBufferDemo {
    //是什么: 一个字节缓冲区

    //属性理解

    /**
     * byte[] buff  //buff即内部用于缓存的数组。
     * position //当前读取的位置。
     * mark //为某一读过的位置做标记，便于某些时候回退到该位置。
     * capacity //初始化时候的容量。
     * limit //当写数据到buffer中时，limit一般和capacity相等，当读数据时，limit代表buffer中有效数据的长度。
     * <p>
     * /**
     * 属性理解
     */
    @Test
    public void test1() {
        ByteBuffer buffer1 = ByteBuffer.allocate(8);
        //相对第一个 多用于IO操作中
        ByteBuffer buffer2 = ByteBuffer.allocateDirect(8);

        byte[] bytes1;


    }


}
