package com.techease.rkonnect.ui.Adapters;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Models.StudentModel;
import com.techease.rkonnect.utils.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adamnoor on 05-Mar-18.
 */

public class SwipeStackAdapter  extends BaseAdapter  {
    private ArrayList<StudentModel> mData;
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String rollNoValue;

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
        sharedPreferences = convertView.getContext().getSharedPreferences(Configuration.MY_PREF, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        StudentModel model=mData.get(position);
        TextView tvStdName = (TextView) convertView.findViewById(R.id.tvStudentName);
        TextView tvStdRollNo = (TextView) convertView.findViewById(R.id.tvRollNo);
        tvStdName.setText(model.getName());
        tvStdRollNo.setText(model.getFatherName());
        if (position==0)
        {
            rollNoValue=model.getRollNo();
            editor.putString("r1",rollNoValue).commit();
        }
        else
            if (position==1)
            {
                rollNoValue=model.getRollNo();
                editor.putString("r2",rollNoValue).commit();
            }
            else
            if (position==2)
            {
                rollNoValue=model.getRollNo();
                editor.putString("r3",rollNoValue).commit();
            }
            else
            if (position==3)
            {
                rollNoValue=model.getRollNo();
                editor.putString("r4",rollNoValue).commit();
            }
            else
            if (position==4)
            {
                rollNoValue=model.getRollNo();
                editor.putString("r5",rollNoValue).commit();
            }
            else
            if (position==5)
            {
                rollNoValue=model.getRollNo();
                editor.putString("r6",rollNoValue).commit();
            }
            else
            if (position==6)
            {
                rollNoValue=model.getRollNo();
                editor.putString("r7",rollNoValue).commit();
            }
            else
            if (position==7)
            {
                rollNoValue=model.getRollNo();
                editor.putString("r8",rollNoValue).commit();
            }
            else
            if (position==8)
            {
                rollNoValue=model.getRollNo();
                editor.putString("r9",rollNoValue).commit();
            }
            else
            if (position==9)
            {
                rollNoValue=model.getRollNo();
                editor.putString("r10",rollNoValue).commit();
            }
            else
            if (position==10)
            {
                rollNoValue=model.getRollNo();
                editor.putString("r11",rollNoValue).commit();
            }
            else
            if (position==11)
            {
                rollNoValue=model.getRollNo();
                editor.putString("r12",rollNoValue).commit();
            }
            else
            if (position==12)
            {
                rollNoValue=model.getRollNo();
                editor.putString("r13",rollNoValue).commit();
            }
            else
            if (position==13)
            {
                rollNoValue=model.getRollNo();
                editor.putString("r14",rollNoValue).commit();
            }
            else
            if (position==14)
            {
                rollNoValue=model.getRollNo();
                editor.putString("r15",rollNoValue).commit();
            }
            else
            if (position==15)
            {
                rollNoValue=model.getRollNo();
                editor.putString("r16",rollNoValue).commit();
            }
            else
            if (position==16)
            {
                rollNoValue=model.getRollNo();
                editor.putString("r17",rollNoValue).commit();
            }
            else
            if (position==17)
            {
                rollNoValue=model.getRollNo();
                editor.putString("r18",rollNoValue).commit();
            }
            else
            if (position==18)
            {
                rollNoValue=model.getRollNo();
                editor.putString("r19",rollNoValue).commit();
            }
            else
            if (position==19)
            {
                rollNoValue=model.getRollNo();
                editor.putString("r20",rollNoValue).commit();
            }

        return convertView;
    }
}