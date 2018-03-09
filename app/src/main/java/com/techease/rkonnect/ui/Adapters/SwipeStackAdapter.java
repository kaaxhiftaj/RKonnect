package com.techease.rkonnect.ui.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Models.StudentModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adamnoor on 05-Mar-18.
 */

public class SwipeStackAdapter  extends BaseAdapter  {
    private ArrayList<StudentModel> mData;
    Context context;

    public SwipeStackAdapter( ArrayList<StudentModel> modelArrayList, Context c) {
        this.mData=modelArrayList;
        this.context = context ;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_student, parent, false);
        StudentModel model=mData.get(position);
        TextView tvStdName = (TextView) convertView.findViewById(R.id.tvStudentName);
        TextView tvStdRollNo = (TextView) convertView.findViewById(R.id.tvRollNo);
        tvStdName.setText(model.getName());
        tvStdRollNo.setText(model.getFatherName());

        return convertView;
    }
}