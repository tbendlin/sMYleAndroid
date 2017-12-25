package com.example.theodorabendlin.mysmileandroid.utils;

import android.content.Context;
import android.text.format.DateUtils;

import org.joda.time.DateTime;

public class DateFormatUtil {

    public static String formatDate(Context context, DateTime dateTime) {
        return DateUtils.formatDateTime(context, dateTime.getMillis(), DateUtils.FORMAT_ABBREV_MONTH | DateUtils.FORMAT_SHOW_YEAR);
    }
}
