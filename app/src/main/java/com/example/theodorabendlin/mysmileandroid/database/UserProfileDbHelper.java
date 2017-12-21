package com.example.theodorabendlin.mysmileandroid.database;

import android.util.Log;

import com.example.theodorabendlin.mysmileandroid.models.UserProfile;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;

public class UserProfileDbHelper {

    private static final String TAG = UserProfileDbHelper.class.getSimpleName();
    private static final String BUNDLE_ID_DB_COL = "summaryBundleId";
    private static final String USER_PROF_DB_COL = "userProfile_id";

    private DatabaseHelper databaseHelper;
    private SharedPrefsHelper sharedPrefsHelper;

    public UserProfileDbHelper(DatabaseHelper databaseHelper, SharedPrefsHelper sharedPrefsHelper) {
        this.databaseHelper = databaseHelper;
        this.sharedPrefsHelper = sharedPrefsHelper;
    }

    public synchronized UserProfile getCurrentUserProfile() {
        return getUserProfileById(sharedPrefsHelper.getUserId());
    }

    private synchronized UserProfile getUserProfileById(String uuid) {
        UserProfile currentUserProfile = null;
        try {
            Dao<UserProfile, ?> userProfileDao = databaseHelper.getDao(UserProfile.class);
            QueryBuilder<UserProfile, ?> userProfileQueryBuilder = userProfileDao.queryBuilder();

            userProfileQueryBuilder.where().eq("uuid", uuid);
            currentUserProfile = userProfileDao.queryForFirst(userProfileQueryBuilder.prepare());
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return currentUserProfile;
    }
}
