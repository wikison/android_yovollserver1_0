package com.zemult.yovollserver.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.flyco.roundview.RoundLinearLayout;
import com.flyco.roundview.RoundTextView;
import com.zemult.yovollserver.R;
import com.zemult.yovollserver.aip.common.CommonCheckcodeRequest;
import com.zemult.yovollserver.aip.common.CommonGetCodeRequest;
import com.zemult.yovollserver.app.BaseActivity;
import com.zemult.yovollserver.model.CommonResult;
import com.zemult.yovollserver.util.StringMatchUtils;
import com.zemult.yovollserver.util.StringUtils;
import com.zemult.yovollserver.util.ToastUtil;
import com.zemult.yovollserver.view.IdentifyingCodeView;

import butterknife.Bind;
import butterknife.OnClick;
import zema.volley.network.ResponseListener;

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
    @Bind(R.id.center_dialog)
    RoundLinearLayout centerDialog;
    @Bind(R.id.ll_phone)
    LinearLayout llPhone;
    @Bind(R.id.ll_code)
    LinearLayout llCode;
    @Bind(R.id.iv_lefticon)
    ImageView ivlefticon;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_next)
    Button btnNext;
    @Bind(R.id.icv)
    IdentifyingCodeView Icv;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.tv_sendcode)
    TextView tvSendcode;
    @Bind(R.id.tv_sendphone)
    TextView tvSendphone;


    CommonGetCodeRequest request_common_getcode;
    CommonCheckcodeRequest request_common_checkcode;
    private boolean isWait = false;
    private Thread mThread = null;
    String strPhone;
    private static final int WAIT = 0x001;
    @Override
    public void setContentView() {
        setContentView(R.layout.activity_be_server_1);
    }

    @Override
    public void init() {
        initData();
        initView();

        Icv.setInputCompleteListener(new IdentifyingCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                if(Icv.getTextContent().length()==4){
                    checkCode(Icv.getTextContent());
                }
            }

            @Override
            public void deleteContent() {
                Log.i("icv_delete", Icv.getTextContent());
            }
        });
    }

    private void initData() {

    }

    private void checkCode(String strCode ) {//发送验证码校验
        try {
            if (request_common_checkcode != null) {
                request_common_checkcode.cancel();
            }
            final CommonCheckcodeRequest.Input input = new CommonCheckcodeRequest.Input();
            input.phone = strPhone;
            input.code = strCode;
            input.convertJson();

            request_common_checkcode = new CommonCheckcodeRequest(input, new ResponseListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.print(error);
                }

                @Override
                public void onResponse(Object response) {
                    int status = ((CommonResult) response).status;
                    if (status == 1) {
                        Intent intent=new Intent(BeServerGuideFirstActivity.this,BeServerGuideSecondActivity.class);
                        intent.putExtra("strPhone",strPhone);
                        startActivity(intent);
                    } else {
                        ToastUtil.showMessage(((CommonResult) response).info);

                    }
                }
            });
            sendJsonRequest(request_common_checkcode);
        } catch (Exception e) {
            Log.e("COMMON_CHECKCODE", e.toString());
        }

    }



    private void getCode() {
        try {
            if (request_common_getcode != null) {
                request_common_getcode.cancel();
            }
            CommonGetCodeRequest.Input input = new CommonGetCodeRequest.Input();
            input.phone = strPhone;
            input.convertJson();
            request_common_getcode = new CommonGetCodeRequest(input, new ResponseListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.print(error);
                }

                @Override
                public void onResponse(Object response) {
                    int status = ((CommonResult) response).status;
                    if (status == 1) {
                        ToastUtil.showMessage("验证码已发送, 请查收!");
                        tvSendcode.setText("重新获取(" + 60 + "s)");
                        tvSendcode.setClickable(false);
                        tvSendcode.setTextColor(0xff828282);
                        waitForClick();
                    } else
                        ToastUtil.showMessage(((CommonResult) response).info);
                }
            });
            sendJsonRequest(request_common_getcode);
        } catch (Exception e) {
            Log.e("COMMON_GETCODE", e.toString());
        }

    }


    // 倒计时60s
    private void waitForClick() {
        isWait = true;
        final Handler handler = new Handler() {
            int i = 60;

            public void handleMessage(Message msg) {
                i--;
                tvSendcode.setText("重新获取(" + i + "s)");
                if (i == 0) {
                    isWait = false;
                    tvSendcode.setText("重新获取");
                    tvSendcode.setClickable(true);
                    tvSendcode.setTextColor(0xffe6bb7c);
                    i = 60;
                }
            }
        };

        mThread = new Thread() {
            @Override
            public void run() {
                while (isWait) {
                    try {
                        handler.sendEmptyMessage(WAIT);
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        mThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isWait = false;
    }

    private void initView() {
        lhTvTitle.setText("成为服务管家");
    }

    @OnClick({R.id.rtv_join_now,R.id.iv_righticon,R.id.btn_next,R.id.iv_lefticon,R.id.tv_sendcode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rtv_join_now:
                centerDialog.setVisibility(View.VISIBLE);
                ivlefticon.setVisibility(View.INVISIBLE);
                llPhone.setVisibility(View.VISIBLE);
                llCode.setVisibility(View.GONE);
                tvTitle.setText("登录");
                rtvJoinNow.setClickable(false);
                break;
            case R.id.iv_righticon:
                centerDialog.setVisibility(View.GONE);
                ivlefticon.setVisibility(View.INVISIBLE);
                llPhone.setVisibility(View.VISIBLE);
                llCode.setVisibility(View.GONE);
                tvTitle.setText("登录");
                break;
            case R.id.btn_next:
                ivlefticon.setVisibility(View.VISIBLE);
                tvTitle.setText("输入验证码");
                llPhone.setVisibility(View.GONE);
                llCode.setVisibility(View.VISIBLE);
                strPhone=etPhone.getText().toString().trim();
                tvSendphone.setText("验证码将发送至   "+strPhone);
                if (StringUtils.isBlank(strPhone)) {
                    ToastUtil.showMessage("请输入手机号码");
                } else {
                    if (!StringMatchUtils.isMobileNO(etPhone.getText().toString())) {
                        ToastUtil.showMessage("请输入正确的手机号码");
                        return;
                    }
                    getCode();
                }
                break;
            case R.id.tv_sendcode:
                if (StringUtils.isBlank(strPhone)) {
                    ToastUtil.showMessage("请输入手机号码");
                } else {
                    if (!StringMatchUtils.isMobileNO(etPhone.getText().toString())) {
                        ToastUtil.showMessage("请输入正确的手机号码");
                        return;
                    }
                    getCode();
                }
                break;
            case R.id.iv_lefticon:
                ivlefticon.setVisibility(View.INVISIBLE);
                tvTitle.setText("登录");
                llPhone.setVisibility(View.VISIBLE);
                llCode.setVisibility(View.GONE);
                rtvJoinNow.setClickable(true);
                break;
        }
    }

}
