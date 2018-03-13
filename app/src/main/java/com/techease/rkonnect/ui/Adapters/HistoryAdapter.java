package com.techease.rkonnect.ui.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Models.AttendanceRecordModel;
import com.techease.rkonnect.ui.Models.HistoryModel;
import com.techease.rkonnect.utils.Configuration;

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
    public void onBindViewHolder(final ViewHolder holder, int position) {
      final  HistoryModel model=models.get(position);
      //  holder.tvStatus.setText(model.getStatus());
        holder.tvRollNo.setText(model.getName());
        holder.tvStudentName.setText(model.getFatherName());
        holder.tvClassName.setText(model.getRollNo());
        holder.tvStatus.setText(model.getAttendence());
        final String title=model.getAge();
//        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AttendanceRecordModel attendenceModel=new AttendanceRecordModel(attendence,formattedDate);
//                holder.databaseReference.child("Classes").child(getBundleClassName).
//                        child("Students").child(rollNo).child(formattedDate).setValue(attendenceModel);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudentName,tvClassName,tvRollNo,tvStatus;
        Button btnUpdate;
        RelativeLayout relativeLayout;
        DatabaseReference databaseReference;
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        public ViewHolder(View itemView) {
            super(itemView);

            sharedPreferences = context.getSharedPreferences(Configuration.MY_PREF, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            tvStudentName=(TextView)itemView.findViewById(R.id.tvName);
            tvClassName=(TextView)itemView.findViewById(R.id.tvClass);
            tvRollNo=(TextView)itemView.findViewById(R.id.tvR_No);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.relativelayout);
            tvStatus=(TextView)itemView.findViewById(R.id.tvStatus);
           // btnUpdate=(Button)itemView.findViewById(R.id.btnUpdate);
            databaseReference= FirebaseDatabase.getInstance().getReference();


        }
    }
}
