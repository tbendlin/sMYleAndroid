package com.example.theodorabendlin.mysmileandroid.injection;

import com.example.theodorabendlin.mysmileandroid.SmyleApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 *  Module class for Dagger application injection
 */

@Module
public class ConfigModule {

    private SmyleApp application;

    public ConfigModule(SmyleApp application) { this.application = application; }

    @Provides
    @Singleton
    SmyleApp providesApplication() { return application; }
}
