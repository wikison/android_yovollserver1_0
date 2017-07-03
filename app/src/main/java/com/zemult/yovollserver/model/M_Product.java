package com.zemult.yovollserver.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;


/**
 * 产品
 *
 * @author Wikison
 * @time 2017/7/3 15:54
 */

public class M_Product implements Serializable {
    @Expose
    public int productId;//  产品id
    @Expose
    public int merchantId;//  商户id(商户外键id)
    @Expose
    public String name;//	名称
    @Expose
    public String note;//	详情文字(预留)
    @Expose
    public String pics;    //	详情图片(多张","分隔)
    @Expose
    public double oldPrice;    //	原本价格
    @Expose
    public double newPrice;    //	现在价格
    @Expose
    public double prePrice;    //	订金
    @Expose
    public double discount;    //	佣金折扣(%---5%存5.0)
    @Expose
    public double params;    //	参数(图片或键值对-暂定多个";"分隔)
    @Expose
    public int stockNum;    //	库存数量
    @Expose
    public int saleNum;    //	已销售的数量(预留)
    @Expose
    public String createTime;    //	创建时间
    @Expose
    public int state;    //	上架状态(0:否,1:是)
}



