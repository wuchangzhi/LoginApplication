package com.ckt.chauncy.myapplication.model;

import android.content.Context;

import com.ckt.chauncy.myapplication.bean.User;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by ckt on 16-3-29.
 */
public class UserModel implements IUserModel {

    private OnRegisterListener mOnRegisterListener;
    private OnLoginListener mOnLoginListener;

    @Override
    public void register(Context context ,String username, String passwd, String email, String birth) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwd);
        user.setEmail(email);
        user.setBirth(birth);
        user.signUp(context,new SaveListener() {
            @Override
            public void onSuccess() {
                if(mOnRegisterListener != null){
                    mOnRegisterListener.onSuccess();
                }
            }

            @Override
            public void onFailure(int i, String s) {
                if(mOnRegisterListener != null){
                    mOnRegisterListener.onFailure(i,s);
                }
            }
        });
    }
    @Override
    public void login(Context mContext, String username, String passwd) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwd);
        user.login(mContext, new SaveListener() {
            @Override
            public void onSuccess() {
                if(mOnLoginListener != null){
                    mOnLoginListener.onSuccess();
                }
            }

            @Override
            public void onFailure(int i, String s) {
                if(mOnLoginListener != null){
                    mOnLoginListener.onFailure(i,s);
                }
            }
        });
    }


    public interface OnRegisterListener{
        void onSuccess();
        void onFailure(int i, String s);
    }

    public interface OnLoginListener{
        void onSuccess();
        void onFailure(int i, String s);
    }

    @Override
    public void setOnRegisterListener(OnRegisterListener mOnRegisterListener) {
        this.mOnRegisterListener = mOnRegisterListener;
    }
    @Override
    public void setOnLoginListener(OnLoginListener onLoginListener) {
        this.mOnLoginListener = onLoginListener;
    }

}
