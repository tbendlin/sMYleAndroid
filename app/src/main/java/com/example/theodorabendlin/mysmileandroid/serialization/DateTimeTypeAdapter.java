package com.example.theodorabendlin.mysmileandroid.serialization;

import android.text.TextUtils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import org.joda.time.DateTime;

import java.io.IOException;

import static com.example.theodorabendlin.mysmileandroid.utils.DateTimeUtil.ISO_DATE_FORMAT;

public class DateTimeTypeAdapter extends TypeAdapter<DateTime> {

    @Override
    public DateTime read(JsonReader in) throws IOException {
        String dateString = in.toString();
        return TextUtils.isEmpty(dateString) ? null : DateTime.parse(dateString);
    }

    @Override
    public void write(JsonWriter out, DateTime value) throws IOException {
        out.value(value.toString(ISO_DATE_FORMAT));
    }
}
