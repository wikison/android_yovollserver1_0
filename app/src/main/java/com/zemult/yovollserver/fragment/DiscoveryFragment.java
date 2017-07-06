package com.zemult.yovollserver.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zemult.yovollserver.R;
import com.zemult.yovollserver.activity.MyCustomerActivity;
import com.zemult.yovollserver.adapter.CommonAdapter;
import com.zemult.yovollserver.adapter.CommonViewHolder;
import com.zemult.yovollserver.app.BaseFragment;
import com.zemult.yovollserver.util.IntentUtil;
import com.zemult.yovollserver.view.FixedGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wikison on 2017/6/23.
 */
public class DiscoveryFragment extends BaseFragment {

    @Bind(R.id.fgv_list)
    FixedGridView fgvList;

    private Context mContext;
    private Activity mActivity;
    List<Item> itemList = new ArrayList<Item>();
    CommonAdapter commonAdapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discovery, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();

    }

    private void initData() {
        mContext = getActivity();
        mActivity = getActivity();
        initListData();

    }

    private void initListData() {
        itemList.add(new Item(R.mipmap.guanli, "客户管理", MyCustomerActivity.class));
        itemList.add(new Item(R.mipmap.pingjia, "客户评价", null));
        itemList.add(new Item(R.mipmap.gonglue, "管家攻略", null));

        fgvList.setAdapter(commonAdapter = new CommonAdapter<Item>(mActivity, R.layout.item_grid_menu, itemList) {
            @Override
            public void convert(CommonViewHolder holder, final Item item, final int position) {
                holder.setImageResource(R.id.iv, item.resId);
                holder.setText(R.id.tv_name, item.name);

                holder.setOnclickListener(R.id.ll_root, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.cls != null)
                            IntentUtil.start_activity(mActivity, item.cls);
                    }
                });
            }

        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    protected void lazyLoad() {

    }

    class Item {
        int resId;
        String name;
        Class<?> cls;

        Item(int resId, String name, Class<?> cls) {
            this.resId = resId;
            this.name = name;
            this.cls = cls;
        }

    }

}
