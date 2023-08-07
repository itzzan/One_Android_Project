package com.zan.funtalk;

import android.Manifest;
import android.graphics.Color;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.zan.manager.MediaRecorderManager;
import com.zan.view.MyScrollView;

import java.util.Locale;

public class AiSpeechActivity extends AppCompatActivity {

    private MyScrollView scrollView;

    private LinearLayout chatLayout;

    private TextView translateText;

    private Button translateBtn, broadcastBtn, aiLongSpeechBtn;

    private TextToSpeech textToSpeech;

    private RelativeLayout longLayout, gameTipsLayout, backgroundLayout;

    private LinearLayout releaseRecognitionLayout, closeReleaseLayout;

    private ImageView duringRecording;

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};

    private MediaRecorder mediaRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_speech);

        //初始化
        longLayout = findViewById(R.id.long_layout);
        gameTipsLayout = findViewById(R.id.game_tips_layout);
        releaseRecognitionLayout = findViewById(R.id.release_recognition_layout);
        closeReleaseLayout = findViewById(R.id.close_release_layout);
        duringRecording = findViewById(R.id.during_recording);
        backgroundLayout = findViewById(R.id.background_layout);

        chatLayout = findViewById(R.id.chatLayout);

        //ChatGPT生成的文字
        String generatedText = "It was really a wonderful journey !\n" +
                "What do you think is the most memorable thing for you?";

        TextView textView = new TextView(this);
        textView.setText(generatedText);
        //设置大小
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(16, 13, 16, 26); // 设置边距

        textView.setLayoutParams(layoutParams);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(24);

        chatLayout.addView(textView);

        scrollView = findViewById(R.id.AiScrollView);
        scrollView.setMaxHeight(450);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN); // 滚动到底部
            }
        });

        //初始化文字转语音tts
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.US); // 设置语言为英语
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This Language is not supported");
                    }
                } else {
                    Log.e("TTS", "Initialization Failed");
                }
            }
        });

        //按钮点击事件
        translateBtn = findViewById(R.id.translate_button);
        translateText = findViewById(R.id.translate_text);
        //翻译
        translateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //搞个翻译的工具类 - 用接口好了
                if (translateText.getVisibility() == View.INVISIBLE) {
                    //如果是未显示的，将其显示
                    translateText.setText("旅程真是精彩！你在这段旅程中有什么难忘的事情吗？");
                    translateText.setVisibility(View.VISIBLE);
                } else if (translateText.getVisibility() == View.VISIBLE) {
                    //取消显示
                    translateText.setText("");
                    translateText.setVisibility(View.INVISIBLE);
                }
            }
        });

        //语音
        broadcastBtn = findViewById(R.id.broadcast_button);
        broadcastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.setSpeechRate(0.9f); //语速
                textToSpeech.setPitch(1.2f); //音调
                speak(generatedText);
            }
        });

        //长按录音
        // 请求录音权限
        MediaRecorderManager manager = MediaRecorderManager.getInstance();
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
        aiLongSpeechBtn = findViewById(R.id.ai_long_speechBtn);

        aiLongSpeechBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        manager.init();
                        showPressedState();
                        return true;
                    case MotionEvent.ACTION_UP:
                        hidePressedState();
                        manager.stop();
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    private void showPressedState() {
        // 设置按住说话按钮的按下状态，例如改变按钮背景颜色或显示一个气泡动画等
        longLayout.setVisibility(View.INVISIBLE);
        gameTipsLayout.setVisibility(View.INVISIBLE);
        releaseRecognitionLayout.setVisibility(View.VISIBLE);
        closeReleaseLayout.setVisibility(View.VISIBLE);
        duringRecording.setVisibility(View.VISIBLE);
        backgroundLayout.setVisibility(View.VISIBLE);
    }

    private void hidePressedState() {
        // 恢复按住说话按钮的初始状态，例如恢复按钮的背景颜色或隐藏气泡动画等
        longLayout.setVisibility(View.VISIBLE);
        gameTipsLayout.setVisibility(View.VISIBLE);
        releaseRecognitionLayout.setVisibility(View.INVISIBLE);
        closeReleaseLayout.setVisibility(View.INVISIBLE);
        duringRecording.setVisibility(View.INVISIBLE);
        backgroundLayout.setVisibility(View.INVISIBLE);
    }

    //语音输出
    private void speak(String text) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null);
    }

}