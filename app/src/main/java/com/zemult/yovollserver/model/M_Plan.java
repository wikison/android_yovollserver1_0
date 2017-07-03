package com.zemult.yovollserver.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;


/**
 * 服务方案
 *
 * @author djy
 * @time 2017/4/26 15:58
 */

public class M_Plan implements Serializable {
    @Expose
    public int planId;    //	方案id
    @Expose
    public int merchantId;    //	商户id
    @Expose
    public String name;    //	标题
    @Expose
    public String note;    //	详情文字(预留)
    @Expose
    public String pics;    //	详情图片(多张","分隔)
    @Expose
    public double oldPrice;    //	原本价格
    @Expose
    public double newPrice;    //	现在价格
    @Expose
    public double prePrice;    //	订金
    @Expose
    public String alidityTime;  //有效期
    @Expose
    public String createTime;   //创建时间
    @Expose
    public int state;    //	状态(0:未启用,1:已启用)


}
