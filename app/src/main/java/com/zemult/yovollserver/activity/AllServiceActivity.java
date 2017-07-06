package com.zemult.yovollserver.activity;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.flyco.roundview.RoundTextView;
import com.zemult.yovollserver.R;
import com.zemult.yovollserver.adapter.ServiceListAdapter;
import com.zemult.yovollserver.aip.common.CommonMerchantServiceListRequest;
import com.zemult.yovollserver.app.BaseActivity;
import com.zemult.yovollserver.model.M_Merchant;
import com.zemult.yovollserver.model.M_Service;
import com.zemult.yovollserver.model.apimodel.APIM_ServiceList;
import com.zemult.yovollserver.util.SlashHelper;
import com.zemult.yovollserver.util.ToastUtil;
import com.zemult.yovollserver.view.FixedListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import zema.volley.network.ResponseListener;

/**
 * Created by Wikison on 2017/7/5.
 */

public class AllServiceActivity extends BaseActivity {
    @Bind(R.id.lh_btn_back)
    Button lhBtnBack;
    @Bind(R.id.ll_back)
    LinearLayout llBack;
    @Bind(R.id.lh_tv_title)
    TextView lhTvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.flv_list)
    FixedListView flvList;

    private Context mContext;
    private Activity mActivity;

    CommonMerchantServiceListRequest commonMerchantServiceListRequest;
    List<M_Service> serviceList = new ArrayList<M_Service>();
    ArrayList<Integer> selectedIds = new ArrayList<Integer>();
    ServiceListAdapter serviceListAdapter;
    M_Merchant merchant;
    int maxIds;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_all_service);
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

        //TODO need M_Merchant or (M_Merchant.merchantId && M_Merchant.reviewstatus)
        merchant = (M_Merchant) getIntent().getSerializableExtra("merchant");
        if (merchant == null) {
            ToastUtil.showMessage("请先选择商户");
            this.finish();
        }
        maxIds = (merchant.reviewstatus == 2 ? Integer.MAX_VALUE : 1);
        getCommonMerchantServiceList();
    }

    private void initView() {
        lhTvTitle.setText("选择服务项");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("保存");

        serviceListAdapter = new ServiceListAdapter(this, serviceList);
        flvList.setAdapter(serviceListAdapter);
    }

    private void initListener() {

        serviceListAdapter.setOnTextTaskClickListener(new ServiceListAdapter.ItemServiceClickListener() {
            @Override
            public void onItemClick(View v, M_Service entity) {
                RoundTextView rtvName = (RoundTextView) v;
                if (!v.isSelected() && selectedIds.size() + 1 > maxIds) {
                    ToastUtil.showMessage("最多选择" + maxIds + "项");
                    return;
                }
                if (selectedIds.contains(entity.serviceId)) {
                    selectedIds.remove(entity.serviceId);
                    rtvName.getDelegate().setBackgroundColor(0xeeeeee);
                    rtvName.setTextColor(0x666666);
                } else {
                    selectedIds.add(entity.serviceId);
                    rtvName.getDelegate().setBackgroundColor(0xb88e42);
                    rtvName.setTextColor(0xffffff);
                }
                v.setSelected(selectedIds.contains(entity.serviceId));
            }
        });
    }

    private void getCommonMerchantServiceList() {
        if (commonMerchantServiceListRequest != null) {
            commonMerchantServiceListRequest.cancel();
        }
        CommonMerchantServiceListRequest.Input input = new CommonMerchantServiceListRequest.Input();
        input.operateUserId = SlashHelper.userManager().getUserId();
        input.merchantId = merchant.merchantId;

        input.convertJson();

        commonMerchantServiceListRequest = new CommonMerchantServiceListRequest(input, new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }

            @Override
            public void onResponse(Object response) {
                if (((APIM_ServiceList) response).status == 1) {
                    fillAdapter(((APIM_ServiceList) response).serviceList);
                } else {
                    ToastUtil.showMessage(((APIM_ServiceList) response).info);
                }

            }
        });
        sendJsonRequest(commonMerchantServiceListRequest);
    }


    /**
     * 设置数据
     */
    private void fillAdapter(final List<M_Service> list) {

        if (list == null || list.size() == 0) {
        } else {
            serviceListAdapter.setData(list);
        }
    }

    @OnClick({R.id.lh_btn_back, R.id.ll_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lh_btn_back:
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_right:
                //TODO Callback
                this.finish();
                break;
        }
    }
}
