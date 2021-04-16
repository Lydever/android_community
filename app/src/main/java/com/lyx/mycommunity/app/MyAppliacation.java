package com.lyx.mycommunity.app;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author create by liyingxia
 * 创建日期：2020/12/8 22
 * 描述：
 * 作用: MyAppliacation页面
 */
public class MyAppliacation extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initOkhttpClient();
    }

    private void initOkhttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
