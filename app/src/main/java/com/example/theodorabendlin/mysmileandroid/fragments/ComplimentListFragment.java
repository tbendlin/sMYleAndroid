package com.example.theodorabendlin.mysmileandroid.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.theodorabendlin.mysmileandroid.R;
import com.example.theodorabendlin.mysmileandroid.activities.CreateUpdateComplimentActivity;
import com.example.theodorabendlin.mysmileandroid.adapters.ComplimentAdapter;
import com.example.theodorabendlin.mysmileandroid.models.Compliment;

import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ComplimentListFragment extends BaseFragment {

    @BindView(R.id.compliment_recycler_view) protected RecyclerView complimentRecyclerView;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpRecyclerView(createSampleCompliments());
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_compliment_list;
    }

    private void setUpRecyclerView(List<Compliment> complimentList) {
        complimentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        complimentRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                                                    DividerItemDecoration.VERTICAL));
        ComplimentAdapter complimentAdapter = new ComplimentAdapter(getContext(), complimentList);
        complimentRecyclerView.setAdapter(complimentAdapter);
    }

    private List<Compliment> createSampleCompliments() {
        Compliment compliment1 = new Compliment();
        compliment1.setMessage("Hey there, you're looking great today ;)");
        compliment1.setStartDateTime(DateTime.now());
        compliment1.setRepeat(Compliment.Repeat.WEEKLY);

        Compliment compliment2 = new Compliment();
        compliment2.setMessage("The app looks amazing!");
        compliment2.setStartDateTime(DateTime.now().plusDays(1));
        compliment2.setRepeat(Compliment.Repeat.DAILY);

        Compliment compliment3 = new Compliment();
        compliment3.setMessage("You look so good in that red dress");
        compliment3.setStartDateTime(DateTime.now().plusDays(10));
        compliment3.setRepeat(Compliment.Repeat.NONE);

        return Arrays.asList(compliment1, compliment2, compliment3);
    }

    @OnClick(R.id.compliment_add_button)
    public void onClickAddCompliment() {
        Intent intent = new Intent(getActivity(), CreateUpdateComplimentActivity.class);
        startActivityForResult(intent, CreateUpdateComplimentActivity.CREATE_COMPLIMENT_REQUEST);
    }
}
