package com.zemult.yovollserver.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zemult.yovollserver.R;

/**
 * Created by Wikison on 2017/6/22.
 */

public class LoadingDialog extends Dialog {

    /**
     * 缓冲界面
     *
     * @param context
     */
    TextView loadingText;

    public LoadingDialog(Context context) {
        super(context, R.style.dialog_activity);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_loading, null);
        ImageView ivLoading = (ImageView) dialogView.findViewById(R.id.loading);
        loadingText = (TextView) dialogView.findViewById(R.id.loadingtext);
        ivLoading.setImageResource(R.drawable.drawable_bg_loading);
        setContentView(dialogView);
        AnimationDrawable animationDrawable = (AnimationDrawable) ivLoading.getDrawable();
        animationDrawable.start();
        setCanceledOnTouchOutside(true);
        setCancelable(true);
    }

    public void setMessageText(String messageText) {
        loadingText.setText(messageText);
    }
}