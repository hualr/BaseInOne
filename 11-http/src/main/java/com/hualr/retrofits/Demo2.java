package com.hualr.retrofits;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * ZNN 展示retrofits的特性
 */
public class Demo2 {
    public static void main(String[] args) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.baidu.com/")
                //有何意义??
                .client(okHttpClient)
                //数据解析器 将返回的数据进行解析 默认是responseBody.可以转为为其他类型.比如下面,此时可以不需要填写responeBody
                //.addConverterFactory(JacksonConverterFactory.create())
                .build();

        IService iService = retrofit.create(IService.class);

        Call<ResponseBody> call = iService.index();

        //异步调用
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String json = response.body().string();
                    System.out.println(json);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
            }
        });
    }
}
