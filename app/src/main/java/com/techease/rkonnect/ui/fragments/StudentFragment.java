package com.techease.rkonnect.ui.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Models.ClassModel;
import com.techease.rkonnect.ui.Models.StudentModel;

import java.util.ArrayList;
import java.util.List;

import link.fls.swipestack.SwipeStack;


public class StudentFragment extends Fragment {

    SwipeStack swipeStack;
    FloatingActionButton fab;
    String strName,strFatherName,strRollNo,strAge,getBundleClassName;
    DatabaseReference databaseReference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_student, container, false);

        getBundleClassName=getArguments().getString("class");
        swipeStack = (SwipeStack)view.findViewById(R.id.swipeStack);
        fab = (FloatingActionButton)view.findViewById(R.id.fabAddStudent);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_add_student, null);
                dialogBuilder.setView(dialogView);
                EditText etName=(EditText)dialogView.findViewById(R.id.etName);
                EditText etFatherName=(EditText)dialogView.findViewById(R.id.etName);
                EditText etAge=(EditText)dialogView.findViewById(R.id.etName);
                EditText etRollNo=(EditText)dialogView.findViewById(R.id.etName);
                strAge=etAge.getText().toString();
                strName=etName.getText().toString();
                strFatherName=etFatherName.getText().toString();
                strRollNo=etRollNo.getText().toString();
                StudentModel model=new StudentModel(strName,strFatherName,strRollNo,strAge);
                databaseReference.child("Classes").child(getBundleClassName).child("Students").child(strRollNo).setValue(model);

                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
            }
        });

        return view;
    }

}
