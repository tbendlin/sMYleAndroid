package com.example.theodorabendlin.mysmileandroid.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.theodorabendlin.mysmileandroid.R;
import com.example.theodorabendlin.mysmileandroid.SmyleApp;
import com.example.theodorabendlin.mysmileandroid.activities.CreateUpdateComplimentActivity;
import com.example.theodorabendlin.mysmileandroid.adapters.ComplimentAdapter;
import com.example.theodorabendlin.mysmileandroid.database.UserProfileDbHelper;
import com.example.theodorabendlin.mysmileandroid.models.Compliment;
import com.example.theodorabendlin.mysmileandroid.utils.ListUtil;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ComplimentListFragment extends BaseFragment {

    @BindView(R.id.compliment_recycler_view) protected RecyclerView complimentRecyclerView;
    @BindView(R.id.empty_compliment_list_message) protected View emptyComplimentListMessageView;

    private UserProfileDbHelper userProfileDbHelper;
    private List<Compliment> compliments;
    private ComplimentAdapter complimentAdapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userProfileDbHelper = SmyleApp.getSmyleComponent().getUserProfileDbHelper();

        compliments = userProfileDbHelper.getComplimentsForUser(userProfileDbHelper.getCurrentUserProfile());
        setUpRecyclerView();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_compliment_list;
    }

    @Override
    public void onResume() {
        super.onResume();
        toggleEmptyListMessage();
    }

    private void setUpRecyclerView() {
        complimentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        complimentRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                                                    DividerItemDecoration.VERTICAL));
        complimentAdapter = new ComplimentAdapter(getContext(), compliments);
        complimentRecyclerView.setAdapter(complimentAdapter);
    }

    private void toggleEmptyListMessage() {
        if (ListUtil.isEmpty(compliments)) {
            complimentRecyclerView.setVisibility(View.GONE);
            emptyComplimentListMessageView.setVisibility(View.VISIBLE);
        } else {
            complimentRecyclerView.setVisibility(View.VISIBLE);
            emptyComplimentListMessageView.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.compliment_add_button)
    public void onClickAddCompliment() {
        Intent intent = new Intent(getActivity(), CreateUpdateComplimentActivity.class);
        startActivityForResult(intent, CreateUpdateComplimentActivity.CREATE_COMPLIMENT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CreateUpdateComplimentActivity.CREATE_COMPLIMENT_REQUEST) {
                String complimentId = data.getStringExtra(CreateUpdateComplimentActivity.KEY_COMPLIMENT_ID);
                Compliment newCompliment = userProfileDbHelper.getComplimentById(complimentId);
                if (newCompliment != null) {
                    compliments.add(newCompliment);
                    complimentAdapter.notifyItemInserted(compliments.size() - 1);
                }
            } else {
                // Update the compliment
            }
        }
    }
}
