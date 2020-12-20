package com.hualr;

import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/11/17 16:16
 * Version: 1.0.0
 */
public class StringTest1 {
    @Test
    public void test1() {
        String m = "1131-back";
        String[] s = m.split("-back");
    }

    @Test
    public void test2() {
        String s = "fwefwefno mdfeeeefef";
        System.out.println(s.contains("no md"));
        System.out.println(s.contains("no1 md"));
        System.out.println(s.contains("no1 md1"));
        System.out.println(s.contains("no md"));
    }

    @Test
    public void test3() {
        System.out.println("".isEmpty());
    }

    @Test
    public void test4() {
        String a = "PORT-1-1-C1";
        int index = a.lastIndexOf("C");
        System.out.println(a.substring(index));
    }

    @Test
    public void test5() {
        String a = "1";
        Apple apple = new Apple();
        test5Init(a, apple);

        //第一个未改变
        System.out.println(a);
        //第二个改变了
        System.out.println(apple);
    }

    void test5Init(String a, Apple apple) {
        a = "2";
        apple.setColor("red");
    }

    @Test
    public void test6() {
        String a = "-B";
        String[] split = a.split("-");
        System.out.println(split);

        String b = "";
        String[] split1 = a.split("-");
        System.out.println(split);

        String[] ab = " - ".split("-");
        System.out.println(ab);

        String srcPhysicalChannel = "-";
        int endIndex = !srcPhysicalChannel.contains("-") ? 0 :
                srcPhysicalChannel.indexOf("-");
        String primaryChannel = srcPhysicalChannel.substring(0, endIndex);
        String backChannel = srcPhysicalChannel.substring(endIndex);

        System.out.println("----------------");
        System.out.println(primaryChannel + "  " + backChannel);
    }

    @Test
    public void test7() {
        String aString = "A-B";
        System.out.println(aString.split("-").length);
    }
}

/**
 * if (Objects.equals(business.getSrcNeId(), connectivityMessager.getNeId())) {
 * //以-为划分 primaryChannel之前 A-B 中A B
 * String srcPhysicalChannel = business.getSrcPhysicalChannel();
 * if (Objects.isNull(srcPhysicalChannel)){
 * srcPhysicalChannel="";
 * }
 * int endIndex = !srcPhysicalChannel.contains("-") ? 0 :
 * srcPhysicalChannel.indexOf("-");
 * String primaryChannel = srcPhysicalChannel.substring(0, endIndex);
 * String backChannel = srcPhysicalChannel.substring(endIndex);
 * if (business.isPrimaryBusiness()) {
 * primaryChannel = isStrEmpty(primaryChannel)?connectivityMessager.getClientPortChannel():primaryChannel;
 * } else {
 * backChannel = isStrEmpty(backChannel)?connectivityMessager.getClientPortChannel():backChannel;
 * }
 * business.setSrcPhysicalChannel(primaryChannel + backChannel);
 * } else {
 * String primaryChannel = isStrEmpty(business.getDstPhysicalChannel()) ? "" : business.getDstPhysicalChannel();
 * String backChannel = isStrEmpty(business.getDstPhysicalChannel()) ? "-" : business.getDstPhysicalChannel();
 * if (business.isPrimaryBusiness()) {
 * primaryChannel = isStrEmpty(primaryChannel)?connectivityMessager.getClientPortChannel():primaryChannel;
 * } else {
 * backChannel = isStrEmpty(backChannel)?connectivityMessager.getClientPortChannel():backChannel;
 * }
 * business.setDstPhysicalChannel(primaryChannel + backChannel);
 */