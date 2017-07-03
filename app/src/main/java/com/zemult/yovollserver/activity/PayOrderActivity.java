package com.zemult.yovollserver.activity;

import android.content.Intent;
import android.view.View;

import com.zemult.yovollserver.R;
import com.zemult.yovollserver.app.BaseActivity;

import butterknife.OnClick;

/**
 * Created by Wikison on 2017/7/3.
 * 买单收款
 */

public class PayOrderActivity extends BaseActivity {
    private static int REQUEST_PLAN_ID = 0x001;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_pay_order);
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

    }

    private void initListener() {

    }

    @OnClick({R.id.lh_btn_back, R.id.ll_back, R.id.rll_product_recommend, R.id.rll_generate_pay_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lh_btn_back:
            case R.id.ll_back:
                finish();
                break;
            case R.id.rll_product_recommend:
                Intent intent = new Intent(PayOrderActivity.this, ProductRecommendActivity.class);
                //TODO 传入商家id
                intent.putExtra("merchantId", 0);
                startActivityForResult(intent, REQUEST_PLAN_ID);
                break;
            case R.id.rll_generate_pay_code:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_PLAN_ID) {

            }
        }
    }
}
