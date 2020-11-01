package com.hualr.retrofits;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * ZNN 展示retrofits的基本使用
 */
public class Demo1 {
    public static void main(String[] args) throws IOException {
        //1. 定义请求接口信息 @IService

        //2. 获取Retrofit实例
        String url = "http://www.baidu.com/";
        Retrofit retrofit = new Retrofit.Builder()
                //ZNN 2.1 必须以http开头 否则无法识别协议 必须以/结尾,否则会出现异常
                //GET中的注解就是从baseUrl开始的
                .baseUrl(url)
                .build();
        //  ZMM 2.2 将请求参数传入.从而利用该对象来调用服务
        IService iService = retrofit.create(IService.class);

        //3. 在需要的时候调用.然后使用okhttp来完成整个请求过程
        Call<ResponseBody> call = iService.index();

        //4.使用同步调用
        Response<ResponseBody> execute = call.execute();
        if (!execute.isSuccessful()) {
            System.out.println("请求异常");
        }
    }

}
