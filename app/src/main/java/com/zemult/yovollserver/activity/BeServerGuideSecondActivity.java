package com.zemult.yovollserver.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.roundview.RoundLinearLayout;
import com.flyco.roundview.RoundTextView;
import com.zemult.yovollserver.R;
import com.zemult.yovollserver.app.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

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

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_be_server_2);
    }

    @Override
    public void init() {
        initData();
        initView();

    }

    private void initData() {

    }

    private void initView() {
        lhTvTitle.setText("成为服务管家");
    }

    @OnClick({R.id.rll_bind_merchant, R.id.rll_position, R.id.rll_user_info, R.id.rll_notify, R.id.rll_contact, R.id.tv_protocol, R.id.rtv_finish})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.rll_bind_merchant:
                break;
            case R.id.rll_position:
                break;
            case R.id.rll_user_info:
                break;
            case R.id.rll_notify:
                break;
            case R.id.rll_contact:
                break;
            case R.id.tv_protocol:
                break;
            case R.id.rtv_finish:
                break;
        }
    }

}
