package com.zan.funtalk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn, registerBtn;

    private Dialog settingsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);

    }

    public void tiaozhuan(View view) {
        Intent intent = new Intent(this, MainActivity.class); //第一个是自己的activity，第二个参数是要跳转的activity
        this.startActivity(intent);
    }

    public void tiaozhuan2(View view) {
        Intent intent = new Intent(this, LoadingActivity.class); //第一个是自己的activity，第二个参数是要跳转的activity
        this.startActivity(intent);
    }
}