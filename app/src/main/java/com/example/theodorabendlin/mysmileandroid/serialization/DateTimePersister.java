package com.example.theodorabendlin.mysmileandroid.serialization;

import android.text.TextUtils;
import android.util.Log;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.DateTimeType;
import com.j256.ormlite.support.DatabaseResults;

import org.joda.time.DateTime;

import java.lang.reflect.Field;
import java.sql.SQLException;

/**
 * A custom persister that is able to store the Joda {@link DateTime} class in the database as a
 * string. This overrides the {@link DateTimeType} which uses reflection instead. This should run faster.
 *
 */
public class DateTimePersister extends DateTimeType {

    private static final String TAG = DateTimePersister.class.getSimpleName();

    private static final DateTimePersister singleton = new DateTimePersister();

    private DateTimePersister() {
        super(SqlType.LONG, new Class<?>[] { DateTime.class });
    }

    public static DateTimePersister getSingleton() {
        return singleton;
    }

    @Override
    public Object javaToSqlArg(FieldType fieldType, Object javaObject) {
        DateTime dateTime = (DateTime) javaObject;
        if (dateTime == null) {
            return null;
        } else {
            return dateTime.toString();
        }
    }

    @Override
    public Object sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos) {
        return parseFromString((String) sqlArg);
    }

    @Override
    public Object resultToSqlArg(FieldType fieldType, DatabaseResults results, int columnPos) throws SQLException {
        return results.getString(columnPos);
    }

    @Override
    public boolean isValidForField(Field field) {
        return field.getType() == DateTime.class;
    }

    @Override
    public Object parseDefaultString(FieldType fieldType, String defaultString) {
        return parseFromString(defaultString);
    }

    private DateTime parseFromString(String dateString) {
        if (TextUtils.isEmpty(dateString)) {
            return null;
        }

        try {
            return DateTime.parse(dateString);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return null;
    }
}
