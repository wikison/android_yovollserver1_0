package com.zemult.yovollserver.model.apimodel;

import com.google.gson.annotations.Expose;
import com.zemult.yovollserver.model.CommonResult;
import com.zemult.yovollserver.model.M_Bill;
import com.zemult.yovollserver.model.M_Present;
import com.zemult.yovollserver.model.M_Reservation;

import java.util.List;


/**
 *
 * @author djy
 * @time 2017/1/20 13:25
 */

public class APIM_PresentList extends CommonResult {
    @Expose
    public List<M_Present> userPresentList;
    @Expose
    public List<M_Present> sysPresentList;
    @Expose
    public List<M_Bill> moneyList;
    @Expose
    public List<M_Reservation> positionList;

}
