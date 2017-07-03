package com.zemult.yovollserver.aip;

import android.util.Pair;

import com.google.gson.reflect.TypeToken;
import com.zemult.yovollserver.config.Urls;
import com.zemult.yovollserver.model.apimodel.APIM_PicList;
import com.zemult.yovollserver.util.Convert;

import java.lang.reflect.Type;

import zema.volley.network.PostStringRequest;
import zema.volley.network.ResponseListener;

//获取商家相册的照片列表(非证件照)
public class MerchantPicListRequest extends PostStringRequest<Type> {

    public static class Input {
        public int page;    //	获取第x页的数据
        public int rows;    //	每次获取的数据个数
        public int merchantId;    //商家(场景)id

        public String ejson;


        public void convertJson(){
            ejson= Convert.securityJson(Convert.pairsToJson(
                    new Pair<String, String>("merchantId", merchantId+""),
                    new Pair<String, String>("page", page+""),new Pair<String, String>("rows", rows+"")));
        }

    }

    public MerchantPicListRequest(Input input, ResponseListener listener) {
        super(Urls.BASIC_URL+Urls.MERCHANT_PIC_LIST,input.ejson , new TypeToken<APIM_PicList>() {
        }.getType() , listener);

    }
}
