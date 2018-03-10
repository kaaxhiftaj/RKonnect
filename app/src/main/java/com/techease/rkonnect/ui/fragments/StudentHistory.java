package com.techease.rkonnect.ui.fragments;

import android.content.Context;
import android.net.Uri;
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
import com.techease.rkonnect.ui.Adapters.HistoryAdapter;
import com.techease.rkonnect.ui.Adapters.HistoryClassesAdapter;
import com.techease.rkonnect.ui.Adapters.SwipeStackAdapter;
import com.techease.rkonnect.ui.Models.HistoryModel;
import com.techease.rkonnect.ui.Models.StudentModel;
import com.techease.rkonnect.utils.AlertsUtils;

import java.util.ArrayList;


public class StudentHistory extends Fragment {

    RecyclerView rvStudentList;
    ArrayList<HistoryModel> models;
    HistoryAdapter adapter;
    FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabase;
    android.support.v7.app.AlertDialog alertDialog;
    String className;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_student_history, container, false);

        className=getArguments().getString("class");
        rvStudentList=(RecyclerView)view.findViewById(R.id.rvStudentHistory);
        rvStudentList.setLayoutManager(new LinearLayoutManager(getActivity()));
        models = new ArrayList<>();
        adapter = new HistoryAdapter(getActivity(),models);
        if (alertDialog == null)
            alertDialog = AlertsUtils.createProgressDialog(getActivity());
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Classes").child(className).child("Students");
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                rvStudentList.setAdapter(adapter);
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {

                    HistoryModel stdModel1=dataSnapshot1.getValue(HistoryModel.class);
                    // models.add(stdModel1);
                    stdModel1.setClass_Name("abc");
                    stdModel1.setRoll_No("12321");
                    stdModel1.setStudentName("kashif");
                    models.add(stdModel1);
                    Toast.makeText(getActivity(), String.valueOf(stdModel1.getRoll_No()), Toast.LENGTH_SHORT).show();
                }
//                adapter=new HistoryAdapter(getActivity(),models);
//                rvStudentList.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return view;
    }
}
