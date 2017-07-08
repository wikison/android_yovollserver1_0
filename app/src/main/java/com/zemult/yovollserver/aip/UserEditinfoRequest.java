package com.zemult.yovollserver.aip;

import android.util.Pair;

import com.google.gson.reflect.TypeToken;
import com.zemult.yovollserver.config.Urls;
import com.zemult.yovollserver.model.CommonResult;
import com.zemult.yovollserver.util.Convert;

import java.lang.reflect.Type;

import zema.volley.network.PostStringRequest;
import zema.volley.network.ResponseListener;

//修改用户资料信息
public class UserEditinfoRequest extends PostStringRequest<Type> {

    public static class Input {
        public int userId;    //	用户id
        public String name;    //	名字
        public String head = "";    //	头像


        public String ejson;


        public void convertJson() {
            ejson = Convert.securityJson(Convert.pairsToJson(
                    new Pair<String, String>("userId", userId + ""),
                    new Pair<String, String>("name", name),
                    new Pair<String, String>("head", head == null ? "" : head)));
        }

    }

    public UserEditinfoRequest(Input input, ResponseListener listener) {
        super(Urls.BASIC_URL + Urls.USER_EDITINFO, input.ejson, new TypeToken<CommonResult>() {
        }.getType(), listener);

    }
}
