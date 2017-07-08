package com.zemult.yovollserver.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.message.PushAgent;
import com.umeng.message.common.UmLog;
import com.umeng.message.inapp.IUmengInAppMsgCloseCallback;
import com.umeng.message.inapp.InAppMessageManager;
import com.zemult.yovollserver.R;
import com.zemult.yovollserver.adapter.HomePagerAdapter;
import com.zemult.yovollserver.app.BaseActivity;
import com.zemult.yovollserver.util.IntentUtil;
import com.zemult.yovollserver.util.SlashHelper;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Wikison on 2017/6/20.
 * 首页
 */

public class MainActivity extends BaseActivity {
    @Bind(R.id.tab)
    TabLayout tab;
    @Bind(R.id.vp)
    ViewPager vp;

    HomePagerAdapter adapter;
    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);

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
        adapter = new HomePagerAdapter(getSupportFragmentManager(), this);
        vp.setAdapter(adapter);
        vp.setOffscreenPageLimit(3);
        vp.setCurrentItem(1);
        tab.setupWithViewPager(vp);
        tab.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < tab.getTabCount(); i++) {
            TabLayout.Tab iTab = tab.getTabAt(i);
            iTab.setCustomView(adapter.getTabView(i));
        }
    }

    private void initListener() {
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                vp.setCurrentItem(position);

                for (int i = 0; i < tab.getTabCount(); i++) {
                    if (i == position) {
                        TabLayout.Tab iTab = tab.getTabAt(i);
                        View v = iTab.getCustomView();
                        ImageView iv = (ImageView) v.findViewById(R.id.imageView);
                        TextView tv = (TextView) v.findViewById(R.id.textView);
                        iv.setImageResource(R.mipmap.xiaolian);
                        tv.setTextColor(0xfff6ca89);
                    } else {
                        TabLayout.Tab iTab = tab.getTabAt(i);
                        View v = iTab.getCustomView();
                        ImageView iv = (ImageView) v.findViewById(R.id.imageView);
                        TextView tv = (TextView) v.findViewById(R.id.textView);
                        iv.setImageResource(R.mipmap.xiaolian_weixuan);
                        tv.setTextColor(0xffc2c2c2);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.rll_buy, R.id.rll_send_order})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.rll_buy:
                IntentUtil.start_activity(MainActivity.this, PayOrderActivity.class);
                break;
            case R.id.rll_send_order:
                IntentUtil.start_activity(MainActivity.this, SendOrderActivity.class);
                //
                break;
        }
    }
}
