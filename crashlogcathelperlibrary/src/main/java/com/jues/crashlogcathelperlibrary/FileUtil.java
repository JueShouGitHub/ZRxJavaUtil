package com.jues.crashlogcathelperlibrary;

import android.os.Environment;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio .
 * 作者：zhong
 * 日期：2018/6/9
 * 邮箱：15206394364@163.com
 * 介绍：
 * 修订：====================
 */
class FileUtil {

    public static void delete(String path, FilenameFilter filter) {
        List<String> fileNameList = new ArrayList<>();
        File file = new File(path);
        new Thread(() -> {
            if (!file.isDirectory()) fileNameList.add(file.getName());
            for(String name : fileNameList){
                if(filter.accept(null, name)){
                    File file1 = new File(name);
                    file1.delete();
                }
            }
        }).start();

    }

    public static String getFileNameWithoutExtension(String fileName) {
        String[] nameSplit = fileName.split(".");
        return nameSplit[0];
    }

    /**
     * SD卡是否存在
     *
     * @return 是否存在
     */
    public static boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
