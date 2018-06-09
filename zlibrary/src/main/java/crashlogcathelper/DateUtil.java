package crashlogcathelper;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Android Studio .
 * 作者：zhong
 * 日期：2018/6/9
 * 邮箱：15206394364@163.com
 * 介绍：
 * 修订：====================
 */
class DateUtil {
    public static String getOtherDay(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        @SuppressLint("SimpleDateFormat")
        DateFormat mFileDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return mFileDateFormat.format(now.getTime());
    }
}
