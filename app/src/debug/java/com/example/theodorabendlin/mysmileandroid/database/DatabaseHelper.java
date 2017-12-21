package com.example.theodorabendlin.mysmileandroid.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.theodorabendlin.mysmileandroid.BuildConfig;
import com.example.theodorabendlin.mysmileandroid.SmyleApp;
import com.example.theodorabendlin.mysmileandroid.serialization.DateTimePersister;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.field.DataPersisterManager;
import com.j256.ormlite.support.ConnectionSource;
import static com.example.theodorabendlin.mysmileandroid.database.SharedPrefsHelper.SHARED_PREFS;

/**
 * Database helper class used to manage the creation and upgrading of your database. This class
 * also usually provides the DAOs used by the other classes
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final String TAG = DatabaseHelper.class.getSimpleName();

    /**
     * Constructor for db w/o encryption (dev)
     *
     * @param context Application context
     */
    public DatabaseHelper(Context context, String databaseName) {
        super(context, databaseName, null, BuildConfig.databaseVersion);
        try {
            DataPersisterManager.registerDataPersisters(DateTimePersister.getSingleton());
        } catch (Exception e) {
            Log.e(TAG, "onCreate registering DateTimePersister", e);
        }
    }

    /**
     * Clears the cached SharedPrefs data
     */
    private synchronized void clearedSharedPrefs() {
        try {
            SharedPrefsHelper.clearAllData(SmyleApp.get().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE));
            //SharedPrefsUtil.clearAllData(SmyleApp.get());
        } catch (IllegalStateException ignore) {
            Log.e(TAG, ignore.getMessage(), ignore);
        }
    }

    public synchronized void resetDatabase() {
        DatabaseTableUtil.dropTables(connectionSource, TAG);
        DatabaseTableUtil.setUpTables(connectionSource, TAG);
        clearedSharedPrefs();
    }

    public synchronized void resetDatabaseOnLogout() {
        DatabaseTableUtil.clearUserTable(connectionSource, TAG);
        clearedSharedPrefs();
    }


    /**
     * This is called when the database is first created. Usually you should call createTable statements here to
     * create the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        Log.i(TAG, "onCreate");
        DatabaseTableUtil.setUpTables(connectionSource, TAG);
    }

    /**
     * This is called when you application is upgraded and it has a higher version number. This allows
     * you to adjust teh various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        resetDatabase();
    }
}
