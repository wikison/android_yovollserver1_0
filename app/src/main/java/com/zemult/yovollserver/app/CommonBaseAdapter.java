package com.zemult.yovollserver.app;

import android.content.Context;
import android.widget.BaseAdapter;

import com.zemult.yovollserver.util.ImageManager;
import com.zemult.yovollserver.view.LoadingDialog;


public abstract class CommonBaseAdapter extends BaseAdapter {
    protected Context mContext;
    private boolean isScrolling = false;
    public LoadingDialog pd;
    public ImageManager imageManager;

    public CommonBaseAdapter(Context context) {
        this.mContext = context;
        pd = new LoadingDialog(mContext);
        pd.setCancelable(false);
        imageManager=new ImageManager(context);
    }

    public boolean isScrolling() {
        return isScrolling;
    }

    public void setScrolling(boolean isScrolling) {
        this.isScrolling = isScrolling;
        this.notifyDataSetChanged();
    }

    protected void showPd() {
        if (pd != null && !pd.isShowing()) {
            pd.show();
        }
    }

    protected void dismissPd() {
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
    }


}
