package com.zemult.yovollserver.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zemult.yovollserver.R;
import com.zemult.yovollserver.adapter.CommonAdapter;
import com.zemult.yovollserver.adapter.CommonViewHolder;
import com.zemult.yovollserver.model.FilterEntity;
import com.zemult.yovollserver.util.DensityUtil;
import com.zemult.yovollserver.util.ToastUtil;

import java.util.List;

public class CommonCloseDialog {

	public static Dialog dialog;

	static LayoutInflater inflater;


	public static Dialog showDialog(final Context context, String titlename,int dialogtype
			 ) {
		if (inflater == null) {
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		View v = inflater.inflate(R.layout.dialog_common_close_view, null);
		Button btnNext = (Button) v.findViewById(R.id.btn_next);
		EditText etPhone = (EditText) v.findViewById(R.id.et_phone);
		ImageView ivLefticon= (ImageView) v.findViewById(R.id.iv_lefticon);
		ImageView ivRighticon= (ImageView) v.findViewById(R.id.iv_righticon);

		TextView msg = (TextView) v.findViewById(R.id.tv_title);
		msg.setText(titlename);
		btnNext.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {


			}
		});
		ivLefticon.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {


			}
		});

		ivRighticon.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog = new Dialog(context, R.style.translucent_notitle);
		dialog.setContentView(v);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
		return dialog;
	}



}
