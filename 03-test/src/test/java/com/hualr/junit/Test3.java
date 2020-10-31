package com.hualr.junit;

import com.hualr.junit.container.TaskTest3;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 嵌套 Test2中首先执行其中的三个
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ Test2.class, TaskTest3.class })
public class Test3 {
}
