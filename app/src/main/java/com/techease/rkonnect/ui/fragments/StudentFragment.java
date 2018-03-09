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
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Adapters.SwipeStackAdapter;
import com.techease.rkonnect.ui.Models.ClassModel;
import com.techease.rkonnect.ui.Models.StudentModel;
import java.util.ArrayList;
import java.util.List;

import link.fls.swipestack.SwipeStack;


public class StudentFragment extends Fragment {

    SwipeStack swipeStack;
    SwipeStackAdapter adapter;
    FloatingActionButton fab;
    String strName,strFatherName,strRollNo,strAge,getBundleClassName;
    DatabaseReference databaseReference,getDatabaseReference;
    List<String> modelArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_student, container, false);

        databaseReference=FirebaseDatabase.getInstance().getReference();
        getBundleClassName=getArguments().getString("class");
        swipeStack = (SwipeStack)view.findViewById(R.id.swipeStack);
        fab = (FloatingActionButton)view.findViewById(R.id.fabAddStudent);
        modelArrayList=new ArrayList<>();
        getDatabaseReference=FirebaseDatabase.getInstance().getReference().child("Classes").child(getBundleClassName).child("Students");
        getDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {

                    StudentModel stdModel1=dataSnapshot1.getValue(StudentModel.class);
                    Toast.makeText(getActivity(), String.valueOf(stdModel1.getName()), Toast.LENGTH_SHORT).show();
                   // modelArrayList.add(stdModel1.getName());
                    modelArrayList.add("abc");
                    modelArrayList.add("xyz");

                }
             //   swipeStack.setAdapter(new SwipeStackAdapter(modelArrayList));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        swipeStack.setAdapter(new SwipeStackAdapter(modelArrayList));
//        adapter=new SwipeStackAdapter(getActivity(),modelArrayList);
//        swipeStack.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_add_student, null);
                dialogBuilder.setView(dialogView);
                final EditText etName=(EditText)dialogView.findViewById(R.id.etName);
                final EditText etFatherName=(EditText)dialogView.findViewById(R.id.etFatherName);
                final EditText etAge=(EditText)dialogView.findViewById(R.id.etAge);
                final EditText etRollNo=(EditText)dialogView.findViewById(R.id.etRollNo);
                Button btnAdd=(Button)dialogView.findViewById(R.id.btnAdd);

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        strAge=etAge.getText().toString();
                        strName=etName.getText().toString();
                        strFatherName=etFatherName.getText().toString();
                        strRollNo=etRollNo.getText().toString();
                        final StudentModel model=new StudentModel(strName,strFatherName,strRollNo,strAge);
                        databaseReference.child("Classes").child(getBundleClassName).child("Students").child(strRollNo).setValue(model);

                    }
                });
                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
            }
        });

        return view;
    }

}
