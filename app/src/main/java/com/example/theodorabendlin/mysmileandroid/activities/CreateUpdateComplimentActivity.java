package com.example.theodorabendlin.mysmileandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.example.theodorabendlin.mysmileandroid.R;
import com.example.theodorabendlin.mysmileandroid.fragments.BaseFragment;
import com.example.theodorabendlin.mysmileandroid.fragments.ComplimentDetailFragment;

import butterknife.BindView;

public class CreateUpdateComplimentActivity extends BaseActivity {

    public static final String KEY_COMPLIMENT_ID = "KEY_COMPLIMENT_ID";
    public static final int CREATE_COMPLIMENT_REQUEST = 1;
    public static final int UPDATE_COMPLIMENT_REQUEST = 2;

    @BindView(R.id.compliment_activity_toolbar) protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_update_compliment);

        Intent intent = getIntent();
        String complimentId = null;
        if (intent != null) {
            complimentId = intent.getStringExtra(KEY_COMPLIMENT_ID);
        }

        BaseFragment.replaceFragmentNotInBackstack(R.id.compliment_activity_container, this,
                                                    ComplimentDetailFragment.newInstance(complimentId),
                                                    ComplimentDetailFragment.class.getSimpleName());
    }
}
