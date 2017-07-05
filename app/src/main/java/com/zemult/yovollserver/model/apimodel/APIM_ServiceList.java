package com.zemult.yovollserver.model.apimodel;

import com.google.gson.annotations.Expose;
import com.zemult.yovollserver.model.CommonResult;
import com.zemult.yovollserver.model.M_Service;

import java.util.List;


/**
 * Created by Wikison on 2017/7/5.
 */

public class APIM_ServiceList extends CommonResult {
    @Expose
    public List<M_Service> serviceList;
}
