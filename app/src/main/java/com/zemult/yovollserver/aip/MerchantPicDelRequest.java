package com.zemult.yovollserver.aip;

import android.util.Pair;

import com.google.gson.reflect.TypeToken;
import com.zemult.yovollserver.config.Urls;
import com.zemult.yovollserver.model.CommonResult;
import com.zemult.yovollserver.util.Convert;

import java.lang.reflect.Type;

import zema.volley.network.PostStringRequest;
import zema.volley.network.ResponseListener;

//商家删除相册照片
public class MerchantPicDelRequest extends PostStringRequest<Type> {

    public static class Input {
        public int	merchantId				;	//	商家id
        public String	picIds				;	//	上传的照片地址(多个用"，"分隔)


        public String ejson;


        public void convertJson(){
            ejson= Convert.securityJson(Convert.pairsToJson(
                    new Pair<String, String>("merchantId", merchantId+""), new Pair<String, String>("picIds",picIds)));
        }

    }

    public MerchantPicDelRequest(Input input, ResponseListener listener) {
        super(Urls.BASIC_URL+Urls.MERCHANT_PIC_DEL,input.ejson , new TypeToken<CommonResult>() {
        }.getType() , listener);

    }
}
