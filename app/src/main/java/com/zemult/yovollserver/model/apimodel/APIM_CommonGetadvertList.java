package com.zemult.yovollserver.model.apimodel;

import com.google.gson.annotations.Expose;
import com.zemult.yovollserver.model.CommonResult;
import com.zemult.yovollserver.model.M_Ad;

import java.util.List;


/**
 * Created by zhangkai on 2016/6/8.
 */

public class APIM_CommonGetadvertList extends CommonResult {
    @Expose
   public  List<M_Ad> advertList ;//广告列表

}
