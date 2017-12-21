package com.example.theodorabendlin.mysmileandroid.injection;

import com.example.theodorabendlin.mysmileandroid.SmyleApp;
import com.example.theodorabendlin.mysmileandroid.serialization.DateTimeTypeAdapter;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dagger Singleton Module to define network based dependencies for all possible injected objects.
 * Mainly used for complex (multi-requirement) built objects, i.e. a Retrofit API call
 */

@Module
public class NetworkModule {

    @Singleton
    @Provides
    Gson providesGson() {
        return new GsonBuilder()
                .registerTypeAdapter(DateTime.class, new DateTimeTypeAdapter().nullSafe())
                .create();
    }

    @Singleton
    @Provides
    OkHttpClient providesOkHttpClient(SmyleApp application) {
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(application));
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cookieJar(cookieJar);
        return builder.build();
    }

    /*@Singleton
    @Provides
    BackendService providesBackendClient(Gson gson, OkHttpClient okHttpClient, SmyleApp application) {
        return new Retrofit.Builder()
                .baseUrl(application.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .callAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(BackendService.class);
    }*/
}
