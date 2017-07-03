package com.zemult.yovollserver.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.roundview.RoundTextView;
import com.zemult.yovollserver.R;
import com.zemult.yovollserver.app.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Wikison on 2017/6/26.
 * 成为服务管家第一步
 */

public class BeServerGuideFirstActivity extends BaseActivity {
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
    @Bind(R.id.tv_text)
    TextView tvText;
    @Bind(R.id.rtv_join_now)
    RoundTextView rtvJoinNow;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_be_server_1);
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

    @OnClick({R.id.rtv_join_now})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rtv_join_now:
                Intent intent=new Intent(BeServerGuideFirstActivity.this,BeServerGuideSecondActivity.class);
                startActivity(intent);
                break;
        }
    }

}
