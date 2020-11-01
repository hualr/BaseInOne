package com.hualr.okhttp;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class Post1 {
    public static void main(String[] args) throws IOException {
        String url = "http://wwww.baidu.com";

        OkHttpClient okHttpClient = new OkHttpClient();

        //填写表单--目前用的不多
        FormBody.Builder form = new FormBody.Builder();
        form.add("name", "zq");
        form.add("age", "24");
        FormBody formBody = form.build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        //3. 将reuquest对象封装为call
        Call call = okHttpClient.newCall(request);
        call.execute();
    }
}
