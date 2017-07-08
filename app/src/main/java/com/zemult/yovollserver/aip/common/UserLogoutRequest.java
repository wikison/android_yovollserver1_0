package com.zemult.yovollserver.aip.common;

import android.util.Pair;

import com.google.gson.reflect.TypeToken;
import com.zemult.yovollserver.config.Urls;
import com.zemult.yovollserver.model.CommonResult;
import com.zemult.yovollserver.util.Convert;

import java.lang.reflect.Type;

import zema.volley.network.PostStringRequest;
import zema.volley.network.ResponseListener;

//用户退出登录

public class UserLogoutRequest extends PostStringRequest<Type> {
    public static class Input {
        public int userId;       //  用户id
        public String device_token;       //IM推送的设备唯一标识
        public String ejson;

    public void convertJson(){
        ejson= Convert.securityJson(Convert.pairsToJson(
                new Pair<String, String>("userId", userId + ""),
                new Pair<String, String>("device_token", device_token)));
    }
    }

    public UserLogoutRequest(Input input, ResponseListener listener) {
        super(Urls.BASIC_URL+Urls.USER_LOGOUT, input.ejson ,new TypeToken<CommonResult>() {
        }.getType(), listener);
    }
}
