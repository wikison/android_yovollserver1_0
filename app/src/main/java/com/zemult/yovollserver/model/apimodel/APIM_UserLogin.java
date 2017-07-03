package com.zemult.yovollserver.model.apimodel;

import com.google.gson.annotations.Expose;
import com.zemult.yovollserver.model.CommonResult;
import com.zemult.yovollserver.model.M_Userinfo;


/**
 * Created by zhangkai on 2016/6/8.
 */

public class APIM_UserLogin extends CommonResult {
    @Expose
   public M_Userinfo userInfo;
    @Expose
    public  M_Userinfo UserInfo;
}
