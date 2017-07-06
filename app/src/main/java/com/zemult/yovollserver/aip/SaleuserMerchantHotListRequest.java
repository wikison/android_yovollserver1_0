package com.zemult.yovollserver.aip;

import android.util.Pair;


import com.google.gson.reflect.TypeToken;
import com.zemult.yovollserver.config.Urls;
import com.zemult.yovollserver.model.apimodel.APIM_MerchantList;
import com.zemult.yovollserver.util.Convert;

import java.lang.reflect.Type;

import zema.volley.network.PostStringRequest;
import zema.volley.network.ResponseListener;

//推荐商家()        附近推荐  签约商家
public class SaleuserMerchantHotListRequest extends PostStringRequest<Type> {

    public static class Input {
        public int  operateUserId	;    //		int	否	操作的用户id(预留)
        public String center	;    //		String	是	用户中心点坐标规则：经度和纬度用","分割;默认"119.969499,31.817949"
        public String city		;    //	String	是	市编号
        public String name	;    //		String	否	场景名称(模糊)
        public int page		;    //	int	是	获取第x页的数据
        public int rows		;    //	int	是	每次获取的数据个数
        public String ejson;


        public void convertJosn() {
            ejson = Convert.securityJson(Convert.pairsToJson(
                    new Pair<String, String>("operateUserId", operateUserId + ""),
                    new Pair<String, String>("center", center),
                    new Pair<String, String>("city", city),
                    new Pair<String, String>("name", name),
                    new Pair<String, String>("page", page+""),
                    new Pair<String, String>("rows", rows+"")
            ));
        }
    }

    public SaleuserMerchantHotListRequest(Input input, ResponseListener listener) {
        super(Urls.BASIC_URL + Urls.SALEUSER_MERCHANT_HOT_LIST, input.ejson, new TypeToken<APIM_MerchantList>() {
        }.getType(), listener);

    }
}
