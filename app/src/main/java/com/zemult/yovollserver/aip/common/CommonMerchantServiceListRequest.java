package com.zemult.yovollserver.aip.common;

import android.util.Pair;

import com.google.gson.reflect.TypeToken;
import com.zemult.yovollserver.config.Urls;
import com.zemult.yovollserver.model.apimodel.APIM_ServiceList;
import com.zemult.yovollserver.util.Convert;

import java.lang.reflect.Type;

import zema.volley.network.PostStringRequest;
import zema.volley.network.ResponseListener;

//获取服务项列表(注册管家选择)
public class CommonMerchantServiceListRequest extends PostStringRequest<Type> {
    public static class Input {
        public int operateUserId;    //	操作的用户id(预留)
        public int merchantId;    //	商户id
        public String ejson;

        public void convertJson() {
            ejson = Convert.securityJson(Convert.pairsToJson(
                    new Pair<String, String>("operateUserId", operateUserId + ""),
                    new Pair<String, String>("merchantId", merchantId + "")));
        }
    }

    public CommonMerchantServiceListRequest(Input input, ResponseListener listener) {
        super(Urls.BASIC_URL + Urls.COMMON_MERCHANT_SERVICE_LIST, input.ejson, new TypeToken<APIM_ServiceList>() {
        }.getType(), listener);

    }
}
