package com.zemult.yovollserver.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zemult.yovollserver.R;
import com.zemult.yovollserver.app.BaseActivity;
import com.zemult.yovollserver.config.Constants;
import com.zemult.yovollserver.model.M_Userinfo;
import com.zemult.yovollserver.util.AppUtils;
import com.zemult.yovollserver.util.IWxCallback;
import com.zemult.yovollserver.util.ImageHelper;
import com.zemult.yovollserver.util.RequestPermissionUtil;
import com.zemult.yovollserver.util.StringUtils;
import com.zemult.yovollserver.util.ToastUtil;
import com.zemult.yovollserver.util.UserManager;
import com.zemult.yovollserver.util.imagepicker.Bimp;
import com.zemult.yovollserver.util.imagepicker.ChoosePicRec;
import com.zemult.yovollserver.util.imagepicker.ImageAlbumActivity;
import com.zemult.yovollserver.util.oss.OssHelper;
import com.zemult.yovollserver.util.oss.OssService;
import com.zemult.yovollserver.view.MMAlert;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Wikison on 2017/6/29.
 */


public class SetUserInfoActivity extends BaseActivity {

    @Bind(R.id.lh_btn_back)
    Button lhBtnBack;
    @Bind(R.id.ll_back)
    LinearLayout llBack;
    @Bind(R.id.lh_tv_title)
    TextView lhTvTitle;
    @Bind(R.id.lh_btn_right)
    Button lhBtnRight;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(R.id.iv_head)
    ImageView ivHead;



    private String headString = "", tackPhotoName = "", imageUrl;
    private Uri imageUri;


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_set_user_info);
    }

    @Override
    public void init() {
        lhTvTitle.setText("个人设置");
        lhBtnRight.setText("保存");
        registerReceiver(new String[]{Constants.BROCAST_OSS_UPLOADIMAGE});

        if(UserManager.instance().getUserinfo()!=null){
            imageManager.loadCircleHead(UserManager.instance().getUserinfo().getUserHead(),ivHead);
            etName.setText(UserManager.instance().getUserinfo().getUserName());
            etPwd.setText(UserManager.instance().getUserinfo().getPassword());
        }
    }

    @OnClick({R.id.lh_btn_back, R.id.ll_back, R.id.lh_btn_right, R.id.iv_head})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lh_btn_back:
            case R.id.ll_back:
                onBackPressed();
                break;
            case R.id.iv_head:
                MMAlert.addPhotoDialog(SetUserInfoActivity.this, new MMAlert.AddPhotoCallback() {
                    @Override
                    public void onTakePic() {
                        //为了适配6.0+系统，这里需要先申请一下读SD卡权限
                        RequestPermissionUtil.requestCameraPermission
                                (SetUserInfoActivity.this, new IWxCallback() {
                            @Override
                            public void onSuccess(Object... result) {
                                takePic();
                            }

                            @Override
                            public void onError(int code, String info) {

                            }

                            @Override
                            public void onProgress(int progress) {

                            }
                        });
                    }

                    @Override
                    public void onChoosePoc() {
                        choosePic();
                    }
                });
                break;

            case R.id.lh_btn_right:
                if(StringUtils.isBlank(etName.getText().toString().trim())){
                    ToastUtil.showMessage("请输入姓名");
                    return;
                }
                if(StringUtils.isBlank(etPwd.getText().toString().trim())){
                    ToastUtil.showMessage("请输入密码");
                    return;
                }
                Intent intent1 = new Intent();
                intent1.putExtra("headString", headString);
                intent1.putExtra("username",etName.getText().toString().trim());
                intent1.putExtra("password",etPwd.getText().toString().trim());
                setResult(RESULT_OK, intent1);

                M_Userinfo m_userinfo=new M_Userinfo();
                m_userinfo.setUserHead(headString);
                m_userinfo.setUserName(etName.getText().toString().trim());
                m_userinfo.setPassword(etPwd.getText().toString().trim());
                UserManager.instance().saveUserinfo(m_userinfo);
                break;
        }
    }


    //接收广播回调
    @Override
    protected void handleReceiver(Context context, Intent intent) {

        if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            return;
        }
        Log.d(getClass().getName(), "[onReceive] action:" + intent.getAction());
        if (Constants.BROCAST_OSS_UPLOADIMAGE.equals(intent.getAction())) {
            if (intent.getStringExtra("status").equals("ok")) {
                headString = Constants.OSSENDPOINT + intent.getStringExtra("object");
                //修改本地IM的头像
            } else {
                ToastUtil.showMessage(intent.getStringExtra("info"));
            }
        }
    }


    /**
     * 拍照
     */
    public void takePic() {
        tackPhotoName = AppUtils.getStringToday() + ".jpg";
        AppUtils.tackPic(this, AppUtils.getStringToday() + ".jpg", Constants.TACKPHOTO);
    }

    /**
     * 选择本地图片
     */
    public void choosePic() {
        chooseImgs(this, 1, choosePicRec);
    }

    /**
     * 从相册选择多张图片,注意: 传入的activity需要解绑ChoosePicRec
     *
     * @param activity
     * @param maxNum   最大可挑选的数量
     */
    public void chooseImgs(Activity activity, int maxNum, ChoosePicRec choosePicRec) {
        try {
            Bimp.clear();
            IntentFilter intentFilter = new IntentFilter("CHOOSEIMG");
            Intent intent = new Intent(activity, ImageAlbumActivity.class);
            activity.registerReceiver(choosePicRec, intentFilter);
            intent.putExtra("maxSize", maxNum);
            intent.putExtra("chooseHead", maxNum);
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // 拍照
            if (resultCode == RESULT_OK && requestCode == Constants.TACKPHOTO) {
                AppUtils.tackPickResult(tackPhotoName, chooseImgHandler);
            }
            // 裁剪
            else if (resultCode == RESULT_OK && requestCode == Constants.PHOTO_CROP) {
                if (data != null) {
                    if (imageUri != null) {
                        Bitmap bitmap = decodeUriAsBitmap(imageUri);
                        String path = AppUtils.removeFileHeader(ImageHelper.saveRotateNoCompressBitmap(new File(imageUrl)));
                        if (bitmap != null && bitmap.isRecycled()) {
                            bitmap.recycle();
                        }
                        uploadImg(path);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Bitmap decodeUriAsBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }


    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }


    protected Handler chooseImgHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                switch (msg.what) {
                    case Constants.CHOOSE_PHOTOS:
                        // 取消广播监听
                        SetUserInfoActivity.this.unregisterReceiver(choosePicRec);
                        crop(((List<String>) msg.obj).get(0));
                        break;
                    case Constants.PHOTO_COMPASS_SUCCESS:
                        // 拍照并 压缩照片成功
                        try {
                            crop(msg.obj.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    protected ChoosePicRec choosePicRec = new ChoosePicRec(chooseImgHandler);

    private void uploadImg(String path) {
        showUncanclePd();
        OssService ossService = OssHelper.initOSS(this);
            String ossImgname = "app/android_" + System.currentTimeMillis() + ".jpg";
            ossService.asyncPutImage(ossImgname, path);
        dismissPd();
    }

    private void crop(String path) {
        imageUrl = Constants.SAVE_IMAGE_PATH_IMGS + new SimpleDateFormat("yyMMddHHmmss")
                .format(new Date()) + ".jpg";
        imageUri = Uri.fromFile(new File(imageUrl));

        Intent intent = new Intent();

        intent.setAction("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT < 24) {
            intent.setDataAndType(Uri.fromFile(new File(path.replace("file://", ""))), "image/*");// mUri是已经选择的图片Uri
        } else {
            intent.setDataAndType(getImageContentUri(new File(path.replace("file://", ""))), "image/*");// mUri是已经选择的图片Uri

        }
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 400);// 裁剪框比例
        intent.putExtra("aspectY", 400);
        intent.putExtra("outputX", 400);// 输出图片大小
        intent.putExtra("outputY", 400);
//        intent.putExtra("return-data", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        startActivityForResult(intent, Constants.PHOTO_CROP);
    }


    public Uri getImageContentUri(File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.lh_btn_back, R.id.ll_back, R.id.lh_btn_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lh_btn_back:
                break;
            case R.id.ll_back:
                break;
            case R.id.lh_btn_right:
                break;
        }
    }
}
