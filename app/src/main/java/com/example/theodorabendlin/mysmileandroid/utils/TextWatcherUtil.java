package com.example.theodorabendlin.mysmileandroid.utils;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Class used to take care of error messaging and other behaviors for
 * EditText fields
 */

public class TextWatcherUtil {

    private static void checkForMessageError(TextInputLayout messageLayout, EditText messageEditText,
                                             String errorMessage) {
        if(TextUtils.isEmpty(messageEditText.getText().toString().trim())){
            messageLayout.setErrorEnabled(true);
            messageLayout.setError(errorMessage);
        } else {
            messageLayout.setError(null);
            messageLayout.setErrorEnabled(false);
        }
    }

    public static void messageTextFocusChangeListener(final TextInputLayout messageLayout,
                                                      final EditText messageEditText,
                                                      final String errorMessage){
        messageEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    // If looses focus, validate to see if its empty
                    checkForMessageError(messageLayout, messageEditText, errorMessage);
                }
            }
        });
    }

    public static void messageTextEditChangeListener(final TextInputLayout messageLayout,
                                                      final EditText messageEditText,
                                                      final String errorMessage) {
        messageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { /* no op */ }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkForMessageError(messageLayout, messageEditText, errorMessage);
                messageLayout.setHint(null);
            }

            @Override
            public void afterTextChanged(Editable editable) { /* no op */ }
        });
    }

    public static void messageTextEditorActionListener(final TextInputLayout messageLayout,
                                                       final EditText messageEditText,
                                                       final String errorMessage){
        messageEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                checkForMessageError(messageLayout, messageEditText, errorMessage);
                return false;
            }
        });
    }

}
