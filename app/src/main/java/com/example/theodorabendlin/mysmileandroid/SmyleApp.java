package com.example.theodorabendlin.mysmileandroid;

import android.app.Application;

import com.example.theodorabendlin.mysmileandroid.injection.BaseComponent;
import com.example.theodorabendlin.mysmileandroid.injection.ConfigModule;
import com.example.theodorabendlin.mysmileandroid.injection.DaggerBaseComponent;
import com.example.theodorabendlin.mysmileandroid.injection.DatabaseModule;
import com.example.theodorabendlin.mysmileandroid.injection.NetworkModule;

import net.danlew.android.joda.JodaTimeAndroid;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 *      Application file
 */

public class SmyleApp extends Application {

    protected BaseComponent baseComponent;

    private static SmyleApp application;

    public SmyleApp() {
        synchronized (this) {
            application = this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto_Condensed/RobotoCondensed-Regular.tff")
                .setFontAttrId(R.attr.fontPath)
                .build());

        baseComponent = DaggerBaseComponent.builder()
                .configModule(new ConfigModule(this))
                .databaseModule(new DatabaseModule())
                .networkModule(new NetworkModule())
                .build();
    }

    /**
     * Gets an instance of the app
     *
     * @return {@link SmyleApp}
     */
    public static synchronized SmyleApp get() {
        return application;
    }

    private BaseComponent getComponent() {
        return baseComponent;
    }

    public static BaseComponent getSmyleComponent() {
        return application.getComponent();
    }
}
