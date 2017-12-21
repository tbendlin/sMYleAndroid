package com.example.theodorabendlin.mysmileandroid.activities;

import android.content.Intent;
import android.os.Bundle;

public class SpashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

}
