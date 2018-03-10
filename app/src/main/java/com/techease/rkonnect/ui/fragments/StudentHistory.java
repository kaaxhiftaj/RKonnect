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
import com.techease.rkonnect.ui.Models.ClassModel;
import com.techease.rkonnect.ui.Models.HistoryModel;
import com.techease.rkonnect.ui.Models.StudentModel;
import com.techease.rkonnect.utils.AlertsUtils;

import java.util.ArrayList;


public class StudentHistory extends Fragment {

    RecyclerView recyclerView;
    ArrayList<HistoryModel> list;
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
        recyclerView=(RecyclerView)view.findViewById(R.id.rvStudentHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new HistoryAdapter(getActivity(),list);
        if (alertDialog == null)
            alertDialog = AlertsUtils.createProgressDialog(getActivity());
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Classes").child(className).child("Students");

        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                recyclerView.setAdapter(adapter);

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    HistoryModel model = dataSnapshot1.getValue(HistoryModel.class);
                    if (alertDialog != null)
                        alertDialog.dismiss();
                    list.add(model);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                recyclerView.setAdapter(adapter);

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    HistoryModel model = dataSnapshot1.getValue(HistoryModel.class);
                    if (alertDialog != null)
                        alertDialog.dismiss();
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
