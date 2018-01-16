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
        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.random), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int difference = Math.abs(MAX_PLUS_DAY - MIN_PLUS_DAY) + 1;
                int offset = (int) (Math.random() * difference) + MIN_PLUS_DAY;
                callback.onDateSet(DateTime.now().plusDays(offset));
            }
        });
        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        callback.onDateSet(new DateTime().withDate(year, month + 1, day));
    }

    public interface DateSetCallback {
        void onDateSet(DateTime dateTime);
    }
}
