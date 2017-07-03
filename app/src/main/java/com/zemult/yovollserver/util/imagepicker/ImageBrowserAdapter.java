package com.zemult.yovollserver.util.imagepicker;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.zemult.yovollserver.R;
import com.zemult.yovollserver.util.ImageManager;
import com.zemult.yovollserver.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ImageBrowserAdapter extends PagerAdapter {

    private List<String> mPhotos = new ArrayList<String>();
    private Context context;
    ImageManager imageManager;

    public interface OnItemClickCallback {
        public void onItemClick();
    }

    public interface OnItemLongClickCallback {
        public void onLongClick(View imageAware);
    }

    private OnItemClickCallback onItemClickCallback;
    private OnItemLongClickCallback onLongClick;

    public ImageBrowserAdapter(Context context, List<String> photos) {
        mPhotos = photos;
        this.context = context;
        imageManager = new ImageManager(context);
    }

    @Override
    public int getCount() {
        return mPhotos.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View) object;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_photoview, null);
        PhotoView photoView = (PhotoView) view.findViewById(R.id.pv);

        if (StringUtils.isBlank(mPhotos.get(position)))
            photoView.setImageResource(R.mipmap.user_icon);
        else if (mPhotos.get(position).indexOf("http://") == -1) {
            imageManager.loadLocalImage(mPhotos.get(position), photoView);
        } else {
            imageManager.loadUrlImage(mPhotos.get(position), photoView);
        }
        container.addView(view, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v1) {
                if (null != onItemClickCallback)
                    onItemClickCallback.onItemClick();
            }
        });
        photoView.setOnLongClickListener(new OnLongClickListener() {

            @Override
            public boolean onLongClick(View arg0) {
                if (onLongClick != null) {
                    onLongClick.onLongClick(arg0);
                    return false;
                } else {
                    return true;
                }
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setDataChanged(List<String> photos) {
        this.mPhotos = photos;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setOnItemLongClickCallback(OnItemLongClickCallback onLongClick) {
        this.onLongClick = onLongClick;
    }
}
