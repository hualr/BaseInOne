package com.hualr.time;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/31 10:06
 * Version: 1.0.0
 */
public class TimeTest {
    //日期
    @Test
    public void test1() {
        LocalDate date = LocalDate.of(2019, 3, 18);
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int monthLength = date.lengthOfMonth();

        System.out.println("year:" + year + ",month:" + month + ",week:" + dayOfWeek +
                ",day:" + day + ",monthLength:" + monthLength);
        LocalDate localDate = LocalDate.now();
        System.out.println("当前时间:" + localDate);
    }

    //时间
    @Test
    public void test2() {
        LocalTime time = LocalTime.of(8, 30, 00);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        System.out.println("hour:" + hour + ",minute:" + minute + ",second:" + second);
    }

    @Test
    public void test3(){
        LocalDateTime now = LocalDateTime.now();
        //打印当前日期+时间
        System.out.println(now);
        System.out.println(now.toLocalDate());
        System.out.println(now.toLocalTime());
        LocalDate date=LocalDate.parse("1876-03-18");
        System.out.println(date);
    }

    //时间修改
    @Test
    public void test4(){
        LocalDate date = LocalDate.now();
        System.out.println(date);
        System.out.println(date.withMonth(4));
        System.out.println(date.withDayOfMonth(20));
        System.out.println(date.plusDays(2));
    }

    //时间差
    @Test
    public void test5(){
        LocalTime time1=LocalTime.now();
        LocalTime time2 = LocalTime.of(10, 54, 00);
        Duration duration = Duration.between(time1, time2);
        long seconds = duration.getSeconds(); // 13565
        System.out.println(seconds);

        LocalDate day1=LocalDate.now();
        LocalDate day2=LocalDate.of(2020,11,1);
        Period period=Period.between(day1, day2);
        System.out.println(period.getDays());
    }
    //比较时间先后 闰年等
    @Test
    public void test6(){
        LocalDate date1=LocalDate.now();
        LocalDate date2 = LocalDate.of(2020,8,13);
        System.out.println(date1.isAfter(date2));
        System.out.println(date1.isBefore(date2));
        System.out.println(date1.isLeapYear());
    }
}