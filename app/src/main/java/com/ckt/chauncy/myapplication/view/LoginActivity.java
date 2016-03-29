package com.ckt.chauncy.myapplication.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ckt.chauncy.myapplication.HomeActivity;
import com.ckt.chauncy.myapplication.R;
import com.ckt.chauncy.myapplication.util.AESEncryptor;
import com.ckt.chauncy.myapplication.util.ToolsUtil;

public class LoginActivity extends BaseActivity {
    private EditText username,passwd;
    private SharedPreferences mSharedPre;
    private Boolean loginStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();

        String login_name = mSharedPre.getString("username","");
        Log.d("test",mSharedPre.getString("password",""));
        String password = AESEncryptor.decrypt(mSharedPre.getString("password",""));
        loginStatus = mSharedPre.getBoolean("login_status",false);

        if(username != null){
            username.setText(login_name);
        }
        if(password != null){
            passwd.setText(password);
        }
    }

    private void init() {
        mSharedPre = getSharedPreferences("login",MODE_PRIVATE);
        username = (EditText) findViewById(R.id.username);
        passwd = (EditText) findViewById(R.id.password);

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwd.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void register(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View view){
        mUserPresenter.login(username.getText().toString(), ToolsUtil.getMD5(passwd.getText().toString()));
    }

    @Override
    public void loginSuccess() {
        super.loginSuccess();
        Intent intent = new Intent(this, HomeActivity.class);

        SharedPreferences.Editor editor = mSharedPre.edit();
        editor.putString("username",username.getText().toString());
        editor.putString("password",AESEncryptor.encrypt(passwd.getText().toString()));
        editor.putBoolean("login_status",true);
        editor.apply();
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFailed(int i, String s) {
        super.loginFailed(i, s);
        Log.d("test",i + " " + s);
        passwd.setText("");
        Toast.makeText(this,R.string.login_faild,Toast.LENGTH_LONG).show();
    }
}
