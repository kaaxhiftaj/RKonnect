package com.techease.rkonnect.ui.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Models.ClassModel;

import java.util.List;

/**
 * Created by Adamnoor on 08-Mar-18.
 */

public class RecyclerviewAdapterForClasses extends RecyclerView.Adapter<RecyclerviewAdapterForClasses.ViewHolder> {

    Context context;
    List<ClassModel> classModels;

    public RecyclerviewAdapterForClasses(Context context, List<ClassModel> models) {

        this.classModels = models;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cutom_classes, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ClassModel cModel = classModels.get(position);
        holder.tvClassTitle.setText(cModel.getClassTitle());
    }

    @Override
    public int getItemCount() {
        return classModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvClassTitle,tvNumberOfStudents;
        public ViewHolder(View itemView) {
            super(itemView);

            tvClassTitle=(TextView)itemView.findViewById(R.id.tvClassName);
        }
    }
}
