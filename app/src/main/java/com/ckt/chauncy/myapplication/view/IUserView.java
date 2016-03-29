package com.ckt.chauncy.myapplication.view;

/**
 * Created by ckt on 16-3-29.
 */
public interface IUserView {
    void registerSuccess();
    void registerFailed(int i, String s);
    void loginSuccess();
    void loginFailed(int i, String s);

}
