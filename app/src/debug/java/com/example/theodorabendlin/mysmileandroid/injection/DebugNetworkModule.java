package com.example.theodorabendlin.mysmileandroid.injection;

import com.example.theodorabendlin.mysmileandroid.SmyleApp;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class DebugNetworkModule extends NetworkModule {

    @Override
    OkHttpClient providesOkHttpClient(SmyleApp application) {
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(application));
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addNetworkInterceptor((Interceptor) new StethoInterceptor());

        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        builder.addInterceptor(interceptor);

        builder.cookieJar(cookieJar);

        return builder.build();
    }
}
