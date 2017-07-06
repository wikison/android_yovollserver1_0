package com.zemult.yovollserver.model.apimodel;

import com.google.gson.annotations.Expose;
import com.zemult.yovollserver.model.CommonResult;
import com.zemult.yovollserver.model.M_Userinfo;

import java.util.List;


/**
 * Created by Wikison on 2017/7/4.
 */

public class APIM_UserList extends CommonResult {
    @Expose
    public List<M_Userinfo> userList;
    @Expose
    public int   maxpage;//当分页获取时，最大的页数
}
