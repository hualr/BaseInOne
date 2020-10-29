package com.hualr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/26 22:59
 * Version: 1.0.0
 */
public class LogDemo {
    static Logger LOG= LoggerFactory.getLogger(LogDemo.class);
    public static void main(String[] args) {
        MDC.put("userId","1234");
        LOG.info("info 级别");
        LOG.warn("warn 级别");
        LOG.error("error 级别");
        MDC.put("userId","5678");
        LOG.info("info 级别");
        LOG.warn("warn 级别");
        LOG.error("error 级别");


    }
}
