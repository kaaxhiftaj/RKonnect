package com.techease.rkonnect.ui.Adapters;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Models.ClassModel;
import com.techease.rkonnect.ui.activities.MainActivity;
import com.techease.rkonnect.ui.fragments.StudentFragment;
import com.techease.rkonnect.utils.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adamnoor on 08-Mar-18.
 */

public class RecyclerviewAdapterForClasses extends RecyclerView.Adapter<RecyclerviewAdapterForClasses.ViewHolder> {

    Context context;
    ArrayList<ClassModel> classModels;

    public RecyclerviewAdapterForClasses(Context context, ArrayList<ClassModel> models) {

        this.classModels = models;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cutom_classes, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ClassModel cModel = classModels.get(position);
        holder.tvClassTitle.setText(cModel.getClassTitle());
        final String title=cModel.getClassTitle();
       holder.linearLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Fragment fragment = new StudentFragment();
               Bundle bundle=new Bundle();
               bundle.putString("class",title);
               holder.editor.putString("class",title).commit();
               fragment.setArguments(bundle);
               Activity activity = (MainActivity) context;
               activity.getFragmentManager().beginTransaction().replace(R.id.fragment_main, fragment).addToBackStack("abc").commit();
           }
       });
    }

    @Override
    public int getItemCount() {
        return classModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvClassTitle,tvNumberOfStudents;
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;

        LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);

            tvClassTitle=(TextView)itemView.findViewById(R.id.tvClassName);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.linearLayout);
            sharedPreferences = context.getSharedPreferences(Configuration.MY_PREF, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
    }
}
