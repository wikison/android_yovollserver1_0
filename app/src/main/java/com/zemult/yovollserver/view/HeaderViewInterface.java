package com.zemult.yovollserver.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zemult.yovollserver.util.ImageManager;


public abstract class HeaderViewInterface<T> {

    protected Activity mContext;
    protected LayoutInflater mInflate;
    protected T mEntity;
    protected ImageManager mImageManager;

    public HeaderViewInterface(Activity context) {
        this.mContext = context;
        mInflate = LayoutInflater.from(context);
        mImageManager = new ImageManager(context);
    }

    public boolean fillView(T t, ViewGroup viewGroup) {
//        if (t == null) {
//            return false;
//        }
//        if ((t instanceof List) && ((List) t).size() == 0) {
//            return false;
//        }
        this.mEntity = t;
        getView(t, viewGroup);
        return true;
    }

    protected abstract void getView(T t, ViewGroup viewGroup);
}
