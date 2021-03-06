package com.techease.rkonnect.ui.fragments.Teacher;

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


public class ProfileFragment extends Fragment {

    TextView tvInsti,tvEmail;
    private DatabaseReference mFirebaseDatabase;
    String insti;
    String email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvEmail=(TextView)view.findViewById(R.id.tvTeacherEmail);
        tvInsti=(TextView)view.findViewById(R.id.tvTeacherInsti);

        mFirebaseDatabase= FirebaseDatabase.getInstance().getReference().child("user").child("Teachers");
        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {

                    insti=dataSnapshot1.child("instituteName").getValue(String.class);
                    email=dataSnapshot1.child("email").getValue(String.class);
                }
                tvInsti.setText(insti);
                tvEmail.setText(email);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }

}
