package com.zemult.yovollserver.aip;

import android.util.Pair;

import com.google.gson.reflect.TypeToken;
import com.zemult.yovollserver.config.Urls;
import com.zemult.yovollserver.model.apimodel.APIM_MerchantGetinfo;
import com.zemult.yovollserver.util.Convert;

import java.lang.reflect.Type;

import zema.volley.network.PostStringRequest;
import zema.volley.network.ResponseListener;

/**
 * 查看服务管家详情
 */

public class SaleuserInfoRequest extends PostStringRequest<Type> {


    public static class Input {
        public int operateUserId;  //操作的用户id(预留)
        public int saleUserId;  //服务管家id(预留)
        public int merchantId;  //商家(场景)的id
        public String center;  //坐标

        public String ejson;

        public void convertJosn() {

            ejson = Convert.securityJson(Convert.pairsToJson(
                    new Pair<String, String>("operateUserId", operateUserId + ""),
                    new Pair<String, String>("saleUserId", saleUserId + ""),
                    new Pair<String, String>("merchantId", merchantId + ""),
                    new Pair<String, String>("center", center)
            ));
        }
    }

    public SaleuserInfoRequest(Input input, ResponseListener listener) {
        super(Urls.BASIC_URL + Urls.SALEUSER_INFO, input.ejson, new TypeToken<APIM_MerchantGetinfo>() {
        }.getType(), listener);

    }

}
