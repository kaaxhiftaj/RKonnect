package com.techease.rkonnect.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Adapters.HistoryAdapter;
import com.techease.rkonnect.ui.Adapters.HistoryClassesAdapter;
import com.techease.rkonnect.ui.Models.HistoryModel;
import com.techease.rkonnect.utils.AlertsUtils;

import java.util.ArrayList;


public class StudentHistory extends Fragment {

    RecyclerView rvStudentList;
    ArrayList<HistoryModel> models;
    HistoryAdapter adapter;
    FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabase;
    android.support.v7.app.AlertDialog alertDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_student_history, container, false);

        rvStudentList=(RecyclerView)view.findViewById(R.id.rvStudentHistory);
        rvStudentList.setLayoutManager(new LinearLayoutManager(getActivity()));
        models = new ArrayList<>();
        adapter = new HistoryAdapter(getActivity(),models);
        if (alertDialog == null)
            alertDialog = AlertsUtils.createProgressDialog(getActivity());
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Classes");


        return view;
    }
}
