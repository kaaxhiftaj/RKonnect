package com.techease.rkonnect.ui.fragments.Parent;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Adapters.HistoryAdapter;
import com.techease.rkonnect.ui.Models.HistoryModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class HistoryFragmentParent extends Fragment {

    RecyclerView recyclerView;
    ArrayList<HistoryModel> list;
    HistoryAdapter adapter;
    FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabase;
    android.support.v7.app.AlertDialog alertDialog;
    String className;
    Button btnNext,btnBack;
    TextView tvDate;
    String formattedDate;
    SimpleDateFormat df;
    Calendar c;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_history_fragment_parent, container, false);

        return view;
    }
}
