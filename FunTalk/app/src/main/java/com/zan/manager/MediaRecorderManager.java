package com.zan.manager;

import android.media.MediaRecorder;

import com.zan.utils.FileUtils;

import java.io.IOException;

public class MediaRecorderManager {
    private static MediaRecorder mediaRecorder;
    private static MediaRecorderManager instance;

    private MediaRecorderManager() {
        // 私有化构造函数，防止外部实例化
    }

    public static MediaRecorderManager getInstance() {
        if (instance == null) {
            instance = new MediaRecorderManager();
        }
        return instance;
    }

    public void init() {
        if (mediaRecorder == null) {
            mediaRecorder = new MediaRecorder();
        }
        // 进行初始化的其他操作
        // 创建一个用于保存录音文件的临时文件
        // 设置MediaRecorder的相关参数，如音频来源、音频格式、音频编码器、采样率等
        // 调用MediaRecorder的start方法开始录音
        // 初始化MediaRecorder
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(FileUtils.getTempFilePath());  // 设置临时录音文件路径
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        // 调用MediaRecorder的stop方法停止录音
        // 保存录音文件到本地（例如，将录音文件移动到指定路径或进行上传等）
        try {
            if (mediaRecorder != null) {
                mediaRecorder.stop();
            }
        } catch (IllegalStateException e) {
            // 处理异常的逻辑
        }
        release();
    }

    private void release() {
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }
}
