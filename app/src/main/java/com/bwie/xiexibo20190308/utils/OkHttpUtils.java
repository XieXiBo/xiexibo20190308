package com.bwie.xiexibo20190308.utils;

import android.util.Log;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/8 13:51:00
 * @Description: OK网络请求封装
 */
public class OkHttpUtils {
    /**
     * 单例模式
     */
    private static OkHttpUtils okHttpUtils;

    private OkHttpUtils() {

    }

    public static OkHttpUtils getInstance() {
        if (okHttpUtils == null) {
            synchronized (OkHttpUtils.class) {
                if (okHttpUtils == null) {
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }

    /**
     * 二次封装OkHttpClient
     */
    private static OkHttpClient okHttpClient;

    private static synchronized OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("lj", "log: " + message);
            }
        });
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        return okHttpClient;
    }

    /**
     * doGet
     */
    public void doGet(String url, Callback callback) {
        OkHttpClient okHttpClient = getOkHttpClient();
        Request build = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(build).enqueue(callback);
    }
}
