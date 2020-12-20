package com.compare;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

import lombok.extern.slf4j.Slf4j;

/**
 * Author: zongqi <br>
 * Function: <br>
 * Creating Time：2020/10/28 22:27 <br>
 * Version: 1.0.0 <br>
 */
@Slf4j
public class ForTest {
    public static void main(String[] args) {
        LOGGER.info("compare info 级别");
        aVoid();
    }

    static void aVoid() {
        LOGGER.severe("compare error 级别");
        bVoid();
    }

    static void bVoid() {
        LOGGER.warning("compare warn 级别");
        LOGGER.severe("compare error 级别");


    }
}
