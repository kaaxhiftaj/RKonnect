package com.techease.rkonnect.ui.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Models.ParentHomeModel;
import com.techease.rkonnect.ui.Models.ShowPercentageModel;

import java.util.ArrayList;

public class ShowPercentageAdapter extends RecyclerView.Adapter<ShowPercentageAdapter.ViewHolder> {
    ArrayList<ShowPercentageModel> models;
    Context context;

    public ShowPercentageAdapter(Context context, ArrayList<ShowPercentageModel> list) {
        this.context=context;
        this.models=list;
    }

    public ShowPercentageAdapter(Activity activity, ArrayList<ParentHomeModel> list) {
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_percentage_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ShowPercentageModel model=models.get(position);
        holder.percentage.setText(model.getStatus());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView percentage;
        public ViewHolder(View itemView) {
            super(itemView);
            percentage = itemView.findViewById(R.id.percentage);
        }
    }
}