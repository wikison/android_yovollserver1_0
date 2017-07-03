package com.zemult.yovollserver.aip.common;

import com.google.gson.reflect.TypeToken;
import com.zemult.yovollserver.config.Urls;
import com.zemult.yovollserver.model.apimodel.APIM_PresentList;

import java.lang.reflect.Type;

import zema.volley.network.PostStringRequest;
import zema.volley.network.ResponseListener;

//获取管家职位种类
public class CommonPositionRequest extends PostStringRequest<Type> {

    public CommonPositionRequest(ResponseListener listener) {
        super(Urls.BASIC_URL + Urls.COMMON_POSITION, "", new TypeToken<APIM_PresentList>() {
        }.getType(), listener);

    }
}
