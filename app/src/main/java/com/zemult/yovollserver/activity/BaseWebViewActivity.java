package com.zemult.yovollserver.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zemult.yovollserver.R;
import com.zemult.yovollserver.app.MBaseActivity;
import com.zemult.yovollserver.config.Constants;
import com.zemult.yovollserver.view.ProgressWebView;


/**
 * 使用条款和隐私策略
 */
public class BaseWebViewActivity extends MBaseActivity {

    String url, titlename, showShare, reservationId;
    private Context mContext;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = this;
        url = getIntent().getStringExtra("url");
        titlename = getIntent().getStringExtra("titlename") == null ? "" : getIntent().getStringExtra("titlename");
        View appView = getLayoutInflater().inflate(
                R.layout.activity_base_webview, null);
        ProgressWebView wView = (ProgressWebView) appView.findViewById(R.id.web_view);
        WebSettings wSet = wView.getSettings();
        wSet.setJavaScriptEnabled(true);
        wView.setWebViewClient(new MyWebViewClient());
        wView.loadUrl(url);
        appMainView.addView(appView, layoutParams);
        setTitleLeftButton("");
        setTitleText(titlename);

    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            String info = "";
            //此处根据需求处理逻辑...
            if (url.startsWith(Constants.SCHEME_PREFIX)) {
                info = url.substring(Constants.SCHEME_PREFIX.length());
                if (info.equalsIgnoreCase("merchantAdd")) {

                }

            } else {
                view.loadUrl(url);

            }
            return true;
        }

    }


}
