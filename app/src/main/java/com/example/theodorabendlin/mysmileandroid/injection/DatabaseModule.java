package com.example.theodorabendlin.mysmileandroid.injection;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.theodorabendlin.mysmileandroid.R;
import com.example.theodorabendlin.mysmileandroid.SmyleApp;
import com.example.theodorabendlin.mysmileandroid.database.DatabaseHelper;
import com.example.theodorabendlin.mysmileandroid.database.SharedPrefsHelper;
import com.example.theodorabendlin.mysmileandroid.database.UserProfileDbHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.example.theodorabendlin.mysmileandroid.database.SharedPrefsHelper.SHARED_PREFS;

@Module
public class DatabaseModule {

    @Singleton
    @Provides
    public DatabaseHelper providesDatabaseHelper(SmyleApp application) {
        return new DatabaseHelper(application, application.getString(R.string.databaseName));
    }

    @Singleton
    @Provides
    public UserProfileDbHelper providesUserProfileDbHelper(DatabaseHelper databaseHelper, SharedPrefsHelper sharedPrefsHelper) {
        return new UserProfileDbHelper(databaseHelper, sharedPrefsHelper);
    }

    @Singleton
    @Provides
    public SharedPreferences providesSharedPreferences(SmyleApp app) {
        return app.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    public SharedPrefsHelper providesSharedPrefsHelper(SharedPreferences sharedPreferences) {
        return new SharedPrefsHelper(sharedPreferences);
    }
}
