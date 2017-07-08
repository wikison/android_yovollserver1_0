package com.zemult.yovollserver.aip;

import android.util.Pair;

import com.google.gson.reflect.TypeToken;
import com.zemult.yovollserver.config.Urls;
import com.zemult.yovollserver.model.CommonResult;
import com.zemult.yovollserver.util.Convert;

import java.lang.reflect.Type;

import zema.volley.network.PostStringRequest;
import zema.volley.network.ResponseListener;

//用户注册申请成为商家的服务管家(没有就新增)       1:用户只能申请1商户
public class SaleuserAddMerchantRequest extends PostStringRequest<Type> {

    public static class Input {


        public String phone		;//	String	是	手机号
        public int merchantId	;//		int	是	商家(场景)的id
        public String  position	;//		String	是	职位
        public int isOnBook		;//	int	否	是否关联 通讯录(作为服务管家 0:否,1:是)
        public String bookPhones;//			String	否	 通讯录手机号(多个用","分隔)
        public String services	;//		String	是	服务ids（多个用","分隔）
        public String name	;//		String	是	昵称
        public String head	;//		String	是	头像
        public String password	;//		String	是	密码(经过MD5加密过后的)



        public String ejson;


        public void convertJson(){
                ejson= Convert.securityJson(Convert.pairsToJson(
                        new Pair<String, String>("phone", phone),
                        new Pair<String, String>("merchantId",merchantId+""),
                        new Pair<String, String>("position",position),
                        new Pair<String, String>("isOnBook",isOnBook+""),
                        new Pair<String, String>("bookPhones",bookPhones),
                        new Pair<String, String>("services",services),
                        new Pair<String, String>("name",name),
                        new Pair<String, String>("head",head),
                        new Pair<String, String>("password",password)
                ));

        }

    }

    public SaleuserAddMerchantRequest(Input input, ResponseListener listener) {
        super(Urls.BASIC_URL+Urls.SALEUSER_ADD_MERCHANT,input.ejson , new TypeToken<CommonResult>() {
        }.getType() , listener);

    }
}
