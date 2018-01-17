package com.example.theodorabendlin.mysmileandroid.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.theodorabendlin.mysmileandroid.R;
import com.example.theodorabendlin.mysmileandroid.SmyleApp;
import com.example.theodorabendlin.mysmileandroid.activities.CreateUpdateComplimentActivity;
import com.example.theodorabendlin.mysmileandroid.fragments.dialogs.DatePickerFragment;
import com.example.theodorabendlin.mysmileandroid.models.Compliment;
import com.example.theodorabendlin.mysmileandroid.utils.TextWatcherUtil;

import org.joda.time.DateTime;

import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class ComplimentDetailFragment extends BaseFragment implements DatePickerFragment.DateSetCallback {

    private static final int MAX_CHARACTERS = 140;
    private static final int COMPLIMENT_ID_LENGTH = 20;

    @BindView(R.id.add_compliment_edit_text_layout) protected TextInputLayout complimentEditTextLayout;
    @BindView(R.id.add_compliment_edit_text) protected TextInputEditText complimentEditText;
    @BindView(R.id.add_compliment_month) protected EditText complimentMonth;
    @BindView(R.id.add_compliment_day) protected EditText complimentDay;
    @BindView(R.id.add_compliment_year) protected EditText complimentYear;
    @BindView(R.id.add_compliment_invalid_date) protected View invalidComplimentDate;
    @BindView(R.id.add_compliment_repeat_spinner) protected Spinner repeatSpinner;

    private Compliment compliment;

    public static ComplimentDetailFragment newInstance(String complimentId) {
        ComplimentDetailFragment complimentDetailFragment = new ComplimentDetailFragment();
        if (complimentId != null && !TextUtils.isEmpty(complimentId)) {
            Bundle args = new Bundle();
            args.putString(CreateUpdateComplimentActivity.KEY_COMPLIMENT_ID, complimentId);
            complimentDetailFragment.setArguments(args);
        }
        return complimentDetailFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            String complimentId = getArguments().getString(CreateUpdateComplimentActivity.KEY_COMPLIMENT_ID, "");
            compliment = SmyleApp.getSmyleComponent().getUserProfileDbHelper().getComplimentById(complimentId);
        }

        if (compliment == null) {
            compliment = new Compliment();
            compliment.setRepeat(Compliment.Repeat.NONE);
            compliment.setUserProfile(SmyleApp.getSmyleComponent().getUserProfileDbHelper().getCurrentUserProfile());

            String generatedId = generateStringId();
            while (SmyleApp.getSmyleComponent().getUserProfileDbHelper().getComplimentById(generatedId) != null) {
                generatedId = generateStringId();
            }
            compliment.setId(generatedId);
        }

        setUpRepeatSpinner();
        setUpTextWatchers();
        initializeValues();
    }

    private String generateStringId() {
        String candidateCharacters = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789";
        StringBuilder builder = new StringBuilder();
        Random random = new Random();

        for (int count = 0; count < COMPLIMENT_ID_LENGTH; count++) {
            char ch = candidateCharacters.charAt(random.nextInt(candidateCharacters.length()));
            builder.append(ch);
        }

        return builder.toString();
    }

    private void setUpRepeatSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.repeat_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repeatSpinner.setAdapter(adapter);
    }

    private void setUpTextWatchers() {
        TextWatcherUtil.messageTextFocusChangeListener(complimentEditTextLayout, complimentEditText, getString(R.string.error_empty_compliment));
        TextWatcherUtil.messageTextEditChangeListener(complimentEditTextLayout, complimentEditText, getString(R.string.error_empty_compliment));
        TextWatcherUtil.messageTextEditorActionListener(complimentEditTextLayout, complimentEditText, getString(R.string.error_empty_compliment));
    }

    private void initializeValues() {
        if (compliment.getMessage() != null && !TextUtils.isEmpty(compliment.getMessage())) {
            complimentEditText.setText(compliment.getMessage());
        }

        if (compliment.getStartDateTime() != null) {
            DateTime startDateTime = compliment.getStartDateTime();
            complimentMonth.setText(getString(R.string.two_digit_format, startDateTime.getMonthOfYear()));
            complimentDay.setText(getString(R.string.two_digit_format, startDateTime.getDayOfMonth()));
            complimentYear.setText(getString(R.string.digit_format, startDateTime.getYear()));
        }

        repeatSpinner.setSelection(getRepeatItemPosition(), false);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_compliment_detail;
    }

    @OnClick({R.id.add_compliment_date_layout, R.id.add_compliment_month,
                R.id.add_compliment_day, R.id.add_compliment_year})
    public void onClickChooseDate() {
        hideSoftKeyBoard();
        complimentEditText.clearFocus();
        DatePickerFragment.newInstance(this)
                          .show(getFragmentManager(), ComplimentDetailFragment.class.getSimpleName());
    }

    private void hideSoftKeyBoard() {
        InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        if(manager != null && manager.isAcceptingText()) { // verify if the soft keyboard is open
            View view = getActivity().getWindow().getCurrentFocus();
            if (view != null) {
                manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    @Override
    public void onDateSet(DateTime dateTime) {
        complimentMonth.setText(getString(R.string.two_digit_format, dateTime.getMonthOfYear()));
        complimentDay.setText(getString(R.string.two_digit_format, dateTime.getDayOfMonth()));
        complimentYear.setText(getString(R.string.digit_format, dateTime.getYear()));
        compliment.setStartDateTime(dateTime);
        invalidComplimentDate.setVisibility(View.GONE);
    }

    @OnClick(R.id.add_compliment_cancel)
    public void onClickCancelUpdateAdd() {
        getActivity().setResult(Activity.RESULT_CANCELED);
        getActivity().finish();
    }

    @OnClick(R.id.add_compliment_save)
    public void onClickUpdateAdd() {
        if (validateFields()) {
            compliment.setMessage(complimentEditText.getText().toString().trim());
            compliment.setRepeat(getRepeatFromDropdown());
            SmyleApp.getSmyleComponent().getUserProfileDbHelper().saveCompliment(compliment);

            Intent data = new Intent();
            data.putExtra(CreateUpdateComplimentActivity.KEY_COMPLIMENT_ID, compliment.getId());
            getActivity().setResult(Activity.RESULT_OK, data);
            getActivity().finish();
        }
    }

    private boolean validateFields() {
        boolean isValid = true;
        if (TextUtils.isEmpty(complimentEditText.getText().toString().trim())) {
            complimentEditTextLayout.setErrorEnabled(true);
            complimentEditTextLayout.setError(getString(R.string.error_empty_compliment));
            complimentEditText.requestFocus();
            isValid = false;
        }

        if (complimentEditText.getText().toString().trim().length() > MAX_CHARACTERS) {
            complimentEditTextLayout.setErrorEnabled(true);
            complimentEditTextLayout.setError(getString(R.string.error_too_long_compliment));
            isValid = false;
        }

        if (compliment.getStartDateTime() == null) {
            invalidComplimentDate.setVisibility(View.VISIBLE);

            isValid = false;
        }

        return isValid;
    }

    private Compliment.Repeat getRepeatFromDropdown() {
        switch (repeatSpinner.getSelectedItemPosition()) {
            case 1:
                return Compliment.Repeat.DAILY;
            case 2:
                return Compliment.Repeat.WEEKLY;
            case 3:
                return Compliment.Repeat.MONTHLY;
            default:
                return Compliment.Repeat.NONE;
        }
    }

    private int getRepeatItemPosition() {
        String[] repeatOptions = getResources().getStringArray(R.array.repeat_options);
        for (int index = 0; index < repeatOptions.length; index++) {
            if (repeatOptions[index].equals(compliment.getRepeat().getValue())) {
                return index;
            }
        }
        return 0;
    }
}
