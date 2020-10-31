package com.hualr.json.bean;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/30 21:46
 * Version: 1.0.0
 */
public class Customer {
    String name;
    int age;
    String sex;
    Teacher teacher;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Teacher getTeacher() {
        return teacher;
    }



}
