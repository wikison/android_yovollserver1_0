package com.zemult.yovollserver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.zemult.yovollserver.R;
import com.zemult.yovollserver.aip.common.UserLoginRequest;
import com.zemult.yovollserver.app.BaseActivity;
import com.zemult.yovollserver.model.apimodel.APIM_UserLogin;
import com.zemult.yovollserver.util.DigestUtils;
import com.zemult.yovollserver.util.SlashHelper;
import com.zemult.yovollserver.util.StringMatchUtils;
import com.zemult.yovollserver.util.StringUtils;
import com.zemult.yovollserver.util.ToastUtil;
import com.zemult.yovollserver.util.UserManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zema.volley.network.ResponseListener;

/**
 * Created by Wikison on 2017/6/22.
 * 登录
 */

public class LoginActivity extends BaseActivity {
    @Bind(R.id.lh_btn_back)
    Button lhBtnBack;
    @Bind(R.id.ll_back)
    LinearLayout llBack;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.tv_forget)
    TextView tvForget;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.tv_be_server)
    TextView tvBeServer;


    String strUserName, strPwd;
    UserLoginRequest user_login_request;


    @Override
    public void setContentView() {
        setContentView(R.layout.login_activity);
    }

    @Override
    public void init() {

    }



    private void login() {
        strUserName = etPhone.getText().toString();
        strPwd = etPassword.getText().toString();
        if (StringUtils.isBlank(strUserName)) {
            etPhone.setError("手机号不能为空");
            return;
        }
        if (StringUtils.isBlank(strPwd)) {
            etPassword.setError("密码不能为空");
            return;
        }
        if (!StringMatchUtils.isMobileNO(strUserName)) {
            ToastUtil.showMessage("请输入正确的手机号码");
            return;
        }
        if (!StringUtils.isBlank(strUserName) && !StringUtils.isBlank(strPwd))
            get_user_login_request();
    }


    //用户登录
    private void get_user_login_request() {
        loadingDialog.show();
        if (user_login_request != null) {
            user_login_request.cancel();
        }
        UserLoginRequest.Input input = new UserLoginRequest.Input();
        input.account = strUserName;
        input.password = DigestUtils.md5(strPwd).toUpperCase();
        input.device_token = SlashHelper.deviceManager().getUmengDeviceToken();
        input.convertJosn();

        user_login_request = new UserLoginRequest(input, new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingDialog.dismiss();
            }

            @Override
            public void onResponse(final Object response) {
                if (((APIM_UserLogin) response).status == 1) {
                    UserManager.instance().saveUserinfo(((APIM_UserLogin) response).userInfo);
                    Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    ToastUtil.showMessage(((APIM_UserLogin) response).info);
                }
                loadingDialog.dismiss();
            }
        });
        sendJsonRequest(user_login_request);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.lh_btn_back, R.id.ll_back, R.id.btn_login, R.id.tv_be_server,R.id.tv_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lh_btn_back:
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_forget:
                Intent forgetintent=new Intent(LoginActivity.this,FindPasswordActivity.class);
                startActivity(forgetintent);
                break;
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_be_server:
                Intent intent=new Intent(LoginActivity.this,BeServerGuideFirstActivity.class);
                startActivity(intent);
                break;
        }
    }
}
