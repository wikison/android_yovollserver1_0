package com.zemult.yovollserver.model.apimodel;

import com.google.gson.annotations.Expose;
import com.zemult.yovollserver.model.CommonResult;
import com.zemult.yovollserver.model.M_Merchant;


/**
 * Created by zhangkai on 2016/6/8.
 */

public class APIM_MerchantGetinfo extends CommonResult {
    @Expose
    public M_Merchant merchant;
    @Expose
    public M_Merchant saleUserInfo;

}
