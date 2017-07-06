package com.zemult.yovollserver.util;

import android.app.Activity;
import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * Created by admin on 2017/7/6.
 */

public class RequestPermissionUtil {

    private static final String TAG = "RequestPermissionUtil";
    public static final int REQUEST_PERMISSIONS = 1;
    public static final int MANAGER_OVERLAY_CODE = 2;
    private static IWxCallback mCallback;

    public RequestPermissionUtil() {
    }

    public static void requestSDCardAndRecordPermission(Context activity, IWxCallback callback) {
        setCallback(callback);
        if(Build.VERSION.SDK_INT >= 23) {
            int writeSDCardPermission = activity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
            int recordPermission = activity.checkSelfPermission("android.permission.RECORD_AUDIO");
            ArrayList permissions = new ArrayList();
            if(writeSDCardPermission != 0) {
                permissions.add("android.permission.WRITE_EXTERNAL_STORAGE");
            }

            if(recordPermission != 0) {
                permissions.add("android.permission.RECORD_AUDIO");
            }

            if(permissions.size() > 0) {
                String[] params = (String[])permissions.toArray(new String[permissions.size()]);
                ActivityCompat.requestPermissions((Activity)activity, params, 1);
            } else {
                handleSuccess();
            }
        } else {
            handleSuccess();
        }

    }

    public static void requestSDCardCameraAndRecordPermission(Fragment fragment, IWxCallback callback) {
        setCallback(callback);
        if(Build.VERSION.SDK_INT >= 23) {
            int writeSDCardPermission = fragment.getActivity().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
            int recordPermission = fragment.getActivity().checkSelfPermission("android.permission.RECORD_AUDIO");
            int cameraPermission = fragment.getActivity().checkSelfPermission("android.permission.CAMERA");
            ArrayList permissions = new ArrayList();
            if(writeSDCardPermission != 0) {
                permissions.add("android.permission.WRITE_EXTERNAL_STORAGE");
            }

            if(recordPermission != 0) {
                permissions.add("android.permission.RECORD_AUDIO");
            }

            if(cameraPermission != 0) {
                permissions.add("android.permission.CAMERA");
            }

            if(permissions.size() > 0) {
                String[] params = (String[])permissions.toArray(new String[permissions.size()]);
                fragment.requestPermissions(params, 1);
            } else {
                handleSuccess();
            }
        } else {
            handleSuccess();
        }

    }

    public static void requestCameraPermission(Activity fragment, IWxCallback callback) {
        setCallback(callback);
        if(Build.VERSION.SDK_INT >= 23) {
            int cameraPermission = fragment.checkSelfPermission("android.permission.CAMERA");
            if(cameraPermission != 0) {
                fragment.requestPermissions(new String[]{"android.permission.CAMERA"}, 1);
            } else {
                handleSuccess();
            }
        } else {
            handleSuccess();
        }

    }

    public static void requestReadSdCardPermission(Fragment fragment, IWxCallback callback) {
        setCallback(callback);
        if(Build.VERSION.SDK_INT >= 23) {
            int readSdCardPermission = fragment.getActivity().checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE");
            if(readSdCardPermission != 0) {
                fragment.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 1);
            } else {
                handleSuccess();
            }
        } else {
            handleSuccess();
        }

    }

    public static void requestPermissions(Fragment fragment, IWxCallback callback) {
        setCallback(callback);
        if(Build.VERSION.SDK_INT >= 23) {
            byte permissionGranted = 0;
            int writeSDCardPermission = fragment.getActivity().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
            int readPhoneStatePermission = fragment.getActivity().checkSelfPermission("android.permission.READ_PHONE_STATE");
            ArrayList permissions = new ArrayList();
            if(writeSDCardPermission != permissionGranted) {
                permissions.add("android.permission.WRITE_EXTERNAL_STORAGE");
            }

            if(readPhoneStatePermission != permissionGranted) {
                permissions.add("android.permission.READ_PHONE_STATE");
            }

            if(permissions.size() > 0) {
                String[] params = (String[])permissions.toArray(new String[permissions.size()]);
                fragment.requestPermissions(params, 1);
            } else {
                handleSuccess();
            }
        } else {
            handleSuccess();
        }

    }

    /**
     * 作用：用户是否同意录音权限
     *
     * @return true 同意 false 拒绝
     */
    public boolean isVoicePermission() {

        try {
            AudioRecord record = new AudioRecord(MediaRecorder.AudioSource.MIC, 22050, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                    AudioFormat.ENCODING_PCM_16BIT, AudioRecord.getMinBufferSize(22050, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                    AudioFormat.ENCODING_PCM_16BIT));
            record.startRecording();
            int recordingState = record.getRecordingState();
            if(recordingState == AudioRecord.RECORDSTATE_STOPPED){
                return false;
            }
            record.release();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    private static void setCallback(IWxCallback callback) {
        mCallback = callback;
    }

    private static void handleSuccess() {
        IWxCallback callback = getAndClearCallback();
        if(callback != null) {
            callback.onSuccess(new Object[0]);
        }

    }

    public static IWxCallback getAndClearCallback() {
        IWxCallback tempCallback = mCallback;
        mCallback = null;
        return tempCallback;
    }
}
