package com.example.theodorabendlin.mysmileandroid.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.theodorabendlin.mysmileandroid.R;
import com.example.theodorabendlin.mysmileandroid.SmyleApp;
import com.example.theodorabendlin.mysmileandroid.adapters.ComplimentAdapter;
import com.example.theodorabendlin.mysmileandroid.models.Compliment;
import com.example.theodorabendlin.mysmileandroid.utils.DateFormatUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComplimentViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.compliment_view_message) protected TextView complimentMessage;
    @BindView(R.id.compliment_view_date) protected TextView complimentDate;
    @BindView(R.id.compliment_view_repeat) protected TextView complimentRepeatOption;

    private ComplimentAdapter.ComplimentClickListener complimentClickListener;
    private View itemView;

    public ComplimentViewHolder(View itemView, ComplimentAdapter.ComplimentClickListener clickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemView = itemView;
        this.complimentClickListener = clickListener;
    }

    public void onBindViewHolder(final Compliment compliment) {
        complimentMessage.setText(compliment.getMessage());
        complimentDate.setText(DateFormatUtil.formatDate(SmyleApp.get().getApplicationContext(),
                                                          compliment.getStartDateTime()));
        complimentRepeatOption.setText(compliment.getRepeat().getValue());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complimentClickListener.onItemClick(compliment);
            }
        });
    }
}
