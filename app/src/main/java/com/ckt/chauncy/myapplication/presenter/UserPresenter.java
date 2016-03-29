package com.ckt.chauncy.myapplication.presenter;

import android.content.Context;

import com.ckt.chauncy.myapplication.model.IUserModel;
import com.ckt.chauncy.myapplication.model.UserModel;
import com.ckt.chauncy.myapplication.view.IUserView;

/**
 * Created by ckt on 16-3-29.
 */
public class UserPresenter implements IUserPresenter {
    private static UserPresenter instence;
    private Context mContext;
    private IUserView mUserView;
    private IUserModel mUserModel;

    private UserPresenter(){
        mUserModel = new UserModel();
    };

    public static IUserPresenter getInstence(){
        if(instence == null){
            instence = new UserPresenter();
        }
        return instence;
    }

    @Override
    public void attach(Context context, IUserView userView) {
        this.mContext = context;
        this.mUserView = userView;
    }

    @Override
    public void register(String username, String passwd, String email, String birth) {
        mUserModel.setOnRegisterListener(new UserModel.OnRegisterListener() {
            @Override
            public void onSuccess() {
                mUserView.registerSuccess();
            }

            @Override
            public void onFailure(int i, String s) {
                mUserView.registerFailed(i,s);
            }
        });
        mUserModel.register(mContext,username,passwd,email,birth);
    }

    @Override
    public void login(String username, String passwd) {
        mUserModel.setOnLoginListener(new UserModel.OnLoginListener() {
            @Override
            public void onSuccess() {
                mUserView.loginSuccess();
            }

            @Override
            public void onFailure(int i, String s) {
                mUserView.loginFailed(i,s);
            }
        });
        mUserModel.login(mContext,username,passwd);
    }
}
