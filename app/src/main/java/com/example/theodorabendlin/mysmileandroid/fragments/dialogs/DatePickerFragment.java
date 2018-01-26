package com.example.theodorabendlin.mysmileandroid.fragments.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.example.theodorabendlin.mysmileandroid.R;

import org.joda.time.DateTime;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private static final int MIN_PLUS_DAY = 1;
    private static final int MAX_PLUS_DAY = 14;
    private static final int MIN_HOUR_MILLIS = 28800000;
    private static final int DEFAULT_MAX_HOUR_MILLIS = 72000000;
    private static final int LATE_HOUR_MILLIS = 79200000;
    private static final int MAX_LATE_HOUR_MILLIS = 86340000;
    private static final int ONE_HOUR_MILLIS = 3600000;

    private DateSetCallback callback;

    public static DatePickerFragment newInstance(DateSetCallback callback) {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.callback = callback;
        return datePickerFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DateTime currentDate = DateTime.now();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, currentDate.getYear(),
                                        currentDate.getMonthOfYear() - 1, currentDate.getDayOfMonth());
        datePickerDialog.getDatePicker().setMinDate(DateTime.now().getMillis());
        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL, getString(R.string.random), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DateTime randomDate = DateTime.now().plusDays(randomInRange(MIN_PLUS_DAY, MAX_PLUS_DAY));
                randomDate = randomDate.withMillisOfDay(randomMillis(randomDate));
                callback.onDateSet(randomDate);
            }
        });
        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        DateTime chosenDate = new DateTime().withDate(year, month + 1, day);
        chosenDate = chosenDate.withMillisOfDay(randomMillis(chosenDate));
        callback.onDateSet(chosenDate);
    }

    /**
     * Calculates a random time for the compliment to occur
     * Rules:
     * 1. If the date is not for today, return a time between 8am and 8pm
     * 2. If the date is for today, try to choose a time before 8pm. If time
     * is close to 8, choose a time before 10. If time is close to 10, choose before 12am
     *
     * A fun idea for later would be for people to create their own rules for the time
     * @return random millis for the given date
     */
    private int randomMillis(DateTime chosenDate) {
        DateTime today = DateTime.now();
        if (today.isBefore(chosenDate)) {
            return randomInRange(MIN_HOUR_MILLIS, DEFAULT_MAX_HOUR_MILLIS);
        } else {
            int currentDayMillis = today.getMillisOfDay();
            int nextHourDayMillis = currentDayMillis + ONE_HOUR_MILLIS;
            if (currentDayMillis < MIN_HOUR_MILLIS || nextHourDayMillis < DEFAULT_MAX_HOUR_MILLIS) {
                return randomInRange(nextHourDayMillis, DEFAULT_MAX_HOUR_MILLIS);
            } else if (nextHourDayMillis < LATE_HOUR_MILLIS) {
                return randomInRange(nextHourDayMillis, LATE_HOUR_MILLIS);
            } else if (nextHourDayMillis < MAX_LATE_HOUR_MILLIS) {
                return randomInRange(nextHourDayMillis, MAX_LATE_HOUR_MILLIS);
            } else {
                return randomInRange(currentDayMillis, MAX_LATE_HOUR_MILLIS);
            }
        }
    }

    /**
     * Helper function that returns a random number in the range (minimum, maximum]
     */
    private int randomInRange(int minimum, int maximum) {
        int difference = Math.abs(maximum - minimum) + 1;
        return (int) (Math.random() * difference) + minimum;
    }

    public interface DateSetCallback {
        void onDateSet(DateTime dateTime);
    }
}
