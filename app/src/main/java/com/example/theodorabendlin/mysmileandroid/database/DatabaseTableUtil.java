package com.example.theodorabendlin.mysmileandroid.database;

import android.util.Log;

import com.example.theodorabendlin.mysmileandroid.models.UserProfile;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 *  File that keeps the table set up for teh app in one place
 */

public class DatabaseTableUtil {

    /**
     * List of all database model classes
     */
    private static Class[] tableClasses = new Class[]{
        UserProfile.class
    };

    private DatabaseTableUtil() { /* Default constructor */ }

    /**
     *  Simple function to build tables for app
     */
    public static void setUpTables(ConnectionSource connectionSource, String tag) {
        try {
            for (Class clazz : tableClasses) {
                TableUtils.dropTable(connectionSource, clazz, false);
            }
        } catch (SQLException e) {
            Log.e(tag, e.getMessage(), e);
        }
    }

    /**
     * Drops all tables in database
     */
    public static void dropTables(ConnectionSource connectionSource, String tag) {
        try {
            for (Class clazz : tableClasses) {
                TableUtils.dropTable(connectionSource, clazz, false);
            }
        } catch (SQLException e) {
            Log.e(tag, e.getMessage(), e);
        }
    }

    /**
     *  Clears all data in the UserProfile database table
     */
    public static void clearUserTable(ConnectionSource connectionSource, String tag) {
        try {
            TableUtils.clearTable(connectionSource, UserProfile.class);
        } catch (SQLException e) {
            Log.e(tag, e.getMessage(), e);
        }
    }
}
