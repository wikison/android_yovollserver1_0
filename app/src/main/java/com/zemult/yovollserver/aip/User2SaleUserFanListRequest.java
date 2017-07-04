package com.zemult.yovollserver.aip;

import android.util.Pair;

import com.google.gson.reflect.TypeToken;
import com.zemult.yovollserver.config.Urls;
import com.zemult.yovollserver.model.apimodel.APIM_UserList;
import com.zemult.yovollserver.util.Convert;

import java.lang.reflect.Type;

import zema.volley.network.PostStringRequest;
import zema.volley.network.ResponseListener;

/**
 * Created by Wikison on 2017/7/4.
 */
//TA的粉丝
public class User2SaleUserFanListRequest extends PostStringRequest<Type> {

    public static class Input {
        public int saleUserId;   //服务管家用户id
        public String name;    //昵称
        public int page;    //	获取第x页的数据
        public int rows;    //	每次获取的数据个数


        public String ejson;


        public void convertJson() {
                ejson = Convert.securityJson(Convert.pairsToJson(
                        new Pair<String, String>("saleUserId", saleUserId + ""),
                        new Pair<String, String>("name", name),
                        new Pair<String, String>("page", page + ""),
                        new Pair<String, String>("rows", rows + ""))
                );

        }

    }

    public User2SaleUserFanListRequest(Input input, ResponseListener listener) {
        super(Urls.BASIC_URL+Urls.USER2_SALEUSER_FANSLIST, input.ejson, new TypeToken<APIM_UserList>() {
        }.getType(), listener);

    }
}
