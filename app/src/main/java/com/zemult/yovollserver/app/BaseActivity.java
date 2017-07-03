package com.zemult.yovollserver.app;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.android.volley.Request;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.zemult.yovollserver.R;
import com.zemult.yovollserver.activity.LoginActivity;
import com.zemult.yovollserver.util.AppUtils;
import com.zemult.yovollserver.util.ImageManager;
import com.zemult.yovollserver.util.SlashHelper;
import com.zemult.yovollserver.view.CommonDialog;
import com.zemult.yovollserver.view.LoadingDialog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.ButterKnife;
import zema.volley.network.VolleyUtil;

/**
 * Created by Wikison on 2017/6/19.
 */

/**
 * Created by wikison on 2016/6/2.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected ArrayList<WeakReference<Request>> listJsonRequest = new ArrayList<WeakReference<Request>>();
    public LoadingDialog loadingDialog;
    private InternalReceiver internalReceiver;

    /**
     * 初始化layout
     */
    public abstract void setContentView();

    public ImageManager imageManager;

    /**
     * 初始化数据和空间
     */
    public abstract void init();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView();
        ButterKnife.bind(this);
        checkStoragePermission();
        imageManager = new ImageManager(getApplicationContext());
        loadingDialog = new LoadingDialog(this);
        loadingDialog.setMessageText("数据加载...");
        init();
    }

    protected void checkStoragePermission() {
        boolean bWritePermission = AndPermission.hasPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (bWritePermission) {

        } else {
            getWritePermission();
        }
    }


    public void showPd() {
        try {
            if (loadingDialog != null && !loadingDialog.isShowing()) {
                loadingDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void showUncanclePd() {
        if (loadingDialog != null && !loadingDialog.isShowing()) {
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.setCancelable(false);
            loadingDialog.show();
        }
    }

    public void dismissPd() {
        try {
            if (loadingDialog != null && loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送请求
     *
     * @param request
     */
    public void sendJsonRequest(Request request) {
        AppUtils.isNetworkAvailable(this);
        WeakReference<Request> ref = new WeakReference<Request>(request);
        listJsonRequest.add(ref);
        VolleyUtil.getRequestQueue().add(request);
    }

    /*
     * 返回
     */
    public void doBack(View view) {
        onBackPressed();
    }

    protected final void registerReceiver(String[] actionArray) {
        if (actionArray == null) {
            return;
        }
        IntentFilter intentfilter = new IntentFilter();
        for (String action : actionArray) {
            intentfilter.addAction(action);
        }
        if (internalReceiver == null) {
            internalReceiver = new InternalReceiver();
        }
        registerReceiver(internalReceiver, intentfilter);
    }

    // Internal calss.
    private class InternalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                return;
            }
            handleReceiver(context, intent);
        }
    }

    /**
     * 如果子界面需要拦截处理注册的广播 需要实现该方法
     *
     * @param context
     * @param intent
     */
    protected void handleReceiver(Context context, Intent intent) {
        // 广播处理
        if (intent == null) {
            return;
        }
    }

    @Override
    protected void onDestroy() {
        if (listJsonRequest != null && !listJsonRequest.isEmpty()) { //遍历取消所有请求
            for (WeakReference<Request> ref : listJsonRequest) {
                Request req = ref.get();
                if (req != null) {
                    req.cancel();
                }
            }
        }
        try {
            if (internalReceiver != null) {
                unregisterReceiver(internalReceiver);
            }
        } catch (Exception e) {
        }
        super.onDestroy();
    }

    public boolean noLogin(final Context context) {
        // 没有登录跳转到登录界面
        if (SlashHelper.userManager().getUserinfo() == null) {
            CommonDialog.showDialogListener(context, null, "取消", "去登录", getResources().getString(R.string.not_login_tip), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommonDialog.DismissProgressDialog();

                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommonDialog.DismissProgressDialog();
                    startActivity(new Intent(context, LoginActivity.class));
                }
            });
            return true;
        }
        return false;
    }

    private void getWritePermission() {
        AndPermission.with(this)
                .requestCode(100)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .callback(this)
                .start();
    }

    @PermissionYes(100)
    private void getWriteYes() {

    }

    @PermissionNo(100)
    private void getWriteNo() {
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

}
