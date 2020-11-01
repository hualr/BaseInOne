package com.hualr.okhttp;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

//将某个json发送出去
public class Post2 {
    private int fetch;
    private int fe;


    public static void main(String[] args) throws IOException {
        String url = "http://wwww.baidu.com";

        System.out.println("fe");
        OkHttpClient okHttpClient = new OkHttpClient();

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        String json="生成的json";
        RequestBody requestBody=RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(url)
                //添加键值对
                .header("Accept","application/json")
                .header("Authorization","Basic YWRtaW46YWRtaW4=")
                .post(requestBody)
                .build();
         okHttpClient.newCall(request).execute();
         List<Integer> list= Arrays.asList(1,2,3,4);

        IntStream.range(0, list.size()).forEach(System.out::println);
         list.forEach(i-> {
             System.out.println(i);
             System.out.println("i");
         });
        System.out.println("obejct");



    }


}
