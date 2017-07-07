package com.zemult.yovollserver.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.flyco.roundview.RoundLinearLayout;
import com.flyco.roundview.RoundTextView;
import com.zemult.yovollserver.R;
import com.zemult.yovollserver.activity.MyCustomerActivity;
import com.zemult.yovollserver.aip.CommonGetadvertListRequest;
import com.zemult.yovollserver.app.BaseFragment;
import com.zemult.yovollserver.config.Constants;
import com.zemult.yovollserver.model.M_Ad;
import com.zemult.yovollserver.model.apimodel.APIM_CommonGetadvertList;
import com.zemult.yovollserver.util.IntentUtil;
import com.zemult.yovollserver.util.ToastUtil;
import com.zemult.yovollserver.view.FixedListView;
import com.zemult.yovollserver.view.HeaderAdView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.grantland.widget.AutofitTextView;
import zema.volley.network.ResponseListener;

/**
 * Created by Wikison on 2017/6/23.
 */
public class HomeFragment extends BaseFragment {

    @Bind(R.id.rll_ad_container)
    LinearLayout rllAdContainer;
    @Bind(R.id.tv_focus_num)
    AutofitTextView tvFocusNum;
    @Bind(R.id.tv_ren)
    TextView tvRen;
    @Bind(R.id.rll_focus)
    RoundLinearLayout rllFocus;
    @Bind(R.id.tv_reward_num)
    AutofitTextView tvRewardNum;
    @Bind(R.id.tv_ci)
    TextView tvCi;
    @Bind(R.id.rll_reward)
    RoundLinearLayout rllReward;
    @Bind(R.id.tv_income)
    AutofitTextView tvIncome;
    @Bind(R.id.tv_text_income)
    TextView tvTextIncome;
    @Bind(R.id.rll_income)
    RoundLinearLayout rllIncome;
    @Bind(R.id.tv_order_num)
    AutofitTextView tvOrderNum;
    @Bind(R.id.tv_text_order)
    TextView tvTextOrder;
    @Bind(R.id.rll_order)
    RoundLinearLayout rllOrder;
    @Bind(R.id.tv_buy)
    AutofitTextView tvBuy;
    @Bind(R.id.tv_text_buy)
    TextView tvTextBuy;
    @Bind(R.id.rll_buy)
    RoundLinearLayout rllBuy;
    @Bind(R.id.tv_product_sale)
    AutofitTextView tvProductSale;
    @Bind(R.id.tv_text_product)
    TextView tvTextProduct;
    @Bind(R.id.rll_product)
    RoundLinearLayout rllProduct;
    @Bind(R.id.rll_customer_manage)
    RoundLinearLayout rllCustomerManage;
    @Bind(R.id.rll_service_scheme)
    RoundLinearLayout rllServiceScheme;
    @Bind(R.id.rtv_talk)
    RoundTextView rtvTalk;
    @Bind(R.id.iv_red_dot)
    ImageView ivRedDot;
    @Bind(R.id.lv)
    FixedListView lv;

    private Context mContext;
    private Activity mActivity;
    private HeaderAdView headerAdView; // 广告视图

    private CommonGetadvertListRequest commonGetadvertListRequest;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();

    }

    private void initData() {
        mContext = getActivity();
        mActivity = getActivity();

        getNetworkData();

    }

    private void getNetworkData() {
        commonGetAdvertList();
    }

    //获取  广告列表
    private void commonGetAdvertList() {
        if (commonGetadvertListRequest != null) {
            commonGetadvertListRequest.cancel();
        }
        CommonGetadvertListRequest.Input input = new CommonGetadvertListRequest.Input();
        input.page = 3;//页面编号(-1:表示全部;0:app开启页1:首页广告位2:我的斜杠3:我是商家)

        input.convertJosn();
        commonGetadvertListRequest = new CommonGetadvertListRequest(input, new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }

            @Override
            public void onResponse(Object response) {
                if (((APIM_CommonGetadvertList) response).status == 1) {
                    setAd(((APIM_CommonGetadvertList) response).advertList);
                } else {
                    ToastUtil.showMessage(((APIM_CommonGetadvertList) response).info);
                }
            }
        });
        sendJsonRequest(commonGetadvertListRequest);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setAd(List<M_Ad> advertList) {
        headerAdView = new HeaderAdView(mActivity, rllAdContainer.getHeight());
        headerAdView.fillView(advertList, rllAdContainer);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.rll_focus, R.id.rll_reward, R.id.rll_income, R.id.rll_order, R.id.rll_buy,
            R.id.rll_product, R.id.rll_customer_manage, R.id.rll_service_scheme, R.id.rtv_talk})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.rll_focus:
                break;
            case R.id.rll_reward:
                break;
            case R.id.rll_income:
                break;
            case R.id.rll_order:
                break;
            case R.id.rll_buy:
                break;
            case R.id.rll_product:
                break;
            case R.id.rll_customer_manage:
                IntentUtil.intStart_activity(mActivity, MyCustomerActivity.class);
                break;
            case R.id.rll_service_scheme:
                break;
            case R.id.rtv_talk:
                break;
        }
    }


    @Override
    protected void handleReceiver(Context context, Intent intent) {

        if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            return;
        }
        Log.d(getClass().getName(), "[onReceive] action:" + intent.getAction());
        if (Constants.BROCAST_UPDATEMYINFO.equals(intent.getAction())) {
            //get_user_info_owner_request();
        }
    }


    @Override
    protected void lazyLoad() {

    }

}
