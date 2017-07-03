package com.zemult.yovollserver.activity;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.flyco.roundview.RoundLinearLayout;
import com.zemult.yovollserver.R;
import com.zemult.yovollserver.aip.Merchant2ProductListRequest;
import com.zemult.yovollserver.app.BaseActivity;

import butterknife.Bind;

/**
 * Created by Wikison on 2017/7/3.
 * 产品推荐
 */

public class ProductRecommendActivity extends BaseActivity {
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
    @Bind(R.id.lv)
    ListView lv;
    @Bind(R.id.tv_select)
    TextView tvSelect;
    @Bind(R.id.rll_sure)
    RoundLinearLayout rllSure;

    Merchant2ProductListRequest merchant2ProductListRequest;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_product_recommend);
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
}
