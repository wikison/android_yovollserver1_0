package com.zemult.yovollserver.model;

import com.google.gson.annotations.Expose;


/**
 * Created by zhangkai on 2016/6/8.
 */

public class M_Zone {
    @Expose
   public  String	name			;	//	地区名称
    @Expose
    public  String	code			;	//	地区编号

    @Expose
    public  int	type			;
    @Expose
    public  int	merchantId			;
    @Expose
    public  int	saleUserId			;
}
