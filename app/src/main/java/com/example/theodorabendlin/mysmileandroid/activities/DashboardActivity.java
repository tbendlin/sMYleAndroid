package com.example.theodorabendlin.mysmileandroid.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.example.theodorabendlin.mysmileandroid.R;
import com.example.theodorabendlin.mysmileandroid.fragments.BaseFragment;
import com.example.theodorabendlin.mysmileandroid.fragments.ComplimentListFragment;
import com.example.theodorabendlin.mysmileandroid.fragments.DashboardFeedFragment;
import com.example.theodorabendlin.mysmileandroid.fragments.ProfileScreenFragment;

import butterknife.BindView;

public class DashboardActivity extends BaseActivity {

    @BindView(R.id.dashboard_bottom_navigation) protected BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        openFragment(new DashboardFeedFragment());

        setUpBottomNavigation();
    }

    private void setUpBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                if (!isAttached(DashboardFeedFragment.class)) {
                                    openFragment(new DashboardFeedFragment());
                                }
                                break;
                            case R.id.action_compliment_list:
                                if (!isAttached(ComplimentListFragment.class)) {
                                    openFragment(new ComplimentListFragment());
                                }
                                break;
                            case R.id.action_profile:
                                if (!isAttached(ProfileScreenFragment.class)) {
                                    openFragment(new ProfileScreenFragment());
                                }
                                break;
                        }
                        return true;
                    }
                }
        );
    }

    private boolean isAttached(Class clazz) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.dashboard_activity_container);
        return fragment != null && fragment.getClass() == clazz;
    }

    private void openFragment(Fragment fragment) {
        BaseFragment.replaceFragmentNotInBackstack(R.id.dashboard_activity_container, this,
                                                    fragment, fragment.getClass().getSimpleName());
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            moveTaskToBack(true);
        } else {
            super.onBackPressed();
        }
    }
}
