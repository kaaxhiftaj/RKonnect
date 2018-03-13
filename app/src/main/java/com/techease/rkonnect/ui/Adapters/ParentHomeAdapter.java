package com.techease.rkonnect.ui.Adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Models.ParentHomeModel;
import com.techease.rkonnect.ui.activities.TeacherDashboard;

import java.util.ArrayList;

/**
 * Created by Adamnoor on 12-Mar-18.
 */

public class ParentHomeAdapter extends RecyclerView.Adapter<ParentHomeAdapter.ViewHolder> {
    ArrayList<ParentHomeModel> models;
    Context context;

    public ParentHomeAdapter(Context context, ArrayList<ParentHomeModel> list) {
        this.context=context;
        this.models=list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_parent_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ParentHomeModel model=models.get(position);
        holder.tvName.setText(model.getName());
        holder.tvInsti.setText(model.getInstituteName());
        holder.tvClass.setText(model.getClassTitle());
        holder.tvRollNo.setText(model.getRollNo());
        holder.tvStatus.setText(model.getStatus());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvRollNo,tvInsti,tvClass,tvStatus;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName=(TextView)itemView.findViewById(R.id.tvChildName);
            tvRollNo=(TextView)itemView.findViewById(R.id.tvChildRollNo);
            tvInsti=(TextView)itemView.findViewById(R.id.tvChildInsti);
            tvClass=(TextView)itemView.findViewById(R.id.tvClass);
            tvStatus=(TextView) itemView.findViewById(R.id.tvChildStatus);
        }
    }
}
