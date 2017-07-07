package com.zemult.yovollserver.activity;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.zemult.yovollserver.R;
import com.zemult.yovollserver.adapter.CommonAdapter;
import com.zemult.yovollserver.adapter.CommonViewHolder;
import com.zemult.yovollserver.aip.User2SaleUserFanListRequest;
import com.zemult.yovollserver.app.BaseActivity;
import com.zemult.yovollserver.config.Constants;
import com.zemult.yovollserver.model.M_Userinfo;
import com.zemult.yovollserver.model.apimodel.APIM_UserList;
import com.zemult.yovollserver.util.StringUtils;
import com.zemult.yovollserver.util.ToastUtil;
import com.zemult.yovollserver.view.SearchView;
import com.zemult.yovollserver.view.SmoothListView.SmoothListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import zema.volley.network.ResponseListener;

/**
 * Created by Wikison on 2017/7/4.
 */

public class MyCustomerActivity extends BaseActivity implements SmoothListView.ISmoothListViewListener {
    @Bind(R.id.lh_btn_back)
    Button lhBtnBack;
    @Bind(R.id.ll_back)
    LinearLayout llBack;
    @Bind(R.id.lh_tv_title)
    TextView lhTvTitle;
    @Bind(R.id.search_view)
    SearchView searchView;
    @Bind(R.id.rl_may_know)
    RelativeLayout rlMayKnow;
    @Bind(R.id.tv_num)
    TextView tvNum;
    @Bind(R.id.iv_unread)
    ImageView ivUnread;
    @Bind(R.id.smooth_list_view)
    SmoothListView smoothListView;
    @Bind(R.id.iv)
    ImageView iv;
    @Bind(R.id.tv_nodata)
    TextView tvNodata;
    @Bind(R.id.rl_no_data)
    RelativeLayout rlNoData;

    User2SaleUserFanListRequest userFansListRequest;
    List<M_Userinfo> mDatas = new ArrayList<M_Userinfo>();

    CommonAdapter commonAdapter;
    Context mContext;
    Activity mActivity;
    int page = 1;
    String name;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_my_customer);
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

        smoothListView.setRefreshEnable(true);
        smoothListView.setLoadMoreEnable(false);
        smoothListView.setSmoothListViewListener(this);
        smoothListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        getCustomerList(true);

    }

    private void initView() {
        lhTvTitle.setText("我的客户");

        searchView.setTvCancelVisible(View.GONE);
        searchView.setBgColor(getResources().getColor(R.color.divider_c1));

    }

    private void initListener() {
        searchView.setSearchViewListener(new SearchView.SearchViewListener() {
            @Override
            public void onSearch(String text) {
                name = text;
                onRefresh();
            }

            @Override
            public void onClear() {
                name = "";
                onRefresh();
            }
        });
    }

    private void getCustomerList(boolean firstLoad) {
        if (userFansListRequest != null) {
            userFansListRequest.cancel();
        }
        User2SaleUserFanListRequest.Input input = new User2SaleUserFanListRequest.Input();
        //TODO input.saleUserId = userId;
        input.name = name;
        if (firstLoad) {
            input.page = 1;
        } else {
            input.page = page;
        }
        input.rows = Constants.ROWS;     //每页显示的行数
        input.convertJson();
        userFansListRequest = new User2SaleUserFanListRequest(input, new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dismissPd();
                smoothListView.stopRefresh();
                smoothListView.stopLoadMore();
            }

            @Override
            public void onResponse(Object response) {
                dismissPd();
                if (((APIM_UserList) response).status == 1) {
                    if (page == 1) {
                        mDatas = ((APIM_UserList) response).userList;
                        if (mDatas == null || mDatas.isEmpty()) {
                            smoothListView.setVisibility(View.GONE);
                            rlNoData.setVisibility(View.VISIBLE);
                        } else {
                            smoothListView.setVisibility(View.VISIBLE);
                            rlNoData.setVisibility(View.GONE);
                            if (mDatas != null && !mDatas.isEmpty()) {
                                smoothListView.setAdapter(commonAdapter = new CommonAdapter<M_Userinfo>(mActivity, R.layout.item_my_customer, mDatas) {
                                    @Override
                                    public void convert(CommonViewHolder holder, M_Userinfo mCustomer, final int position) {
                                        if (!StringUtils.isBlank(mCustomer.head)) {
                                            holder.setCircleImage(R.id.iv_head, mCustomer.head);
                                        }
                                        holder.setText(R.id.tv_name, mCustomer.name);
                                        holder.setText(R.id.tv_merchant_name, mCustomer.merchantName + "  " + mCustomer.getPosition());

                                        holder.setOnclickListener(R.id.ll_root, new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                            }
                                        });
                                    }

                                });
                            }

                        }
                    } else {
                        mDatas.addAll(((APIM_UserList) response).userList);
                        commonAdapter.notifyDataSetChanged();
                    }

                    if (((APIM_UserList) response).maxpage <= page) {
                        smoothListView.setLoadMoreEnable(false);
                    } else {
                        smoothListView.setLoadMoreEnable(true);
                        page++;
                    }

                } else {
                    ToastUtil.showMessage(((APIM_UserList) response).info);
                }
                smoothListView.stopRefresh();
                smoothListView.stopLoadMore();

            }
        });

        sendJsonRequest(userFansListRequest);
    }

    @OnClick({R.id.lh_btn_back, R.id.ll_back, R.id.rl_may_know})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lh_btn_back:
            case R.id.ll_back:
                finish();
                break;
            case R.id.rl_may_know:
                break;
        }
    }

    @Override
    public void onRefresh() {
        showPd();
        getCustomerList(true);
    }

    @Override
    public void onLoadMore() {
        getCustomerList(false);

    }
}
