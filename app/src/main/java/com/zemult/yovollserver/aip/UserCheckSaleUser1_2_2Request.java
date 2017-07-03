package com.zemult.yovollserver.aip;

import android.util.Pair;

import com.google.gson.reflect.TypeToken;
import com.zemult.yovollserver.config.Urls;
import com.zemult.yovollserver.model.CommonResult;
import com.zemult.yovollserver.util.Convert;

import java.lang.reflect.Type;

import zema.volley.network.PostStringRequest;
import zema.volley.network.ResponseListener;

//判断用户是否可以申请商家的服务管家
public class UserCheckSaleUser1_2_2Request extends PostStringRequest<Type> {

    public static class Input {
        public int	userId				;	//	用户id(申请的)
        public int	merchantId				;	//	商家(场景)的id


        public String ejson;


        public void convertJson(){
            ejson= Convert.securityJson(Convert.pairsToJson(
                    new Pair<String, String>("userId", userId+""), new Pair<String, String>("merchantId", merchantId+"")));
        }

    }

    public UserCheckSaleUser1_2_2Request(Input input, ResponseListener listener) {
        super(Urls.BASIC_URL+Urls.USER_CHECK_SALEUSER_1_2_2,input.ejson , new TypeToken<CommonResult>() {
        }.getType() , listener);

    }
}
