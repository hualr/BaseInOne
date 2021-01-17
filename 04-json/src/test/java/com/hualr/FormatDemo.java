package com.hualr;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import org.junit.Test;

/**
 * Function: <br>
 * Creating Time: 2021/1/13 15:43 <br>
 * Version: 1.0.0
 *
 * @author zongqi
 */
public class FormatDemo {
    /**
     *
     */
    @Test
    public void test1() {
        NumberFormat integerNumber = NumberFormat.getIntegerInstance(Locale.getDefault());
        System.out.println(integerNumber.format(1100));


        NumberFormat numberFormat = NumberFormat.getInstance();
        DecimalFormat numberDecimalFormat;
        //捕捉异常，以防强制类型转换出错
        try {
            //强制转换成DecimalFormat
            numberDecimalFormat = (DecimalFormat) numberFormat;
            //保留小数点后面三位，不足的补零,前面整数部分 每隔四位 ，用 “,” 符合隔开
            numberDecimalFormat.applyPattern("#,####.000");
            //设置舍入模式 为DOWN,否则默认的是HALF_EVEN
            numberDecimalFormat.setRoundingMode(RoundingMode.DOWN);
            //设置 要格式化的数 是正数的时候。前面加前缀
            numberDecimalFormat.setPositivePrefix("|+");
            System.out.println("|+" + numberDecimalFormat.format(123456.7891));
            //设置 要格式化的数 是正数的时候。后面加后缀
            numberDecimalFormat.setPositiveSuffix("|");
            System.out.println("|+" + numberDecimalFormat.format(123456.7891));
            //设置整数部分的最大位数
            numberDecimalFormat.setMaximumIntegerDigits(3);
            System.out.println("整数最大位数 " + numberDecimalFormat.format(123456.7891));
            //设置整数部分最小位数
            numberDecimalFormat.setMinimumIntegerDigits(10);
            System.out.println("整数最小位数 " + numberDecimalFormat.format(123456.7891));
            //设置小数部分的最大位数
            numberDecimalFormat.setMaximumFractionDigits(2);
            System.out.println("小数部分最大位数 " + numberDecimalFormat.format(123.4));
            //设置小数部分的最小位数
            numberDecimalFormat.setMinimumFractionDigits(6);
            System.out.println("小数部分最小位数 " + numberDecimalFormat.format(123.4));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 百分设置
     */
    @Test
    public void test2() {
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMinimumFractionDigits(2);
        String percent = numberFormat.format(Objects.equals(50, 0) ? 0.00 :
                (double) 50 / (double) 100);
        System.out.println(percent);
    }
}
