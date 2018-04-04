package com.techease.rkonnect.ui.fragments.Teacher;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.techease.rkonnect.R;


public class ReportsFragment extends Fragment {
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  v= inflater.inflate(R.layout.fragment_reports, container, false);
        auth = FirebaseAuth.getInstance();

        return  v;
    }
}
