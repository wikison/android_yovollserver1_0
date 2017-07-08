package com.zemult.yovollserver.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.zemult.yovollserver.R;
import com.zemult.yovollserver.aip.common.UserLoginRequest;
import com.zemult.yovollserver.app.BaseActivity;
import com.zemult.yovollserver.model.M_Userinfo;
import com.zemult.yovollserver.model.apimodel.APIM_UserLogin;
import com.zemult.yovollserver.util.DigestUtils;
import com.zemult.yovollserver.util.SlashHelper;
import com.zemult.yovollserver.util.StringMatchUtils;
import com.zemult.yovollserver.util.StringUtils;
import com.zemult.yovollserver.util.ToastUtil;
import com.zemult.yovollserver.util.UserManager;

import butterknife.Bind;
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
    @Bind(R.id.lh_tv_title)
    TextView lhTvTitle;
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

    UserLoginRequest user_login_request;

    private Context mContext;
    private Activity mActivity;

    String strUserName, strPwd;

    @Override
    public void setContentView() {
        setContentView(R.layout.login_activity);
    }

    @Override
    public void init() {
        initData();
        initView();
        initListener();
    }

    private void initData() {
        mContext = this;
        mActivity = this;
    }

    private void initView() {
        lhTvTitle.setText("登录");
        tvForget.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        etPhone.addTextChangedListener(watcher);
        etPassword.addTextChangedListener(watcher);
    }

    private void initListener() {

    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.toString().length() > 0) {
                if (etPhone.getText().toString().length() > 0
                        && etPassword.getText().toString().length() > 0) {
                    btnLogin.setEnabled(true);
                    btnLogin.setBackgroundResource(R.drawable.common_selector_btn);
                }

            } else {
                btnLogin.setEnabled(false);
                btnLogin.setBackgroundResource(R.drawable.next_bg_btn_select);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

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
        input.phone = strUserName;
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
                    M_Userinfo m_userinfo=new M_Userinfo();
                    m_userinfo.setUserName(strUserName);
                    m_userinfo.setPassword(strPwd);
                    m_userinfo.setUserId(((APIM_UserLogin) response).userId);
                    UserManager.instance().saveUserinfo(m_userinfo);
//                    UserManager.instance().saveUserinfo(((APIM_UserLogin) response).userInfo);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
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


    @OnClick({R.id.lh_btn_back, R.id.ll_back, R.id.btn_login, R.id.tv_be_server, R.id.tv_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lh_btn_back:
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_forget:
                Intent forgetintent = new Intent(LoginActivity.this, FindPasswordActivity.class);
                startActivity(forgetintent);
                break;
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_be_server:
                Intent intent = new Intent(LoginActivity.this, BeServerGuideFirstActivity.class);
                startActivity(intent);
                break;
        }
    }
}
