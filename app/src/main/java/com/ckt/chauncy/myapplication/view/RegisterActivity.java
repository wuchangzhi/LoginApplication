package com.ckt.chauncy.myapplication.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ckt.chauncy.myapplication.R;
import com.ckt.chauncy.myapplication.util.ToolsUtil;

/**
 * Created by ckt on 16-3-29.
 */
public class RegisterActivity extends BaseActivity {
    private EditText username, passwd,confirm_passwd, email, birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        init();
    }

    private void init() {
        username = (EditText) findViewById(R.id.username_reg);
        passwd = (EditText) findViewById(R.id.password_reg);
        confirm_passwd = (EditText) findViewById(R.id.confirm_passwd_reg);
        email = (EditText) findViewById(R.id.email_reg);
        birth = (EditText) findViewById(R.id.birth_reg);

    }

    public void register(View view) {
        if(passwd.getText().toString().trim().equals(confirm_passwd.getText().toString().trim())){
            mUserPresenter.register(username.getText().toString(),
                    ToolsUtil.getMD5(passwd.getText().toString()),
                    email.getText().toString(),
                    birth.getText().toString());
        }else {
            Toast.makeText(this,R.string.confirm_fail,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void registerSuccess() {
        super.registerSuccess();
        Toast.makeText(this,R.string.save_success,Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void registerFailed(int i, String s) {
        super.registerFailed(i, s);
        Log.d("test",i + " " + s);
        if(i == 304){
            Toast.makeText(this,R.string.is_not_null,Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(this,R.string.save_fail,Toast.LENGTH_LONG).show();
        finish();
    }
}