package com.zemult.yovollserver.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.flyco.roundview.RoundLinearLayout;
import com.flyco.roundview.RoundTextView;
import com.zemult.yovollserver.R;
import com.zemult.yovollserver.aip.SaleuserAddMerchantRequest;
import com.zemult.yovollserver.aip.common.CommonMerchantServiceListRequest;
import com.zemult.yovollserver.app.BaseActivity;
import com.zemult.yovollserver.config.Constants;
import com.zemult.yovollserver.model.CommonResult;
import com.zemult.yovollserver.model.apimodel.APIM_ServiceList;
import com.zemult.yovollserver.util.SlashHelper;
import com.zemult.yovollserver.util.StringUtils;
import com.zemult.yovollserver.util.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;
import zema.volley.network.ResponseListener;

/**
 * Created by Wikison on 2017/6/26.
 * 成为服务管家第二步
 */

public class BeServerGuideSecondActivity extends BaseActivity {

    @Bind(R.id.lh_btn_back)
    Button lhBtnBack;
    @Bind(R.id.ll_back)
    LinearLayout llBack;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.ll_right)
    LinearLayout llRight;
    @Bind(R.id.iv_right2)
    ImageView ivRight2;
    @Bind(R.id.ll_right2)
    LinearLayout llRight2;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.lh_tv_title)
    TextView lhTvTitle;
    @Bind(R.id.lh_btn_right)
    Button lhBtnRight;
    @Bind(R.id.lh_btn_rightiamge)
    Button lhBtnRightiamge;
    @Bind(R.id.rll_bind_merchant)
    RoundLinearLayout rllBindMerchant;
    @Bind(R.id.rll_position)
    RoundLinearLayout rllPosition;
    @Bind(R.id.rll_user_info)
    RoundLinearLayout rllUserInfo;
    @Bind(R.id.rll_notify)
    RoundLinearLayout rllNotify;
    @Bind(R.id.rll_contact)
    RoundLinearLayout rllContact;
    @Bind(R.id.cb_agree)
    CheckBox cbAgree;
    @Bind(R.id.tv_protocol)
    TextView tvProtocol;
    @Bind(R.id.rtv_finish)
    RoundTextView rtvFinish;

    String strPhone;

    int INFOCODE=1;
    int POSTIONCODE=2,merchantId;
    String services,postionName,headString,username,password;
    SaleuserAddMerchantRequest saleuserAddMerchantRequest;
    @Override
    public void setContentView() {
        setContentView(R.layout.activity_be_server_2);
    }

    @Override
    public void init() {
        initData();
        initView();
        registerReceiver(new String[]{ Constants.BROCAST_BE_SERVER_MANAGER_SUCCESS});
    }

    private void initData() {
        strPhone=getIntent().getStringExtra("strPhone");
        strPhone="15861153231";
    }

    private void initView() {
        lhTvTitle.setText("成为服务管家");
    }

    @OnClick({R.id.rll_bind_merchant, R.id.rll_position, R.id.rll_user_info, R.id.rll_notify, R.id.rll_contact, R.id.tv_protocol, R.id.rtv_finish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rll_bind_merchant:
                Intent  merchantintent =new Intent(BeServerGuideSecondActivity.this,BindMerchantActivity.class);
                startActivity(merchantintent);
                break;
            case R.id.rll_position:
                Intent  positionintent =new Intent(BeServerGuideSecondActivity.this,PositionSetActivity.class);
                startActivityForResult(positionintent,POSTIONCODE);
                break;
            case R.id.rll_user_info:
                Intent  infointent =new Intent(BeServerGuideSecondActivity.this,SetUserInfoActivity.class);
                startActivityForResult(infointent,INFOCODE);
                break;
            case R.id.rll_notify:
                break;
            case R.id.rll_contact:
                break;
            case R.id.tv_protocol:
                break;
            case R.id.rtv_finish:

                if(merchantId==0){
                    ToastUtil.showMessage("请选择商户");
                    return;
                }
                if(StringUtils.isBlank(postionName)){
                    ToastUtil.showMessage("请选择职位");
                    return;
                }
                if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
                    ToastUtil.showMessage("请填写个人资料");
                    return;
                }
                if(StringUtils.isBlank(headString)){
                    ToastUtil.showMessage("请设置用户头像");
                    return;
                }
                if(!cbAgree.isChecked()){
                    ToastUtil.showMessage("请阅读并接受协议");
                    return;
                }
                ToastUtil.showMessage(strPhone+","+merchantId+","+postionName+","+services+","+username+","+headString+","+password );

//                saleuserAddMerchantRequest();

                break;
        }
    }



    private void saleuserAddMerchantRequest() {
        if (saleuserAddMerchantRequest != null) {
            saleuserAddMerchantRequest.cancel();
        }
        SaleuserAddMerchantRequest.Input input = new SaleuserAddMerchantRequest.Input();
        input.phone = strPhone;
        input.merchantId = merchantId;
        input.position =postionName;
        input.isOnBook =0;//是否关联 通讯录(作为服务管家 0:否,1:是)
        input.bookPhones ="";// 通讯录手机号(多个用","分隔)
        input.services =services;
        input.name =username;
        input.head =headString;
        input.password=password;


        input.convertJson();

        saleuserAddMerchantRequest = new SaleuserAddMerchantRequest(input, new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }

            @Override
            public void onResponse(Object response) {
                if (((CommonResult) response).status == 1) {
                    Intent  mainintent =new Intent(BeServerGuideSecondActivity.this,MainActivity.class);
                    startActivity(mainintent);
                } else {
                    ToastUtil.showMessage(((CommonResult) response).info);
                }

            }
        });
        sendJsonRequest(saleuserAddMerchantRequest);
    }

    //接收广播回调
    @Override
    protected void handleReceiver(Context context, Intent intent) {

        if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            return;
        }
        Log.d(getClass().getName(), "[onReceive] action:" + intent.getAction());
        if(Constants.BROCAST_BE_SERVER_MANAGER_SUCCESS.equals(intent.getAction())){
            services=intent.getStringExtra("servicesIds");
            merchantId=intent.getIntExtra("merchantId",merchantId);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==INFOCODE&&resultCode==RESULT_OK){
            ToastUtil.showMessage(data.getStringExtra("headString"));
            headString=data.getStringExtra("headString");
            username=data.getStringExtra("username");
            password=data.getStringExtra("password");
        }if(requestCode==POSTIONCODE&&resultCode==RESULT_OK){
            postionName=data.getStringExtra("position_name");
        }
    }
}
