package com.zemult.yovollserver.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zemult.yovollserver.R;
import com.zemult.yovollserver.util.ImageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/6/13.
 */
public class HomePeopleAdapter extends
        RecyclerView.Adapter<HomePeopleAdapter.ViewHolder>
{

    private LayoutInflater mInflater;
    private List<String> mDatas;
    private ImageManager imageManager;
    private boolean noPeople;


    public HomePeopleAdapter(Context context, String heads)
    {
        mInflater = LayoutInflater.from(context);
        initPhotos(heads);
        imageManager = new ImageManager(context);
    }

    private void initPhotos(String pic){
        mDatas = new ArrayList<>();
        if(TextUtils.isEmpty(pic)){
            mDatas.add("");
            noPeople = true;
            return;
        }

        if (pic.contains(",")) {
            String[] photosarray = pic.split(",");

            for (int i = 0; i < photosarray.length; i++) {
                mDatas.add(photosarray[i]);
            }
        } else {
            mDatas.add(pic);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ViewHolder(View arg0)
        {
            super(arg0);
        }

        ImageView iv;
    }

    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = mInflater.inflate(R.layout.item_home_people,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.iv = (ImageView) view.findViewById(R.id.iv_head);
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(noPeople)
            holder.iv.setImageResource(R.mipmap.yueke_icon);
        else
            imageManager.loadCircleHead(mDatas.get(position), holder.iv, "@70w_70h_1e");
    }
}
