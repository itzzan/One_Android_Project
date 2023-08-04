package com.zan.funtalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        ImageView img_gif = findViewById (R.id.img_gif);
        //调用音频
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.loading_music);
        mediaPlayer.start();
        //如果系统版本为Android9.0以上,则利用新增的AnimatedImageDrawable显示GIF动画
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            try {
                //利用Android9.0新增的ImageDecoder读取gif动画
                ImageDecoder.Source source = ImageDecoder.createSource(getResources(), R.drawable.loading2);
                //从数据源中解码得到gif图形数据
                Drawable drawable = ImageDecoder.decodeDrawable(source);
                //设置图像视图的图形为gif图片
                img_gif.setImageDrawable(drawable);
                //如果是动画图形，则开始播放动画
                if (drawable instanceof Animatable) {
                    Animatable animatable = (Animatable) img_gif.getDrawable();
                    animatable.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //在指定时间后跳转到另一个页面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 跳转到另一个页面的代码
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(intent);
                mediaPlayer.stop();
                mediaPlayer.release();
                finish(); // 可选，根据需求决定是否关闭当前页面
            }
        }, 4000);
    }
}