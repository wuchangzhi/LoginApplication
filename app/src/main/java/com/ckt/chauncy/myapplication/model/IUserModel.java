package com.ckt.chauncy.myapplication.model;

import android.content.Context;

/**
 * Created by ckt on 16-3-29.
 */
public interface IUserModel {
    void register(Context context,String username, String passwd, String email, String birth);
    void setOnRegisterListener(UserModel.OnRegisterListener mOnRegisterListener);

    void login(Context mContext, String username, String passwd);

    void setOnLoginListener(UserModel.OnLoginListener onLoginListener);
}
