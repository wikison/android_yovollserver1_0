package com.zemult.yovollserver.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import zema.volley.network.VolleyUtil;

/**
 * Created by Wikison on 2017/6/20.
 */

public class AppApplication extends Application {

    private static AppApplication mAppApplication;
    private static Context mContext;
    private static Handler handler;
    private static AppApplication _instance;

    public AppApplication() {
        _instance = this;
    }

    public static AppApplication instance() {
        if (_instance == null) {
            throw new IllegalStateException("Application not init!!!");
        }
        return _instance;
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
        mAppApplication = this;

        initialize();
    }

    private void initialize() {
        initRequestQueue();
    }

    private void initRequestQueue() {
        VolleyUtil.initialize(mContext);
    }

}
