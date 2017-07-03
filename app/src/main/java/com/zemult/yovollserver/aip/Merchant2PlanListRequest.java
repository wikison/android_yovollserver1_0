package com.zemult.yovollserver.aip;

import android.util.Pair;

import com.google.gson.reflect.TypeToken;
import com.zemult.yovollserver.config.Urls;
import com.zemult.yovollserver.model.apimodel.APIM_PlanList;
import com.zemult.yovollserver.util.Convert;

import java.lang.reflect.Type;

import zema.volley.network.PostStringRequest;
import zema.volley.network.ResponseListener;

//商家的服务方案列表
public class Merchant2PlanListRequest extends PostStringRequest<Type> {
    public static class Input {
        public int merchantId;    //	商户id
        public String ejson;

        public void convertJson() {
            ejson = Convert.securityJson(Convert.pairsToJson(
                    new Pair<String, String>("merchantId", merchantId + "")));
        }
    }

    public Merchant2PlanListRequest(Input input, ResponseListener listener) {
        super(Urls.BASIC_URL + Urls.MERCHANT2_PLAN_LIST, input.ejson, new TypeToken<APIM_PlanList>() {
        }.getType(), listener);

    }
}
