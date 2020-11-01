package com.hualr.retrofits;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;


//ZNN 该接口不可以继承于其他接口

/**
 * 第一步,定义接口,该接口主要定义客户机对服务器的操作路径和方法(GET/POST等)
 * 注意:
 * 1. 首先,该接口不可以继承于其他接口
 */
public interface IService {

    //1. 定义一个get方法,其路径为 baseUrl/index.html
    @GET("index.html")
    Call<ResponseBody> index();


    //2. 定义 ZMM Headers方法 主要给报文添加header.如下表示的是给服务器的报文时json格式,base认证.这是一个PUT方法
    @Headers({"Accept: application/json", "Authorization: Basic YWRtaW46YWRtaW4="})
    @PUT("/restconf/config/hwresource:dci-connectivity")
    // 3. ZMM Body方法 主要用于非表单请求体
    Call<ResponseBody> storeConnService(@Body RequestBody inputInfo);


}
