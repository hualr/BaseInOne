package com.hualr;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.hualr.json.bean.Customer;
import com.hualr.json.bean.Student;
import com.hualr.json.bean.Teacher;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * Author: zongqi
 * Function:
 * Creating Time：2020/10/30 21:27
 * Version: 1.0.0
 */
public class JsonDemo {
    Student student;
    Teacher teacher;

    //jackson序列化
    @Test
    public void json1() throws IOException {
        //1. mapper对象创建
        ObjectMapper mapper = new ObjectMapper();
        //2. 将对象转为json-->String类型
        String studentStr = mapper.writeValueAsString(student);
        System.out.println(studentStr);
        byte[] studentBytes = mapper.writeValueAsBytes(student);
        /**
         * 实际是根据get方法来将信息从json转化为Bean的 */
        Customer studentObj1 = mapper.readValue(studentStr, Customer.class);
        System.out.println(studentObj1);

        Customer studentObj2 = mapper.readValue(studentBytes, Customer.class);
        System.out.println(studentObj2);
    }


    //GSON 序列化 obj->json
    @Test
    public void json2() {
        String jsonstr = new Gson().toJson(student);
        System.out.println(jsonstr);
        //GSON 反序列化 json->obj
        Student myobj = new Gson().fromJson(jsonstr, Student.class);
        System.out.println(myobj.getName());
    }

    //alibaba  fastJson BUG最多 能不用就不用
    @Test
    public void json3(){
        String jsons = JSON.toJSONString(student);
        System.out.println(jsons);
        Student stu = JSON.parseObject(jsons, Student.class);
        System.out.println(stu.getName());

        byte[] message = JSON.toJSONBytes(student);
        student=JSON.parseObject(message,Student.class);
        System.out.println(student);
    }

    @Before
    public void prepareResource() {
        student = new Student();
        student.setName("小明");
        student.setAge(10);
        student.setSex("男");

        teacher = new Teacher();
        teacher.setName("lisa");
        teacher.setAge(35);
        teacher.setSex("女");
        student.setTeacher(teacher);

    }
}
