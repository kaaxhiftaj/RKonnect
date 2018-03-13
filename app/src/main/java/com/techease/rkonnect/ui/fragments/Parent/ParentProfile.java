package com.techease.rkonnect.ui.fragments.Parent;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techease.rkonnect.R;

public class ParentProfile extends Fragment {

    TextView tvName,tvCNIC,tvEmail;
    private DatabaseReference mFirebaseDatabase;
    String name;
    String cnic;
    String email;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_parent_profile, container, false);

        tvName=(TextView)view.findViewById(R.id.tvParentName);
        tvEmail=(TextView)view.findViewById(R.id.tvParentEmail);
        tvCNIC=(TextView)view.findViewById(R.id.tvParentCNIC);

        mFirebaseDatabase= FirebaseDatabase.getInstance().getReference().child("user").child("Parents");
        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {

                     name=dataSnapshot1.child("name").getValue(String.class);
                     cnic=dataSnapshot1.child("cnic").getValue(String.class);
                     email=dataSnapshot1.child("email").getValue(String.class);
                }
                tvName.setText(name);
                tvCNIC.setText(cnic);
                tvEmail.setText(email);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        return view;
    }

}
