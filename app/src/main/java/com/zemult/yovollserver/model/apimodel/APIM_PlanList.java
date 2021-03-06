package com.zemult.yovollserver.model.apimodel;

import com.google.gson.annotations.Expose;
import com.zemult.yovollserver.model.CommonResult;
import com.zemult.yovollserver.model.M_Plan;

import java.util.List;


/**
 * Created by Wikison on 2017/7/3.
 */

public class APIM_PlanList extends CommonResult {
    @Expose
    public List<M_Plan> merchantPlanList;
    @Expose
    public int maxpage;
}
