package com.example.theodorabendlin.mysmileandroid.injection;

import android.content.SharedPreferences;

import com.example.theodorabendlin.mysmileandroid.database.DatabaseHelper;
import com.example.theodorabendlin.mysmileandroid.database.SharedPrefsHelper;
import com.example.theodorabendlin.mysmileandroid.database.UserProfileDbHelper;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Component class to define Dagger injection pattern globally for the application
 * Any class that requires a Dagger-injected object must be injected here
 */

@Component(modules = {DatabaseModule.class, ConfigModule.class, NetworkModule.class})
@Singleton
public interface BaseComponent {

    Gson getGson();

    SharedPreferences getSharedPreferences();

    SharedPrefsHelper getSharedPrefsHelper();

    DatabaseHelper getDatabaseHelper();

    UserProfileDbHelper getUserProfileDbHelper();

}
