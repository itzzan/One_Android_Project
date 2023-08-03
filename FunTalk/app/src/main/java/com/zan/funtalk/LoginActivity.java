package com.zan.funtalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.loginBtn);

    }

    public void tiaozhuan(View view) {
        Intent intent = new Intent(this, MainActivity.class); //第一个是自己的activity，第二个参数是要跳转的activity
        this.startActivity(intent);
    }
}