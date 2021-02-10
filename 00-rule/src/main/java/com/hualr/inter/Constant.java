package com.hualr.inter;

import java.util.Random;

/**
 * 对于一个常量 不要给其任何作为常量可能改变的空间 下图就会存在问题
 */
public interface Constant {
    static final int RAND_CONSTANT=new Random().nextInt();
}
