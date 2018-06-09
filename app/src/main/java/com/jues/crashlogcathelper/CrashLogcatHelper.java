package com.jues.crashlogcathelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Android Studio .
 * 作者：zhong
 * 日期：2018/6/9
 * 邮箱：15206394364@163.com
 * 介绍：<h3>全局捕获异常</h3>
 * 当程序发生Uncaught异常的时候,有该类来接管程序,并记录错误日志
 * 修订：====================
 */
public class CrashLogcatHelper implements Thread.UncaughtExceptionHandler {
    private static String TAG = "CrashLogcatHelper";
    private static CrashLogcatHelper mLelper;
    //保存文件的名称
    private static String PATH_LOGCAT = "Crash";
    //保存天数
    private static int saveDays = 7;
    private static boolean isDebug;
    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;
    private Context mContext;
    //存储设备信息和异常信息
    private Map<String, String> mStringMap = new HashMap<>();
    @SuppressLint("SimpleDateFormat")
    private DateFormat mErrorDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @SuppressLint("SimpleDateFormat")
    private DateFormat mFileDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private CrashLogcatHelper() {
    }

    public static CrashLogcatHelper getInstance() {
        if (mLelper == null) mLelper = new CrashLogcatHelper();
        return mLelper;
    }

    private static String getGlobalPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + PATH_LOGCAT + File.separator;
    }

    public static void setTag(String tag) {
        TAG = tag;
    }

    public static void setDays(int days) {
        saveDays = days;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context, String fileName, boolean debugOpen) {
        this.mContext = context;
        PATH_LOGCAT = fileName;
        isDebug = debugOpen;
        // 获取系统默认的UncaughtException处理器
        mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
        autoClear(saveDays);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultExceptionHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultExceptionHandler.uncaughtException(thread, ex);
        } else {
            SystemClock.sleep(3000);
            // 退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息; 否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) return false;
        try {
            // 使用Toast来显示异常信息
            new Thread(() -> {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉,程序出现异常,即将退出.",
                        Toast.LENGTH_LONG).show();
                Looper.loop();
            }).start();
            if (isDebug) {
                // 收集设备参数信息
                collectDeviceInfo(mContext);
                // 保存日志文件
                saveCrashInfoFile(ex);
            }
            SystemClock.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    private void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName + "";
                String versionCode = pi.versionCode + "";
                mStringMap.put("versionName", versionName);
                mStringMap.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                mStringMap.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     * @throws Exception
     */
    private String saveCrashInfoFile(Throwable ex) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n======================================================\n")
                .append("ErrorFile:").append(TAG).append("\n");
        try {
            String date = mErrorDateFormat.format(new Date());
            sb.append("\r\n").append(date).append("\n");
            for (Map.Entry<String, String> entry : mStringMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                sb.append(key).append("=").append(value).append("\n");
            }

            Writer writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            ex.printStackTrace(printWriter);
            Throwable cause = ex.getCause();
            while (cause != null) {
                cause.printStackTrace(printWriter);
                cause = cause.getCause();
            }
            printWriter.flush();
            printWriter.close();
            String result = writer.toString();
            sb.append(result);
            sb.append("======================================================\n");
            return writeFile(sb.toString());
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
            sb.append("an error occured while writing file...\r");
            sb.append("======================================================\n");
            writeFile(sb.toString());
        }
        return null;
    }

    private String writeFile(String sb) throws Exception {
        String time = mFileDateFormat.format(new Date());
        String fileName = "crash-" + time + ".log";
        if (FileUtil.hasSdcard()) {
            String path = getGlobalPath();
            File dir = new File(path);
            if (!dir.exists()) dir.mkdirs();
            FileOutputStream fos = new FileOutputStream(path + fileName, true);
            fos.write(sb.getBytes());
            fos.flush();
            fos.close();
        }
        return fileName;
    }

    private void autoClear(final int autoClearDay) {
        FileUtil.delete(getGlobalPath(), new FilenameFilter() {
            @Override
            public boolean accept(File file, String filename) {
                String s = FileUtil.getFileNameWithoutExtension(filename);
                int day = autoClearDay >= 0 ? autoClearDay : -1 * autoClearDay;
                String date = "crash-" + DateUtil.getOtherDay(new Date(), day);
                return date.compareTo(s) >= 0;
            }
        });
    }
}
