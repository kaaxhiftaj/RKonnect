package com.techease.rkonnect.ui.fragments.Teacher;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Adapters.HistoryClassesAdapter;
import com.techease.rkonnect.ui.Adapters.RecyclerviewAdapterForClasses;
import com.techease.rkonnect.ui.Models.ClassModel;
import com.techease.rkonnect.utils.AlertsUtils;

import java.util.ArrayList;


public class HistoryFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ClassModel> list;
    HistoryClassesAdapter adapter;
    FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabase;
    android.support.v7.app.AlertDialog alertDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView=(RecyclerView)view.findViewById(R.id.rvHistoy);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new HistoryClassesAdapter(getActivity(),list);
        if (alertDialog == null)
            alertDialog = AlertsUtils.createProgressDialog(getActivity());
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Classes");

        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                recyclerView.setAdapter(adapter);

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ClassModel model = dataSnapshot1.getValue(ClassModel.class);
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
