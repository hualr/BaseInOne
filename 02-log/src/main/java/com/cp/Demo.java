package com.cp;

import lombok.extern.slf4j.Slf4j;

/**
 * Author: zongqi <br>
 * Function: <br>
 *     <li> mlook的使用: 注意这里用的是log </li>
 *     <li> 主要观察的是修改logback对应包下的级别打印情况</li>
 * Creating Time：2020/10/28 22:27 <br>
 * Version: 1.0.0 <br>
 */
@Slf4j
public class Demo {
    public static void main(String[] args) {
        log.info("compare info 级别");
        aVoid();
    }

    static void aVoid() {
        log.error("compare error 级别");
        bVoid();
    }

    static void bVoid() {
        log.warn("compare warn 级别");
        log.error("compare error 级别");
    }
}
