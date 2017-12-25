package com.example.theodorabendlin.mysmileandroid.fragments;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.example.theodorabendlin.mysmileandroid.R;
import com.example.theodorabendlin.mysmileandroid.activities.CreateUpdateComplimentActivity;

public class ComplimentDetailFragment extends BaseFragment {

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
        getActivity().setTitle(R.string.new_compliment);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_compliment_detail;
    }
}
