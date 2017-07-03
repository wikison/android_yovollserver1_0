package com.zemult.yovollserver.aip.common;

import android.util.Pair;

import com.google.gson.reflect.TypeToken;
import com.zemult.yovollserver.config.Urls;
import com.zemult.yovollserver.model.CommonResult;
import com.zemult.yovollserver.util.Convert;

import java.lang.reflect.Type;

import zema.volley.network.PostStringRequest;
import zema.volley.network.ResponseListener;

//验证短信验证码
public class CommonCheckcodeRequest extends PostStringRequest<Type> {

    public static class Input {
        public String phone;       //手机号
        public String code;       //验证码
        public String ejson;


        public void convertJson(){
            ejson= Convert.securityJson(Convert.pairsToJson(
                    new Pair<String, String>("code", code),
                    new Pair<String, String>("phone", phone)));
        }

    }

    public CommonCheckcodeRequest(Input input, ResponseListener listener) {
        super(Urls.BASIC_URL+Urls.COMMON_CHECKCODE,input.ejson , new TypeToken<CommonResult>() {
        }.getType() , listener);

    }
}
