package com.example.theodorabendlin.mysmileandroid.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.theodorabendlin.mysmileandroid.SmyleApp;
import com.example.theodorabendlin.mysmileandroid.models.UserProfile;

public class SpashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        UserProfile userProfile = new UserProfile();
        userProfile.setUuid(UserProfile.DEFAULT_UUID);
        SmyleApp.getSmyleComponent().getUserProfileDbHelper().saveUserProfile(userProfile);

        SmyleApp.getSmyleComponent().getSharedPrefsHelper().setCurrentUser(userProfile);

        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

}
