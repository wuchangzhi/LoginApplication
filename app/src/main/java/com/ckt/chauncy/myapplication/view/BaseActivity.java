package com.ckt.chauncy.myapplication.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ckt.chauncy.myapplication.presenter.IUserPresenter;
import com.ckt.chauncy.myapplication.presenter.UserPresenter;

/**
 * Created by ckt on 16-3-29.
 */
public abstract class BaseActivity extends AppCompatActivity implements IUserView{
    protected IUserPresenter mUserPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserPresenter = UserPresenter.getInstence();
        mUserPresenter.attach(this,this);
    }

    @Override
    public void registerSuccess() {
    }

    @Override
    public void registerFailed(int i, String s) {
    }

    @Override
    public void loginSuccess() {
    }

    @Override
    public void loginFailed(int i, String s) {
    }
}
