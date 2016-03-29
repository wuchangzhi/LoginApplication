package com.ckt.chauncy.myapplication.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.ckt.chauncy.myapplication.HomeActivity;
import com.ckt.chauncy.myapplication.R;
import com.ckt.chauncy.myapplication.util.AESEncryptor;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                SystemClock.sleep(3000);
                if (sharedPreferences.getBoolean("login_status", false)) {
                    String login_name = sharedPreferences.getString("username", "");
                    String password = AESEncryptor.decrypt(sharedPreferences.getString("password", ""));
                    mUserPresenter.login(login_name, password);
                } else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }).start();
    }

    @Override
    public void loginSuccess() {
        super.loginSuccess();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFailed(int i, String s) {
        super.loginFailed(i, s);
        Log.d("test",i + " " + s);
        Toast.makeText(this,R.string.login_faild,Toast.LENGTH_LONG).show();

        SharedPreferences preferences = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("login_status",false);
        editor.apply();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
