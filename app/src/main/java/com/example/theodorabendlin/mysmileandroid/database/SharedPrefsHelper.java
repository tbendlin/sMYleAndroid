package com.example.theodorabendlin.mysmileandroid.database;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.theodorabendlin.mysmileandroid.models.UserProfile;

public class SharedPrefsHelper {

    public static final String SHARED_PREFS = "smyleSharedPrefs";
    private static final String CURRENT_UID = "CURRENT_UUID";

    private static final String DB_PW_KEY = "DB_PW_KEY";
    private static final String KEY_NOTIFICATION_BUNDLES = "notification_bundles";
    private static final String KEY_VERSION_CODE = "KEY_VERSION_CODE";

    private SharedPreferences sharedPrefs;

    private static String currentUserId;
    private int versionCode = 0;

    public SharedPrefsHelper(SharedPreferences sharedPrefs) {
        this.sharedPrefs = sharedPrefs;
    }

    /**
     * Saves user ID to shared prefs
     * @param user -- the logged in user
     */
    public synchronized void setCurrentUser(UserProfile user) {
        setUserId(user.getUuid());
    }

    /**
     * Retrieves user ID from shared prefs
     */
    public synchronized String getUserId() {
        if (TextUtils.isEmpty(currentUserId)) {
            currentUserId = sharedPrefs.getString(CURRENT_UID, "");
        }
        return currentUserId;
    }

    public synchronized void setUserId(String userId) {
        currentUserId = userId;
        sharedPrefs.edit().putString(CURRENT_UID, userId).apply();
    }

    /**
     * clears the saved NotificationBundles
     */
    public void removeAllNotificationBundles() {
        sharedPrefs.edit().remove(KEY_NOTIFICATION_BUNDLES).apply();
    }

    /**
     * Function to clear all data from shared prefs
     * Leave DB key
     */
    public static synchronized void clearAllData(SharedPreferences sharedPrefs) {
        String dbKey = sharedPrefs.getString(DB_PW_KEY, null);
        String lastLoginUser = sharedPrefs.getString(CURRENT_UID, null);
        int versionCode =  sharedPrefs.getInt(KEY_VERSION_CODE, 0);

        sharedPrefs.edit().clear().commit();
        sharedPrefs.edit().putInt(KEY_VERSION_CODE, versionCode).commit();
        if (!TextUtils.isEmpty(dbKey)) {
            sharedPrefs.edit().putString(DB_PW_KEY, dbKey).commit();
        }
        if (!TextUtils.isEmpty(lastLoginUser)) {
            sharedPrefs.edit().putString(CURRENT_UID, lastLoginUser).commit();
        }
    }
}
