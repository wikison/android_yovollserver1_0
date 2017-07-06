package com.zemult.yovollserver.util;

/**
 * Created by admin on 2017/7/6.
 */

public interface IWxCallback {
    int ERROR = 0;
    int ERROR_NETWORK_NULL = 1;
    int ERROR_INVALID_LOGINSTATE = 2;
    int ERROR_INVALID_CMDID = 3;
    int ERROR_REQ_NOT_ALLOWED = 4;
    int ERROR_VALID_FAIL = 5;
    int ERROR_INVALID_PARAMS = 6;
    int ERROR_TOKEN_UNAVAIL = 7;
    int ERROR_NETWORK_ERROR = 8;
    int ERROR_TIME_OUT = 9;
    int ERROR_TOKEN_SAME = 10;
    int ERROR_PARAM_ERR = 11;
    int ERROR_OOM = 12;
    int ERROR_POOL_FULL = 13;
    int ERROR_NO_EXIST_SDCARD = 15;
    int ERROR_UNPACK_ERR = 254;
    int ERROR_SERVER_ERR = 255;

    void onSuccess(Object... var1);

    void onError(int var1, String var2);

    void onProgress(int var1);
}