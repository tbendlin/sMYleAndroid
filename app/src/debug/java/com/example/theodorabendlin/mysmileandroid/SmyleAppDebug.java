package com.example.theodorabendlin.mysmileandroid;

import com.example.theodorabendlin.mysmileandroid.injection.ConfigModule;
import com.example.theodorabendlin.mysmileandroid.injection.DaggerBaseComponent;
import com.example.theodorabendlin.mysmileandroid.injection.DatabaseModule;
import com.example.theodorabendlin.mysmileandroid.injection.DebugNetworkModule;
import com.facebook.stetho.Stetho;

public class SmyleAppDebug extends SmyleApp {

    @Override
    public void onCreate() {
        super.onCreate();

        baseComponent = DaggerBaseComponent.builder()
                .configModule(new ConfigModule(this))
                .databaseModule(new DatabaseModule())
                .networkModule(new DebugNetworkModule())
                .build();

        Stetho.initializeWithDefaults(this);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());

    }
}
