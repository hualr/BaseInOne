package com.compare;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/28 22:27
 * Version: 1.0.0
 */
public class ForTest {
    static Logger LOG= LoggerFactory.getLogger(ForTest.class);
    public static void main(String[] args) {
        LOG.info("compare info 级别");
        aVoid();
    }
    static void aVoid(){
        LOG.error("compare error 级别");
        bVoid();
    }
    static void bVoid(){
        LOG.warn("compare warn 级别");
        LOG.error("compare error 级别");


    }
}
