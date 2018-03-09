package com.techease.rkonnect.ui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.techease.rkonnect.R;

import java.util.List;

/**
 * Created by Adamnoor on 05-Mar-18.
 */

public class SwipeStackAdapter extends BaseAdapter {
    private List<String> mData;
    Context context;
    public SwipeStackAdapter(List<String> data) {
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public String getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
         convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_student, parent, false);
        TextView tvStudentName = (TextView) convertView.findViewById(R.id.tvStudentName);
        TextView tvRollNo = (TextView) convertView.findViewById(R.id.tvRollNo);
        tvStudentName.setText(mData.get(position));


        return convertView;
    }
}
