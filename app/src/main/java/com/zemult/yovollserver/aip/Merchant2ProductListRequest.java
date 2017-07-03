package com.zemult.yovollserver.aip;

import android.util.Pair;

import com.google.gson.reflect.TypeToken;
import com.zemult.yovollserver.config.Urls;
import com.zemult.yovollserver.model.apimodel.APIM_ProductList;
import com.zemult.yovollserver.util.Convert;

import java.lang.reflect.Type;

import zema.volley.network.PostStringRequest;
import zema.volley.network.ResponseListener;

//商家的产品列表(已上架的)
public class Merchant2ProductListRequest extends PostStringRequest<Type> {
    public static class Input {
        public int merchantId;    //	商户id
        public String ejson;

        public void convertJson() {
            ejson = Convert.securityJson(Convert.pairsToJson(
                    new Pair<String, String>("merchantId", merchantId + "")));
        }
    }

    public Merchant2ProductListRequest(Input input, ResponseListener listener) {
        super(Urls.BASIC_URL + Urls.MERCHANT2_PRODUCT_LIST, input.ejson, new TypeToken<APIM_ProductList>() {
        }.getType(), listener);

    }
}
