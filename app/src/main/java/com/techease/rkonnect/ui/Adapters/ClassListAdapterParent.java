package com.techease.rkonnect.ui.Adapters;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Models.ClassModel;
import com.techease.rkonnect.ui.activities.ParentDashboard;
import com.techease.rkonnect.ui.fragments.Parent.ParentHomeFragment;
import com.techease.rkonnect.utils.Configuration;

import java.util.ArrayList;

/**
 * Created by Adamnoor on 12-Mar-18.
 */

public class ClassListAdapterParent extends RecyclerView.Adapter<ClassListAdapterParent.ViewHolder> {
    ArrayList<ClassModel> models;
    Context context;
    public ClassListAdapterParent(Context context, ArrayList<ClassModel> list) {
        this.models=list;
        this.context=context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cutom_classes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ClassModel cModel = models.get(position);
        holder.tvClassTitle.setText(cModel.getClassTitle());
        final String title=cModel.getClassTitle();

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new ParentHomeFragment();
                Bundle bundle=new Bundle();
                bundle.putString("class",title);
                holder.editor.putString("class",cModel.getClassTitle()).commit();
                fragment.setArguments(bundle);
                Activity activity = (ParentDashboard) context;
                activity.getFragmentManager().beginTransaction().replace(R.id.fragment_main2, fragment).addToBackStack("abc").commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvClassTitle,tvNumberOfStudents;
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        String token;
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
