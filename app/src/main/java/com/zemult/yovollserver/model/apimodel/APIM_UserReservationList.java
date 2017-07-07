package com.zemult.yovollserver.model.apimodel;

import com.google.gson.annotations.Expose;
import com.zemult.yovollserver.model.CommonResult;
import com.zemult.yovollserver.model.M_Reservation;

import java.util.List;

/**
 * Created by admin on 2017/1/20.
 */

public class APIM_UserReservationList extends CommonResult {
    @Expose
    public List<M_Reservation> reservationList;

    @Expose
    public int maxpage;//当分页获取时，最大的页数

}
