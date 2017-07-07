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
 * Created by admin on 2017/1/20.
 */
//约客的预约记录列表     (不包含待确认的)
public class UserSaleReservationList extends PostStringRequest<Type> {

    public static class Input {

        public int saleUserId;    //	约客的用户id
        public int merchantId; //商户id
        public int state;//状态(-1:全部,0:待确认,1:带买单,5:已结束(状态2/3/4都是已结束的分支))
        public int page;    //	获取第x页的数据
        public int rows;    //	每次获取的数据个数
        public String ejson;


        public void convertJosn() {
            ejson = Convert.securityJson(Convert.pairsToJson(
                    new Pair<String, String>("saleUserId", saleUserId + ""),
                    new Pair<String, String>("merchantId", merchantId + ""),
                    new Pair<String, String>("state", state + ""),
                    new Pair<String, String>("page", page + ""),
                    new Pair<String, String>("rows", rows + ""))
            );
        }

    }

    public UserSaleReservationList(Input input, ResponseListener listener) {
        super(Urls.BASIC_URL + Urls.USER_SALE_RESERVATION_LIST, input.ejson, new TypeToken<APIM_UserReservationList>() {
        }.getType(), listener);

    }
}