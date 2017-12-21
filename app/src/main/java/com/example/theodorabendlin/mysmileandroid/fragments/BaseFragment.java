package com.example.theodorabendlin.mysmileandroid.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    /**
     * The layout resource if for this fragment
     */
    protected abstract int getLayoutResourceId();

    /**
     * Covers off common fragment initialization tasks such as creating the content view &
     * binding the sub-views that sub classes should implement
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(getLayoutResourceId(), container, false);
        ButterKnife.bind(BaseFragment.this, view);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        return view;
    }

    /**
     * Use this method when you are replacing existing fragments in an activity
     *
     * @param containerId   -- id of the current view container
     * @param activity      -- activity hosting the current view
     * @param fragment      -- fragment to replace the current view with
     * @param transactionId -- the simple name of the fragment
     */
    public static void replaceFragmentInActivity(int containerId, AppCompatActivity activity,
                                                 Fragment fragment, String transactionId) {
        activity.getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(containerId, fragment, transactionId)
                .addToBackStack(transactionId)
                .commit();
    }

    /**
     * Use this method when you are starting an activity with the first fragment
     *
     * @param containerId   -- id of the current view container
     * @param activity      -- activity hosting the current view
     * @param fragment      -- fragment to replace the current view with
     * @param transactionId -- the simple name of the fragment
     */
    public static void replaceFragmentNotInBackstack(int containerId, AppCompatActivity activity,
                                                 Fragment fragment, String transactionId) {
        activity.getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(containerId, fragment, transactionId)
                .commit();
    }
}
