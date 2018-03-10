package com.techease.rkonnect.ui.Adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Models.AttendanceRecordModel;

import java.util.ArrayList;

/**
 * Created by Adamnoor on 09-Mar-18.
 */

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder> {
    ArrayList<AttendanceRecordModel> models;
    Context context;

    public AttendanceAdapter(Context context, ArrayList<AttendanceRecordModel> list) {
        this.context=context;
        this.models=list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_attendenelist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AttendanceRecordModel model=models.get(position);
        holder.tvDate.setText(model.getStrDate());
        holder.tvStatus.setText(model.getStatus());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvStatus,tvDate;
        public ViewHolder(View itemView) {
            super(itemView);
            tvStatus=(TextView)itemView.findViewById(R.id.tvAttdeneceStatus);
            tvDate=(TextView)itemView.findViewById(R.id.tvDate);
        }
    }
}
