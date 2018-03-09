package com.techease.rkonnect.ui.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Models.ClassModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView rvClasses;
    DatabaseReference databaseReferenceForClasses;
    DatabaseReference databaseReference;
    DatabaseReference getDatabaseReference;
    FirebaseDatabase firebaseDatabase;
    FloatingActionButton fab;
    String strClassTitle,strInstituteName;
    List<ClassModel> list = new ArrayList<>();
    RecyclerView.Adapter adapter ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        rvClasses=(RecyclerView)v.findViewById(R.id.rvClasses);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReferenceForClasses = firebaseDatabase.getReference("Classes");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Classes");
        databaseReference.keepSynced(true);
        rvClasses.setHasFixedSize(true);
        rvClasses.setLayoutManager(new LinearLayoutManager(getActivity()));

        fab = (FloatingActionButton)v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_alert_dialog, null);
                dialogBuilder.setView(dialogView);

                EditText etClassTitle = (EditText) dialogView.findViewById(R.id.etClassTitle);
                EditText etInstituteName = (EditText) dialogView.findViewById(R.id.etClassTitle);
                Button btnSave=(Button)dialogView.findViewById(R.id.btnSave);
                strClassTitle=etClassTitle.getText().toString();
                strInstituteName=etInstituteName.getText().toString();
                final DatabaseReference newPost = databaseReference.push();
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                        {
                            newPost.child("Class Title").setValue(strClassTitle);
                            newPost.child("Institute Name").setValue(strInstituteName);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
            }
        });
        return  v ;
    }
}
