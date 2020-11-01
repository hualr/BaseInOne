package com.hualr.okhttp;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 初步使用
 * */
 class Get1 {
    public static void main(String[] args) throws IOException {
        String url = "http://wwww.baidu.com";

        //1.新建client对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2. 新建request对象
        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        //3. 将reuquest对象封装为call
        Call call = okHttpClient.newCall(request);


        //4.1 执行方法
        //call.execute();
        //4.2 执行异步方法
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("failure");
            }

            @Override
            //ZNN 打断点可以看到获取的内容
            public void onResponse(Call call, Response response) throws IOException {
                //string不是tostring方法
                String data=response.body().string();
                System.out.println(data);
            }
        });
    }
}
