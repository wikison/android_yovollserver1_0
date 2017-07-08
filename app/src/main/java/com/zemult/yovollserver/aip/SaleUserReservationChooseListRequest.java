package com.zemult.yovollserver.aip;

import android.util.Pair;

import com.google.gson.reflect.TypeToken;
import com.zemult.yovollserver.config.Urls;
import com.zemult.yovollserver.model.apimodel.APIM_UserReservationList;
import com.zemult.yovollserver.util.Convert;

import java.lang.reflect.Type;

import zema.volley.network.PostStringRequest;
import zema.volley.network.ResponseListener;

/**
 * Created by Wikison on 2017/7/8.
 */
//获取服务管家的可用于生成买单的预约单列表(确认未支付且未使用的预约单且 预约时间为当天的)
public class SaleUserReservationChooseListRequest extends PostStringRequest<Type> {

    public static class Input {

        public int saleUserId;    //	约客的用户id
        public int merchantId; //商户id
        public int page;    //	获取第x页的数据
        public int rows;    //	每次获取的数据个数
        public String ejson;


        public void convertJson() {
            ejson = Convert.securityJson(Convert.pairsToJson(
                    new Pair<String, String>("saleUserId", saleUserId + ""),
                    new Pair<String, String>("merchantId",merchantId+""),
                    new Pair<String, String>("page", page + ""),
                    new Pair<String, String>("rows", rows + ""))
            );
        }

    }

    public SaleUserReservationChooseListRequest(Input input, ResponseListener listener) {
        super(Urls.BASIC_URL+Urls.SALEUSER_RESERVATION_CHOOSE_LIST, input.ejson, new TypeToken<APIM_UserReservationList>() {
        }.getType(), listener);

    }
}