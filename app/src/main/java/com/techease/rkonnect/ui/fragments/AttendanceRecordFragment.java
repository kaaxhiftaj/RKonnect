package com.techease.rkonnect.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Adapters.AttendanceAdapter;
import com.techease.rkonnect.ui.Adapters.HistoryAdapter;
import com.techease.rkonnect.ui.Models.AttendanceRecordModel;
import com.techease.rkonnect.utils.Configuration;

import java.util.ArrayList;

public class AttendanceRecordFragment extends Fragment {

    RecyclerView recyclerView;
    private DatabaseReference mFirebaseDatabase;
    ArrayList<AttendanceRecordModel> list;
    AttendanceAdapter attendanceAdapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String className,rollNo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_attendance_record, container, false);
        sharedPreferences = getActivity().getSharedPreferences(Configuration.MY_PREF, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        className=sharedPreferences.getString("class","");
        rollNo=sharedPreferences.getString("roll","");
        Toast.makeText(getActivity(), className+rollNo, Toast.LENGTH_SHORT).show();
        recyclerView=(RecyclerView)view.findViewById(R.id.rvAttendanceRocord);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        attendanceAdapter = new AttendanceAdapter(getActivity(),list);
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Classes").child(className).child("Students").child(rollNo).child("Attendence");
        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                recyclerView.setAdapter(attendanceAdapter);
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    Toast.makeText(getActivity(), String.valueOf(dataSnapshot1), Toast.LENGTH_SHORT).show();
                    AttendanceRecordModel model=dataSnapshot1.getValue(AttendanceRecordModel.class);
                    Toast.makeText(getActivity(), String.valueOf(model.getStatus()), Toast.LENGTH_SHORT).show();
                    list.add(model);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }
}
