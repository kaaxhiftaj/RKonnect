package com.techease.rkonnect.ui.fragments.Parent;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.FileObserver;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Adapters.ParentHomeAdapter;
import com.techease.rkonnect.ui.Adapters.ShowPercentageAdapter;
import com.techease.rkonnect.ui.Models.ParentHomeModel;
import com.techease.rkonnect.ui.Models.ShowPercentageModel;
import com.techease.rkonnect.utils.Configuration;

import java.util.ArrayList;

public class ParentProfile extends Fragment {
    RecyclerView rvParentProfile;
    ArrayList<ShowPercentageModel> list;
    ShowPercentageAdapter adapter;
    private DatabaseReference mFirebaseDatabase;
    DatabaseReference databaseReferenceforDate;
    FirebaseAuth auth;
    String name,rollNo,attendanceAverage;
    String Storage_Path = "All_Image_Uploads/";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String user_id,user_token;
    String class_name,DATE;
    String childs,CNIC;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_parent_profile, container, false);
      //  rvParentProfile=(RecyclerView)view.findViewById(R.id.rvChildsPercent);
      //  list = new ArrayList<>();
        auth = FirebaseAuth.getInstance();

        //for getting only date
        databaseReferenceforDate = FirebaseDatabase.getInstance().getReference().child("Classes").child("1st").child("Students");
        databaseReferenceforDate.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    CNIC= dataSnapshot1.child("cnic").getValue(String.class);
                    Log.d("nic", CNIC);
                    childs = dataSnapshot1.getKey();
                    Log.d("childs", childs);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //this is for getting all the attendance
        mFirebaseDatabase= FirebaseDatabase.getInstance().getReference().child("user").child("Parents");
        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    String cnic =dataSnapshot1.child("cnic").getValue(String.class);
                    Log.d("parent", cnic);
                    if (cnic.equals(CNIC)) {
                        Toast.makeText(getActivity(), "Matched", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


       // adapter = new ShowPercentageAdapter(getActivity(),list);
       // rvParentProfile.setAdapter(adapter);
        return view;
    }

}
