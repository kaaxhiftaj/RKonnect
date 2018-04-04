package com.techease.rkonnect.ui.fragments.Teacher;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Adapters.RecyclerviewAdapterForClasses;
import com.techease.rkonnect.ui.Models.ClassModel;
import com.techease.rkonnect.ui.activities.TeacherDashboard;
import com.techease.rkonnect.utils.AlertsUtils;

import java.util.ArrayList;
import java.util.List;

public class Classes extends Fragment {

    RecyclerView rvClasses;
    FloatingActionButton fab;
    String strClassTitle,strInstituteName;
    ArrayList<ClassModel> list;
    RecyclerviewAdapterForClasses adapter;
    FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabase;
    android.support.v7.app.AlertDialog alertDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        rvClasses=(RecyclerView)v.findViewById(R.id.rvClasses);
        rvClasses.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new RecyclerviewAdapterForClasses(getActivity(),list);


        if (alertDialog == null)
            alertDialog = AlertsUtils.createProgressDialog(getActivity());
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Classes");
        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rvClasses.setAdapter(adapter);

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
        fab = (FloatingActionButton)v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                dialogBuilder.setCancelable(true);
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_alert_dialog, null);
                dialogBuilder.setView(dialogView);
                final EditText etClassTitle = (EditText) dialogView.findViewById(R.id.etClassTitle);
                final EditText etInstituteName = (EditText) dialogView.findViewById(R.id.etInstituteNameAddClass);
                Button btnSave=(Button)dialogView.findViewById(R.id.btnSave);
                final AlertDialog alertDialog2 = dialogBuilder.create();
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        strClassTitle=etClassTitle.getText().toString();
                        strInstituteName=etInstituteName.getText().toString();
                        ClassModel reviewLocation = new ClassModel(strClassTitle,strInstituteName);
                        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                        database.child("Classes").child(strClassTitle).setValue(reviewLocation);
                        alertDialog2.cancel();
                    }
                });
                alertDialog2.show();
            }
        });
        return  v ;
    }
}
