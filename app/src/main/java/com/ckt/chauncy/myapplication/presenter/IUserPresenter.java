package com.ckt.chauncy.myapplication.presenter;

import android.content.Context;

import com.ckt.chauncy.myapplication.view.IUserView;

/**
 * Created by ckt on 16-3-29.
 */
public interface IUserPresenter {
    void attach(Context context,IUserView userView);

    void register(String username, String passwd, String email, String birth);

    void login(String username, String passwd);
}
