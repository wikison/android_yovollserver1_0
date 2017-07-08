package com.zemult.yovollserver.activity.mine;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.zemult.yovollserver.R;
import com.zemult.yovollserver.aip.UserEditinfoRequest;
import com.zemult.yovollserver.app.BaseActivity;
import com.zemult.yovollserver.model.CommonResult;
import com.zemult.yovollserver.util.EditFilter;
import com.zemult.yovollserver.util.SlashHelper;
import com.zemult.yovollserver.util.StringUtils;
import com.zemult.yovollserver.util.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;
import zema.volley.network.ResponseListener;

public class NicknameActivity extends BaseActivity {

    @Bind(R.id.lh_btn_back)
    Button lhBtnBack;
    @Bind(R.id.lh_tv_title)
    TextView lhTvTitle;
    @Bind(R.id.lh_btn_right)
    Button lhBtnRight;
    @Bind(R.id.niname_et)
    EditText ninameEt;
    @Bind(R.id.ll_back)
    LinearLayout llBack;

    UserEditinfoRequest userEditinfoRequest;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_nickname);

    }

    @Override
    public void init() {
        initData();
        initView();
        initListener();
    }

    private void initData() {

    }

    private void initView() {
        lhTvTitle.setText("名字");
        lhBtnRight.setVisibility(View.VISIBLE);

        lhBtnRight.setText("保存");
        lhBtnRight.setTextSize(16);
        lhBtnRight.setTextColor(getResources().getColor(R.color.white));
        lhBtnRight.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        ninameEt.setText(SlashHelper.userManager().getUserinfo().getName());
    }

    private void initListener() {
        ninameEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ninameEt.setText("");
                    EditFilter.WordFilter(ninameEt, 6);
                }
            }
        });
    }

    //修改用户资料信息
    private void user_editinfo() {
        showPd();
        if (userEditinfoRequest != null) {
            userEditinfoRequest.cancel();
        }
        UserEditinfoRequest.Input input = new UserEditinfoRequest.Input();
        if (SlashHelper.userManager().getUserinfo() != null) {
            input.userId = SlashHelper.userManager().getUserId();
            input.name = ninameEt.getText().toString();
            input.convertJson();
        }

        userEditinfoRequest = new UserEditinfoRequest(input, new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dismissPd();
            }

            @Override
            public void onResponse(Object response) {
                if (((CommonResult) response).status == 1) {
                    SlashHelper.userManager().getUserinfo().setName(ninameEt.getText().toString());
                    SlashHelper.userManager().saveUserinfo(SlashHelper.userManager().getUserinfo());
                    Intent intent = new Intent();
                    //把返回数据存入Intent
                    intent.putExtra("result", ninameEt.getText().toString());
                    //设置返回数据
                    NicknameActivity.this.setResult(RESULT_OK, intent);
//                    Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                    NicknameActivity.this.finish();
                } else {
                    ToastUtil.showMessage(((CommonResult) response).info);
                }
                dismissPd();
            }
        });
        sendJsonRequest(userEditinfoRequest);
    }


    @OnClick({R.id.ll_back, R.id.lh_btn_right, R.id.lh_btn_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:  //返回按钮
            case R.id.lh_btn_back:
                NicknameActivity.this.setResult(RESULT_OK);
                NicknameActivity.this.finish();

                break;
            case R.id.lh_btn_right:   //点击保存按钮

                if (StringUtils.isBlank(ninameEt.getText().toString())) {
                    ToastUtil.showMessage("请输入您的昵称");
                } else {
                    user_editinfo();

                }
                break;
        }
    }

}
