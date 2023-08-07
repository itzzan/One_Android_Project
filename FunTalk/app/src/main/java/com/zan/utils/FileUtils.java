package com.zan.utils;

public class FileUtils {

    //获取文件路径
    public static String getTempFilePath() {

        String filePath = "/storage/emulated/0/Android/data/com.zan.funtalk/cache/";
        filePath += "/temp.mp3";
//        Log.e("file", filePath); // E/file: /storage/emulated/0/Android/data/com.zan.funtalk/cache/audio.3gp
        return filePath;
    }
}
