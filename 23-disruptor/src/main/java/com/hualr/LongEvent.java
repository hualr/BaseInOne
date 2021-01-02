package com.hualr;

import lombok.Data;

/**
 * Author: zongqi.<br>
 * Function:  define for carry data<br>
 * Creating Time: 2020/12/23 23:16 <br>
 * Version: 1.0.0 <br>
 */
@Data
public class LongEvent {
    private long value;

    public void set(long value) {
        this.value = value;
    }
}