package com.zemult.yovollserver.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zemult.yovollserver.R;
import com.zemult.yovollserver.activity.mine.MyInfoSetActivity;
import com.zemult.yovollserver.activity.mine.MySettingActivity;
import com.zemult.yovollserver.app.BaseFragment;
import com.zemult.yovollserver.config.Constants;
import com.zemult.yovollserver.util.IntentUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2016/6/3.
 */
public class MineFragment extends BaseFragment {

    @Bind(R.id.ll_head_info)
    LinearLayout llHeadInfo;
    @Bind(R.id.iv_head)
    ImageView ivHead;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.rl_my_info)
    LinearLayout rlMyInfo;
    @Bind(R.id.tv_unsure_num)
    TextView tvUnsureNum;
    @Bind(R.id.ll_service_record)
    LinearLayout llServiceRecord;
    @Bind(R.id.iv_service_record)
    ImageView ivServiceRecord;
    @Bind(R.id.rl_my_order)
    RelativeLayout rlMyOrder;
    @Bind(R.id.tv_my_own)
    TextView tvMyOwn;
    @Bind(R.id.rl_my_own)
    RelativeLayout rlMyOwn;
    @Bind(R.id.tv_my_scheme)
    TextView tvMyScheme;
    @Bind(R.id.rl_my_scheme)
    RelativeLayout rlMyScheme;
    @Bind(R.id.tv_my_merchant)
    TextView tvMyMerchant;
    @Bind(R.id.rl_my_merchant)
    RelativeLayout rlMyMerchant;
    @Bind(R.id.tv_my_service)
    TextView tvMyService;
    @Bind(R.id.rl_my_service)
    RelativeLayout rlMyService;
    @Bind(R.id.tv_my_msg)
    TextView tvMyMsg;
    @Bind(R.id.rl_my_msg)
    RelativeLayout rlMyMsg;
    @Bind(R.id.tv_set)
    TextView tvSet;
    @Bind(R.id.rl_set)
    RelativeLayout rlSet;

    private boolean hasStarted = false;
    int state;

    int isSetPaypwd, isConfirm;
    double mymoney;
    String myname, head;
    int isSaleUser;

    private Context mContext;
    private Activity mActivity;
    // UserInfoOwnerRequest userInfoOwnerRequest;
    //User2SaleUserLoginRequest user2SaleUserLoginRequest;


    @Override
    public void onResume() {
        super.onResume();
        //get_user_info_owner_request();

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            //get_user_info_owner_request();
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
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerReceiver(new String[]{Constants.BROCAST_UPDATEMYINFO});
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.ll_head_info, R.id.rl_my_order, R.id.rl_my_own, R.id.rl_my_scheme, R.id.rl_my_merchant, R.id.rl_my_service, R.id.rl_my_msg, R.id.rl_set})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_head_info:
                IntentUtil.start_activity(mActivity, MyInfoSetActivity.class);
                break ;
            case R.id.rl_my_order:
                break;
            case R.id.rl_my_own:
                break;
            case R.id.rl_my_scheme:
                break;
            case R.id.rl_my_merchant:
                break;
            case R.id.rl_my_service:
                break;
            case R.id.rl_my_msg:
                break;
            case R.id.rl_set:
                IntentUtil.start_activity(mActivity, MySettingActivity.class);
                break;
        }
    }


    @Override
    protected void handleReceiver(Context context, Intent intent) {

        if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            return;
        }
        if (Constants.BROCAST_UPDATEMYINFO.equals(intent.getAction())) {
        }

        if (Constants.BROCAST_BE_SERVER_MANAGER_SUCCESS.equals(intent.getAction())) {
        }
    }


    @Override
    protected void lazyLoad() {

    }

}
