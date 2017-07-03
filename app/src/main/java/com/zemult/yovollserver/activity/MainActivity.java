package com.zemult.yovollserver.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zemult.yovollserver.R;
import com.zemult.yovollserver.adapter.HomePagerAdapter;
import com.zemult.yovollserver.app.BaseActivity;

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
        HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager(), this);
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
                break;
            case R.id.rll_send_order:
                break;
        }
    }
}
