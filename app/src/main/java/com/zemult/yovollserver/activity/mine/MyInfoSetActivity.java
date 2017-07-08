package com.zemult.yovollserver.activity.mine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.zemult.yovollserver.R;
import com.zemult.yovollserver.adapter.PhotoFix3Adapter;
import com.zemult.yovollserver.aip.UserEditinfoRequest;
import com.zemult.yovollserver.aip.common.UserLogoutRequest;
import com.zemult.yovollserver.app.BaseActivity;
import com.zemult.yovollserver.config.Constants;
import com.zemult.yovollserver.model.CommonResult;
import com.zemult.yovollserver.util.DensityUtil;
import com.zemult.yovollserver.util.SlashHelper;
import com.zemult.yovollserver.util.ToastUtil;
import com.zemult.yovollserver.view.CommonDialog;
import com.zemult.yovollserver.view.FixedGridView;

import butterknife.Bind;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import zema.volley.network.ResponseListener;

public class MyInfoSetActivity extends BaseActivity {

    @Bind(R.id.lh_btn_back)
    Button lhBtnBack;
    @Bind(R.id.ll_back)
    LinearLayout llBack;
    @Bind(R.id.lh_tv_title)
    TextView lhTvTitle;
    @Bind(R.id.iv_head)
    ImageView ivHead;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.rl_head)
    RelativeLayout rlHead;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.rl_name)
    RelativeLayout rlName;
    @Bind(R.id.gv_pic)
    FixedGridView gvPic;
    @Bind(R.id.ll_photo)
    LinearLayout llPhoto;
    @Bind(R.id.rl_qr)
    RelativeLayout rlQr;
    @Bind(R.id.btn_exit)
    Button btnExit;

    UserEditinfoRequest userEditinfoRequest;
    UserLogoutRequest userLogoutRequest;
    String nameString, headString, accountString, picString;

    Context mContext;
    Activity mActivity;

    //private YWIMKit mIMKit;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_myinfo_set);

    }

    @Override
    public void init() {
        initData();
        initView();
        initListener();
    }

    private void initListener() {

    }

    private void initData() {
        mContext = this;
        mActivity = this;
//        mIMKit = LoginSampleHelper.getInstance().getIMKit();
//        if (mIMKit == null) {
//            return;
//        }

        //注册修改头像广播
        registerReceiver(new String[]{Constants.BROCAST_EDITUSERINFO});
    }

    private void initView() {
        lhTvTitle.setText("个人信息");
        tvName.setText(SlashHelper.userManager().getUserinfo().getName());
        imageManager.loadCircleHead(SlashHelper.userManager().getUserinfo().getHead(), ivHead);

        nameString = SlashHelper.userManager().getUserinfo().getName();
        headString = SlashHelper.userManager().getUserinfo().getHead();
        accountString = SlashHelper.userManager().getUserinfo().getAccount();
        picString = SlashHelper.userManager().getUserinfo().pics;

        // 相册图片(多个用","分隔，最多显示3个)
        if (!TextUtils.isEmpty(picString)) {
            PhotoFix3Adapter adapter = new PhotoFix3Adapter(this, picString);
            adapter.setWidth(DensityUtil.dip2px(this, 70));
            adapter.setRule("@50p");
            gvPic.setAdapter(adapter);
        }
    }

    @OnClick({R.id.ll_back, R.id.lh_btn_back, R.id.rl_head, R.id.rl_name, R.id.ll_photo, R.id.rl_qr, R.id.btn_exit})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.lh_btn_back:
            case R.id.ll_back:
                onBackPressed();
                break;
            case R.id.rl_head:
                //头像
                startActivityForResult(new Intent(mActivity, HeadManageActivity.class), 110);
                break;
            case R.id.rl_name:
                //名字
                intent = new Intent(mActivity, NicknameActivity.class);
                startActivityForResult(intent, Constants.REQUESTCODE_CHANGENICKNAME);
                break;
            case R.id.ll_photo:
                intent = new Intent(mActivity, AlbumActivity.class);
                intent.putExtra(AlbumActivity.INTENT_USERID, SlashHelper.userManager().getUserId());
                startActivity(intent);
                break;
            case R.id.rl_qr:
                //我的二维码
                intent = new Intent(mActivity, MyQrActivity.class);
                intent.putExtra("from", "MyInfoSet");
                startActivity(intent);
                break;
            case R.id.btn_exit:

                CommonDialog.showDialogListener(MyInfoSetActivity.this, null, "否", "是", getResources().getString(R.string.login_out_tip), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CommonDialog.DismissProgressDialog();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CommonDialog.DismissProgressDialog();
                        //退出用户登录
                        logout();
                    }
                });
                break;

        }
    }

    //接收广播回调
    @Override
    protected void handleReceiver(Context context, Intent intent) {

        if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            return;
        }
        Log.d(getClass().getName(), "[onReceive] action:" + intent.getAction());
        if (Constants.BROCAST_EDITUSERINFO.equals(intent.getAction())) {
            user_editinfo();
        }
    }

    //用户退出登录
    private void logout() {

        if (userLogoutRequest != null) {
            userLogoutRequest.cancel();
        }
        UserLogoutRequest.Input input = new UserLogoutRequest.Input();
        if (SlashHelper.userManager().getUserinfo() != null) {
            input.userId = SlashHelper.userManager().getUserId();
        }
        input.device_token = SlashHelper.deviceManager().getUmengDeviceToken();
        input.convertJson();
        userLogoutRequest = new UserLogoutRequest(input, new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

            @Override
            public void onResponse(Object response) {
                if (((CommonResult) response).status == 1) {
//                    SlashHelper.userManager().getUserinfo();
                    SlashHelper.userManager().saveUserinfo(null);
                    ImLogout();
                    ToastUtil.showMessage("退出成功");
                    EventBus.getDefault().post("exit");
                    finish();
                }
            }
        });
        sendJsonRequest(userLogoutRequest);
    }

    public void ImLogout() {
        // openIM SDK提供的登录服务
//        IYWLoginService mLoginService = mIMKit.getLoginService();
//
//        mLoginService.logout(new IWxCallback() {
//            //此时logout已关闭所有基于IMBaseActivity的OpenIM相关Actiivity，s
//            @Override
//            public void onSuccess(Object... arg0) {
//                YWLog.i("------IM_LOGOUT---------", "退出成功");
//                String account = YWAPI.getCurrentUser();
//                mIMKit = YWAPI.getIMKitInstance(account);
//                LoginSampleHelper.getInstance().setIMKit(mIMKit);
//            }
//
//            @Override
//            public void onProgress(int arg0) {
//
//            }
//
//            @Override
//            public void onError(int arg0, String arg1) {
//                ToastUtil.showMessage("退出失败,请重新登录");
//            }
//        });
    }


    //修改用户资料信息
    private void user_editinfo() {
        showPd();
        if (userEditinfoRequest != null) {
            userEditinfoRequest.cancel();
        }
        UserEditinfoRequest.Input input = new UserEditinfoRequest.Input();
        if (SlashHelper.userManager().getUserinfo() != null) {
            input.userId = SlashHelper.userManager().getUserId();
            input.name = nameString;
            input.head = headString;
            input.convertJson();
        }

        userEditinfoRequest = new UserEditinfoRequest(input, new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dismissPd();
            }

            @Override
            public void onResponse(Object response) {
                if (((CommonResult) response).status == 1) {
                    SlashHelper.userManager().getUserinfo().setName(nameString);
                    SlashHelper.userManager().getUserinfo().setHead(headString);
                    SlashHelper.userManager().getUserinfo().setAccount(accountString);


                    SlashHelper.userManager().saveUserinfo(SlashHelper.userManager().getUserinfo());
                    initData();

                } else {
                    ToastUtil.showMessage(((CommonResult) response).info);
                }
                dismissPd();
            }
        });
        sendJsonRequest(userEditinfoRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUESTCODE_CHANGENICKNAME && resultCode == RESULT_OK) {
            nameString = SlashHelper.userManager().getUserinfo().getName();
            //修改用户资料信息
            tvName.setText(SlashHelper.userManager().getUserinfo().getName());
            user_editinfo();
        }
       else if (requestCode == 110 && resultCode == RESULT_OK) {
            headString = SlashHelper.userManager().getUserinfo().getHead();
            imageManager.loadCircleHead(headString, ivHead);
        }
        Intent in = new Intent(Constants.BROCAST_LOGIN);
        sendBroadcast(in);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
