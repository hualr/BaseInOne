package com.hualr.okhttp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 超时时间demo
 *  okhttp底层基于socket 所以timeout时间是给socket的connect read 以及write
 */
 class Get2 {
    public static void main(String[] args) throws IOException {
        String url = "http://wwww.baidu.com";

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                                                      .connectTimeout(10, TimeUnit.SECONDS)//连接超时时间
                                                      .readTimeout(20L,TimeUnit.SECONDS)//读取超时时间
                                                      .writeTimeout(10L, TimeUnit.SECONDS)//写超时
                                                      .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).execute();
    }
}
