package com.techease.rkonnect.ui.Adapters;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Models.HistoryModel;
import com.techease.rkonnect.ui.activities.MainActivity;
import com.techease.rkonnect.ui.fragments.StudentFragment;

import java.util.ArrayList;

/**
 * Created by Adamnoor on 09-Mar-18.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
    Context context;
    ArrayList<HistoryModel>  models;

    public HistoryAdapter(Context context, ArrayList<HistoryModel> models) {
        this.context=context;
        this.models=models;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HistoryModel model=models.get(position);
      //  holder.tvStatus.setText(model.getStatus());
        holder.tvRollNo.setText(model.getRoll_No());
        holder.tvStudentName.setText(model.getStudentName());
        holder.tvClassName.setText(model.getClass_Name());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudentName,tvClassName,tvRollNo,tvStatus;
        public ViewHolder(View itemView) {
            super(itemView);

            tvStudentName=(TextView)itemView.findViewById(R.id.tvName);
            tvClassName=(TextView)itemView.findViewById(R.id.tvClass);
            tvRollNo=(TextView)itemView.findViewById(R.id.tvR_No);
            tvStatus=(TextView)itemView.findViewById(R.id.tvStatus);


        }
    }
}
