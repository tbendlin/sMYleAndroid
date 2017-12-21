package com.example.theodorabendlin.mysmileandroid.database;

import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * DatabaseConfigUtil writes a configuration file to avoid using annotation processing in runtime
 * which is very slow under Android. This gains a noticable performance improvement.
 *
 * The configuration file is written to /res/raw/ by default
 */

public class DatabaseConfigUtil extends OrmLiteConfigUtil {

    private static final String TAG = DatabaseConfigUtil.class.getSimpleName();

    public static void main(String[] args) {
        try {
            writeConfigFile("ormlite_config.txt");
        } catch (SQLException | IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
}
