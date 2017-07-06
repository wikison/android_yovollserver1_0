package com.zemult.yovollserver.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.zemult.yovollserver.R;
import com.zemult.yovollserver.activity.search.SearchMerchantSimpleFragment;
import com.zemult.yovollserver.aip.SaleuserMerchantHotListRequest;
import com.zemult.yovollserver.aip.common.CommonPositionRequest;
import com.zemult.yovollserver.app.BaseActivity;
import com.zemult.yovollserver.config.Constants;
import com.zemult.yovollserver.model.apimodel.APIM_PresentList;
import com.zemult.yovollserver.util.ToastUtil;
import com.zemult.yovollserver.view.SearchView;

import butterknife.Bind;
import butterknife.OnClick;
import zema.volley.network.ResponseListener;

/**
 * Created by Wikison on 2017/6/29.
 * 绑定商户
 */

public class BindMerchantActivity extends BaseActivity {

    public static final String INTENT_KEY = "key";
    @Bind(R.id.a_seach_searchview)
    SearchView searchview;
    @Bind(R.id.fl)
    FrameLayout fl;
    @Bind(R.id.lh_tv_title)
    TextView lhTvTitle;
    @Bind(R.id.ll_head)
    LinearLayout llHead;

    private Context mContext;
    private Activity mActivity;
    private SearchMerchantSimpleFragment merchantFragment;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_bind_merchant);
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
        searchview.setStrHint("");

        merchantFragment = new SearchMerchantSimpleFragment();
        Bundle bundle = new Bundle();
        llHead.setVisibility(View.VISIBLE);
        lhTvTitle.setText("绑定商户");
        searchview.setStrHint("搜索商户名称");
        searchview.setTvCancelVisible(View.GONE);
        searchview.setBgColor(0xffc1c1c1);

        bundle.putString(BindMerchantActivity.INTENT_KEY, "");
        merchantFragment.setArguments(bundle);

        registerReceiver(new String[]{ Constants.BROCAST_BE_SERVER_MANAGER_SUCCESS});
    }

    private void initView() {
        // 开启Fragment事务
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl, merchantFragment);
        transaction.commit();
    }

    private void initListener() {
        searchview.setSearchViewListener(new SearchView.SearchViewListener() {
            @Override
            public void onSearch(String text) {
                merchantFragment.search(text);

            }

            @Override
            public void onClear() {

            }
        });
    }





    //接收广播回调
    @Override
    protected void handleReceiver(Context context, Intent intent) {

        if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            return;
        }
        Log.d(getClass().getName(), "[onReceive] action:" + intent.getAction());
        if(Constants.BROCAST_BE_SERVER_MANAGER_SUCCESS.equals(intent.getAction())){
            finish();
        }
    }

    @OnClick({R.id.lh_btn_back, R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lh_btn_back:
            case R.id.ll_back:
                onBackPressed();
                break;
        }
    }
}
