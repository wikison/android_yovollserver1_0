package com.zemult.yovollserver.activity.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.zemult.yovollserver.R;
import com.zemult.yovollserver.activity.AllServiceActivity;
import com.zemult.yovollserver.activity.BindMerchantActivity;
import com.zemult.yovollserver.adapter.HomeChildNew4KeyWordsAdapter;
import com.zemult.yovollserver.aip.MerchantFirstpageSearchListRequest;
import com.zemult.yovollserver.aip.UserCheckSaleUser1_2_2Request;
import com.zemult.yovollserver.app.BaseFragment;
import com.zemult.yovollserver.config.Constants;
import com.zemult.yovollserver.model.CommonResult;
import com.zemult.yovollserver.model.M_Merchant;
import com.zemult.yovollserver.model.apimodel.APIM_MerchantList;
import com.zemult.yovollserver.util.SlashHelper;
import com.zemult.yovollserver.util.ToastUtil;
import com.zemult.yovollserver.view.SmoothListView.SmoothListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zema.volley.network.ResponseListener;

/**
 * Created by wikison on 2016/6/15.
 * 搜索场景
 */
public class SearchMerchant4KeyWordsFragment extends BaseFragment implements SmoothListView.ISmoothListViewListener {

    public static final String TAG = SearchMerchant4KeyWordsFragment.class.getSimpleName();

    @Bind(R.id.smoothListView)
    SmoothListView smoothListView;
    @Bind(R.id.ll_no_bind)
    LinearLayout llNoBind;
    @Bind(R.id.tv_ruzhu)
    TextView tvRuzhu;

    MerchantFirstpageSearchListRequest request;
    UserCheckSaleUser1_2_2Request checkSaleUser1_2_2Request;

    private HomeChildNew4KeyWordsAdapter mAdapter; // 主页数据

    private Context mContext;
    private int page = 1;
    private String key;

    @Override
    protected void lazyLoad() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_child, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initView();
        initListener();
        showPd();
        merchant_firstpage_search_List(false);
    }

    private void initData() {
        key = getArguments().getString(Search4KeyWordsActivity.INTENT_KEY);
        mContext = getActivity();
    }

    private void initView() {
        mAdapter = new HomeChildNew4KeyWordsAdapter(mContext, new ArrayList<M_Merchant>());
        smoothListView.setAdapter(mAdapter);
    }

    private void initListener() {
        smoothListView.setRefreshEnable(true);
        smoothListView.setLoadMoreEnable(false);
        smoothListView.setSmoothListViewListener(this);
        smoothListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                M_Merchant merchant = mAdapter.getItem(position - 1);
                Intent it = new Intent(mContext, AllServiceActivity.class);
                it.putExtra("merchant", merchant);
                startActivity(it);
            }
        });

    }


    /**
     * 判断用户是否可以申请商家的服务管家
     */
    private void user_check_saleuser_1_2_2(final M_Merchant merchant) {
        showPd();
        if (checkSaleUser1_2_2Request != null) {
            checkSaleUser1_2_2Request.cancel();
        }
        UserCheckSaleUser1_2_2Request.Input input = new UserCheckSaleUser1_2_2Request.Input();

        input.userId = SlashHelper.userManager().getUserId();
        input.merchantId = merchant.merchantId;
        input.convertJson();
        checkSaleUser1_2_2Request = new UserCheckSaleUser1_2_2Request(input, new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dismissPd();
            }

            @Override
            public void onResponse(Object response) {
                if (((CommonResult) response).status == 1) {
                    Intent it = new Intent(mContext, BindMerchantActivity.class);
                    //it.putExtra(MerchantDetailActivity.MERCHANT_ID, merchant.merchantId);
                    startActivity(it);
                } else {
                    ToastUtil.showMessage(((CommonResult) response).info);
                }
                dismissPd();
            }
        });
        sendJsonRequest(checkSaleUser1_2_2Request);
    }

    public void search(String key) {
        if (key.equals(this.key))
            return;

        showPd();
        this.key = key;
        merchant_firstpage_search_List(false);
    }

    //搜索方案列表
    public void merchant_firstpage_search_List(final boolean isLoadMore) {
        if (request != null) {
            request.cancel();
        }

        MerchantFirstpageSearchListRequest.Input input = new MerchantFirstpageSearchListRequest.Input();
        input.operateUserId = SlashHelper.userManager().getUserId();
        input.name = key;
        input.city = Constants.CITYID;
        input.center = Constants.CENTER;
        input.page = isLoadMore ? ++page : (page = 1);
        input.rows = Constants.ROWS;

        input.convertJson();
        request = new MerchantFirstpageSearchListRequest(input, new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                smoothListView.stopLoadMore();
                smoothListView.stopRefresh();
                dismissPd();
            }

            @Override
            public void onResponse(Object response) {
                if (((APIM_MerchantList) response).status == 1) {
                    fillAdapter(((APIM_MerchantList) response).merchantList,
                            ((APIM_MerchantList) response).maxpage,
                            isLoadMore);

                } else {
                    ToastUtil.showMessage(((APIM_MerchantList) response).info);
                }
                smoothListView.stopLoadMore();
                smoothListView.stopRefresh();
                dismissPd();
            }
        });
        sendJsonRequest(request);
    }

    // 填充数据
    private void fillAdapter(List<M_Merchant> list, int maxpage, boolean isLoadMore) {
        if (list == null || list.size() == 0) {
            smoothListView.setVisibility(View.GONE);
            llNoBind.setVisibility(View.VISIBLE);

        } else {
            smoothListView.setVisibility(View.VISIBLE);
            llNoBind.setVisibility(View.GONE);

            smoothListView.setLoadMoreEnable(page < maxpage);
            mAdapter.setData(list, isLoadMore);

            if (!isLoadMore)
                smoothListView.setSelection(0);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        merchant_firstpage_search_List(false);
    }

    @Override
    public void onLoadMore() {
        merchant_firstpage_search_List(true);
    }

    public void setIndustryIdAndSearch(int industryId) {
        showPd();
        merchant_firstpage_search_List(false);
    }

    @OnClick(R.id.tv_ruzhu)
    public void onClick() {
        //startActivity(new Intent(mContext, MerchantEnter2Activity.class));
    }
}
