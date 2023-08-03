package com.zan.funtalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button startBtn, closeBtn, settingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.index_startBtn);
        closeBtn = findViewById(R.id.index_closeBtn);
        settingBtn = findViewById(R.id.index_settingBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "开始游戏", Toast.LENGTH_SHORT).show();
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "结束游戏", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void settingClick(View view) {
        Intent intent = new Intent(this, IndexSettingActivity.class);
        startActivity(intent);
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "设置", Toast.LENGTH_SHORT).show();
            }
        });
    }
}