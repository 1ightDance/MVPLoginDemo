package com.example.lightdance.logintest.util;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 用于网络传送数据的工具类，通过重写okhttp3.Callback的onResponse和onFailure方法控制通信之后取到的数据
 *
 * @author LightDance
 * @date 2018/2/20.
 */

public class HttpUtil {
    private final static int CONNECT_TIMEOUT = 30;
    private final static int READ_TIMEOUT = 60;
    private final static int WRITE_TIMEOUT = 40;
    //设置读写连接的超时时间
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .build();

    /**
     * enqueue方法内部已开子线程，取数据的方法
     *
     * @param address  地址
     * @param callback 回调接口
     */
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        Request request = new Request.Builder()
                .url(address).build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * enqueue方法内部已开子线程，传数据的方法
     *
     * @param address     地址
     * @param requestBody 传送的数据，需要构造{@link JSONObject}JSON数据传入
     * @param callback    回调接口
     */
    public static void sendOkHttpRequest(String address, RequestBody requestBody, okhttp3.Callback callback) {
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
