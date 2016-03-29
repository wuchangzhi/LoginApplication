package com.ckt.chauncy.myapplication.view;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by ckt on 16-3-29.
 */
public class MainApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this,"95a4aadacdcb20b1ac9770a11b039ff9");
    }
}
