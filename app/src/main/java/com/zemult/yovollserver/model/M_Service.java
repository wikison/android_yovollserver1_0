package com.zemult.yovollserver.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;


/**
 * Created by Wikison on 2017/07/05.
 * 服务项
 */

public class M_Service implements Serializable {
    @Expose
    public int serviceId;    //	服务项id
    @Expose
    public String name;    //	行业名称
    @Expose
    public String icon;    //	行业图标 地址
    @Expose
    public List<M_Service> childs;    //	子服务项


}
