package com.zemult.yovollserver.activity.mine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zemult.yovollserver.R;
import com.zemult.yovollserver.app.BaseActivity;
import com.zemult.yovollserver.config.Constants;
import com.zemult.yovollserver.util.DensityUtil;
import com.zemult.yovollserver.util.QrImageUtil;
import com.zemult.yovollserver.util.SlashHelper;
import com.zemult.yovollserver.util.StringUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by wikison on 2016/6/21.
 */
public class MyQrActivity extends BaseActivity {

    @Bind(R.id.money_tv)
    TextView moneyTv;
    @Bind(R.id.set_btn)
    Button setBtn;
    @Bind(R.id.lh_btn_back)
    Button lhBtnBack;
    @Bind(R.id.ll_back)
    LinearLayout llBack;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.ll_right)
    LinearLayout llRight;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.lh_tv_title)
    TextView lhTvTitle;
    @Bind(R.id.lh_btn_right)
    Button lhBtnRight;
    @Bind(R.id.lh_btn_rightiamge)
    Button lhBtnRightiamge;
    @Bind(R.id.iv_head)
    ImageView ivHead;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_level)
    TextView tvLevel;
    @Bind(R.id.iv_merchant_head)
    ImageView ivMerchantHead;
    @Bind(R.id.tv_merchant_name)
    TextView tvMerchantName;
    @Bind(R.id.ll_merchant_head)
    LinearLayout llMerchantHead;
    @Bind(R.id.iv_qr)
    ImageView ivQr;
    @Bind(R.id.tv_hint)
    TextView tvHint;
    @Bind(R.id.view)
    View view;
    int userSaleId, merchantId;

    String merchantHead, merchantName;
    Bitmap bitmap;
    String strFrom = "";
    String qrInfo;
    String price = "";

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_my_qr);
    }

    @Override
    public void init() {
        initData();
        initView();

    }


    private void initData() {
        strFrom = getIntent().getStringExtra("from");
        userSaleId = getIntent().getIntExtra("userSaleId", 0);
        merchantId = getIntent().getIntExtra("merchantId", 0);
        merchantHead = getIntent().getStringExtra("merchantHead");
        merchantName = getIntent().getStringExtra("merchantName");
    }

    private void initView() {
        lhBtnBack.setVisibility(View.VISIBLE);
        lhTvTitle.setVisibility(View.VISIBLE);
        if(!StringUtils.isBlank(SlashHelper.userManager().getUserinfo().getHead()))
            imageManager.loadCircleHead(SlashHelper.userManager().getUserinfo().getHead(), ivHead);

        tvName.setText(SlashHelper.userManager().getUserinfo().getName());
        if ("MyInfoSet".equals(strFrom)) {
            lhTvTitle.setText("我的二维码");
            qrInfo = "userId=" + SlashHelper.userManager().getUserId();
            bitmap = QrImageUtil.createQRImage(Constants.QR_USER_PREFIX + qrInfo, DensityUtil.dip2px(this, 240),
                    DensityUtil.dip2px(this, 240));
            tvHint.setVisibility(View.VISIBLE);
            view.setVisibility(View.VISIBLE);
        }
        if (bitmap != null)
            ivQr.setImageBitmap(bitmap);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == RESULT_OK) {
            price = data.getExtras().getString("result");
            moneyTv.setText("￥ " + price);
            qrInfo = "merchantId=" + merchantId + "&userId=" + userSaleId + "&price=" + price;
            bitmap = QrImageUtil.createQRImage(Constants.QR_PAY_PREFIX + qrInfo, DensityUtil.dip2px(this, 240),
                    DensityUtil.dip2px(this, 240));
            if (bitmap != null)
                ivQr.setImageBitmap(bitmap);
            setBtn.setText("清除金额");

        }
    }

}
