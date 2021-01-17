package com.hualr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Author: zongqi <br>
 * Function: MDC的使用<br>
 * Creating Time：2020/10/26 22:59 <br>
 * Version: 1.0.0 <br>
 */
public class LOGDemo {
    static Logger LOG= LoggerFactory.getLogger(LOGDemo.class);
    public static void main(String[] args) {
        MDC.put("userId","Qzong");
        LOG.info("info 级别");
        LOG.warn("warn 级别");
        LOG.error("error 级别");
        MDC.put("userId","Peter");
        LOG.info("info 级别");
        LOG.warn("warn 级别");
        //清除后才没有
        MDC.clear();
        LOG.error("error 级别");
    }
}
