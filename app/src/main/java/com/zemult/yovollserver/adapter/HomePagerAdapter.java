package com.zemult.yovollserver.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zemult.yovollserver.R;
import com.zemult.yovollserver.fragment.DiscoveryFragment;
import com.zemult.yovollserver.fragment.HomeFragment;
import com.zemult.yovollserver.fragment.MineFragment;

/**
 * Created by Wikison on 2017/6/26.
 */

public class HomePagerAdapter extends FragmentStatePagerAdapter {

    private Context mContext;
    private String[] titles = new String[]{"我的", "", "发现"};
    private int[] imageResId = new int[]{R.mipmap.xiaolian, R.mipmap.xiaolian, R.mipmap.xiaolian};

    MineFragment mineFragment;
    HomeFragment homeFragment;
    DiscoveryFragment discoveryFragment;

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public HomePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                mineFragment = new MineFragment();
                return mineFragment;
            case 1:
                homeFragment = new HomeFragment();
                return homeFragment;
            case 2:
                discoveryFragment = new DiscoveryFragment();
                return discoveryFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable image = mContext.getResources().getDrawable(imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return titles[position];
        return null;
    }


    public View getTabView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tab_item, null);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(titles[position]);
        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        img.setImageResource(imageResId[position]);
        if (position != 1) {
            img.setVisibility(View.GONE);
        } else {
            tv.setVisibility(View.GONE);

        }

        return view;
    }


}
