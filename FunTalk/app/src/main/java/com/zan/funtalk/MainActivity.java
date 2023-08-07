package com.zan.funtalk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button startGameBtn, closeGameBtn, settingBtn;

    private Dialog settingsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGameBtn = findViewById(R.id.index_startBtn);
        closeGameBtn = findViewById(R.id.index_closeBtn);
        settingBtn = findViewById(R.id.index_settingBtn);

        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AiSpeechActivity.class);
                startActivity(intent);
            }
        });

        // 创建一个Dialog对象
        settingsDialog = new Dialog(MainActivity.this);
        // 设置对话框布局
        settingsDialog.setContentView(R.layout.activity_index_setting);
        setDialogMatchParent(settingsDialog);

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSettingsDialog();
            }
        });
    }

    private void showSettingsDialog() {
        // 找到对话框中的控件，并添加相应的功能
        ImageButton closeButton = settingsDialog.findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsDialog.dismiss(); // 关闭对话框
            }
        });

        ImageButton chineseBtn = settingsDialog.findViewById(R.id.chineseBtn);
        ImageButton englishBtn = settingsDialog.findViewById(R.id.englishBtn);

        ViewGroup.LayoutParams chineseBtnParams = chineseBtn.getLayoutParams();
        ViewGroup.LayoutParams englishBtnParams = englishBtn.getLayoutParams();

        chineseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //中英文转化 - dp 转换成 int
                chineseBtnParams.width = ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics()));
                chineseBtnParams.height = ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 67, getResources().getDisplayMetrics()));
                englishBtnParams.width = ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics()));
                englishBtnParams.height = ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 54, getResources().getDisplayMetrics()));

                chineseBtn.setBackground(getDrawable(R.mipmap.chinese_select));
                englishBtn.setBackground(getDrawable(R.mipmap.english));
                chineseBtn.setLayoutParams(chineseBtnParams);
                englishBtn.setLayoutParams(englishBtnParams);
            }
        });
        englishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chineseBtnParams.width = ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics()));
                chineseBtnParams.height = ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 54, getResources().getDisplayMetrics()));
                englishBtnParams.width = ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics()));
                englishBtnParams.height = ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 67, getResources().getDisplayMetrics()));

                chineseBtn.setBackground(getDrawable(R.mipmap.chinese));
                englishBtn.setBackground(getDrawable(R.mipmap.english_select));
                chineseBtn.setLayoutParams(chineseBtnParams);
                englishBtn.setLayoutParams(englishBtnParams);
            }
        });

        SeekBar musicSeekBar = settingsDialog.findViewById(R.id.music_seek_bar);
        TextView musicText = settingsDialog.findViewById(R.id.music_text);
        musicSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 音乐音量调节的处理逻辑
                seekBar.setProgress(progress);
                musicText.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        SeekBar soundEffectSeekBar = settingsDialog.findViewById(R.id.sound_effect_seek_bar);
        TextView soundEffectText = settingsDialog.findViewById(R.id.sound_effect_text);
        soundEffectSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 音效音量调节的处理逻辑
                seekBar.setProgress(progress);
                soundEffectText.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // 显示对话框
        settingsDialog.show();
    }

    /**
     * 设置对话框全屏
     */
    public void setDialogMatchParent(Dialog dialog) {
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;// 屏幕宽度（像素）
        int height = dm.heightPixels; // 屏幕高度（像素）
        float density = dm.density;//屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;//屏幕密度dpi（120 / 160 / 240）
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        //这个地方可以用ViewGroup.LayoutParams.MATCH_PARENT属性，各位试试看看有没有效果
        Display display = getWindowManager().getDefaultDisplay();
        layoutParams.width = (int) (display.getWidth() * 1);
        layoutParams.height = (int) (display.getHeight() * 1);
//        layoutParams.width = width;
//        layoutParams.height = height;
        layoutParams.alpha = 0.95f;
        dialog.getWindow().setAttributes(layoutParams);
    }
}