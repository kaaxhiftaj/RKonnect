package com.techease.rkonnect.ui.fragments.Parent;

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
import com.techease.rkonnect.ui.Adapters.ParentHomeAdapter;
import com.techease.rkonnect.ui.Adapters.RecyclerviewAdapterForClasses;
import com.techease.rkonnect.ui.Models.ParentHomeModel;
import com.techease.rkonnect.utils.AlertsUtils;

import java.util.ArrayList;

public class ParentHomeFragment extends Fragment {

    RecyclerView rvParentHome;
    ArrayList<ParentHomeModel> list;
    ParentHomeAdapter adapter;
    FirebaseAuth mAuth;
    String CNIC,className;
    String classTitle,insti;
    private DatabaseReference mFirebaseDatabase;
    private DatabaseReference mFirebaseDatabaseForCnic;
    private DatabaseReference mFirebaseDatabaseForClassAndInsti;
    android.support.v7.app.AlertDialog alertDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_parent_home, container, false);

        className=getArguments().getString("class","");
        rvParentHome=(RecyclerView)view.findViewById(R.id.rvParentHome);
        rvParentHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new ParentHomeAdapter(getActivity(),list);
        mFirebaseDatabaseForCnic=FirebaseDatabase.getInstance().getReference().child("user").child("Parents");
        mFirebaseDatabaseForCnic.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {

                    CNIC=dataSnapshot1.child("cnic").getValue(String.class);
                  //  Toast.makeText(getActivity(), CNIC, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mFirebaseDatabaseForClassAndInsti=FirebaseDatabase.getInstance().getReference().child("Classes").child(className);
        mFirebaseDatabaseForClassAndInsti.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                     classTitle=dataSnapshot1.child("classTitle").getValue(String.class);
                     insti=dataSnapshot1.child("instituteName").getValue(String.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if (alertDialog == null)
            alertDialog = AlertsUtils.createProgressDialog(getActivity());
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Classes").child(className).child("Students");
        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rvParentHome.setAdapter(adapter);
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    ParentHomeModel model=new ParentHomeModel();
                    String name = dataSnapshot1.child("name").getValue(String.class);
                    String rollNo = dataSnapshot1.child("rollNo").getValue(String.class);
                    String cnic= dataSnapshot1.child("cnic").getValue(String.class);


                    if (cnic.equals(CNIC))
                    {
                        model.setName(name);
                        model.setRollNo(rollNo);
                        model.setInstituteName(insti);
                        model.setClassTitle(classTitle);
                        list.add(model);
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "No record found", Toast.LENGTH_SHORT).show();
                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

}
