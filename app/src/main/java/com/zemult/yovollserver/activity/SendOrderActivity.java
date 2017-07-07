package com.zemult.yovollserver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.flyco.roundview.RoundLinearLayout;
import com.flyco.roundview.RoundRelativeLayout;
import com.zemult.yovollserver.R;
import com.zemult.yovollserver.app.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Wikison on 2017/6/30.
 * 发订单
 */

public class SendOrderActivity extends BaseActivity {


    @Bind(R.id.et_order_info)
    EditText etOrderInfo;
    @Bind(R.id.et_order_earnest)
    EditText etOrderEarnest;
    @Bind(R.id.rll_recommend)
    RoundRelativeLayout rllRecommend;
    @Bind(R.id.rll_product_recommend)
    RoundRelativeLayout rllProductRecommend;
    @Bind(R.id.rll_send_msg)
    RoundLinearLayout rllSendMsg;
    @Bind(R.id.rll_send_wx)
    RoundLinearLayout rllSendWx;
    @Bind(R.id.lh_btn_back)
    Button lhBtnBack;
    @Bind(R.id.ll_back)
    LinearLayout llBack;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_send_order);
    }

    @Override
    public void init() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rll_recommend, R.id.rll_product_recommend, R.id.rll_send_msg, R.id.rll_send_wx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lh_btn_back:
            case R.id.ll_back:
                finish();
                break;
            case R.id.rll_recommend:
                Intent planintent=new Intent(SendOrderActivity.this,PlanRecommendActivity.class);
                startActivity(planintent);
                break;
            case R.id.rll_product_recommend:
                Intent prointent=new Intent(SendOrderActivity.this,ProductRecommendActivity.class);
                startActivity(prointent);
                break;
            case R.id.rll_send_msg:
                break;
            case R.id.rll_send_wx:
                break;
        }
    }

}
