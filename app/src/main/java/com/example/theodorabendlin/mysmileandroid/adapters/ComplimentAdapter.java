package com.example.theodorabendlin.mysmileandroid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.theodorabendlin.mysmileandroid.R;
import com.example.theodorabendlin.mysmileandroid.adapters.viewholders.ComplimentViewHolder;
import com.example.theodorabendlin.mysmileandroid.models.Compliment;
import com.example.theodorabendlin.mysmileandroid.utils.ListUtil;

import java.util.List;

public class ComplimentAdapter extends RecyclerView.Adapter<ComplimentViewHolder> {

    private List<Compliment> compliments;
    private Context context;
    private ComplimentClickListener listener;

    public ComplimentAdapter(Context context, List<Compliment> compliments) {
        this.compliments = compliments;
        this.context = context;
    }

    @Override
    public ComplimentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View complimentView = inflater.inflate(R.layout.card_view_compliment, parent, false);
        return new ComplimentViewHolder(complimentView, listener);
    }

    @Override
    public void onBindViewHolder(ComplimentViewHolder holder, int position) {
        holder.onBindViewHolder(compliments.get(position));
    }

    @Override
    public int getItemCount() {
        return ListUtil.getCount(this.compliments);
    }

    public void setOnComplimentClickListener(ComplimentClickListener listener) {
        this.listener = listener;
    }

    public interface ComplimentClickListener {
        void onItemClick(Compliment compliment);
    }
}
