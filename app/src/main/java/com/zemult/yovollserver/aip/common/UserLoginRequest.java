package com.zemult.yovollserver.aip.common;

import android.util.Pair;

import com.google.gson.reflect.TypeToken;
import com.zemult.yovollserver.config.Urls;
import com.zemult.yovollserver.model.apimodel.APIM_UserLogin;
import com.zemult.yovollserver.util.Convert;

import java.lang.reflect.Type;

import zema.volley.network.PostStringRequest;
import zema.volley.network.ResponseListener;

//用户登录

public class UserLoginRequest extends PostStringRequest<Type> {
    public UserLoginRequest(Input input, ResponseListener listener) {
        super(Urls.BASIC_URL + Urls.SALEUSER_LOGIN, input.ejson, new TypeToken<APIM_UserLogin>() {
        }.getType(), listener);
    }

    public static class Input {
        public String account;       //  账号(手机号或者账号)
        public String password;       //密码(经过MD5加密过后的)
        public String device_token;       //IM推送的设备唯一标识
        public String ejson;

        public void convertJosn() {
            ejson = Convert.securityJson(Convert.pairsToJson(
                    new Pair<String, String>("account", account),
                    new Pair<String, String>("password", password),
                    new Pair<String, String>("device_token", device_token)));
        }
    }
}
