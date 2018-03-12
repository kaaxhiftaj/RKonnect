package com.techease.rkonnect.ui.fragments.Parent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Adapters.ParentHomeAdapter;
import com.techease.rkonnect.ui.Models.ParentHomeModel;
import com.techease.rkonnect.utils.AlertsUtils;

import java.util.ArrayList;

public class ParentHomeActivity extends AppCompatActivity {


    RecyclerView rvParentHome;
    ArrayList<ParentHomeModel> list;
    ParentHomeAdapter adapter;
    FirebaseAuth mAuth;
    String CNIC,className;
    private DatabaseReference mFirebaseDatabase;
    private DatabaseReference mFirebaseDatabaseForCnic;
    android.support.v7.app.AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_home);

        rvParentHome=(RecyclerView)findViewById(R.id.rvParentHome);
        rvParentHome.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new ParentHomeAdapter(this,list);
        mFirebaseDatabaseForCnic= FirebaseDatabase.getInstance().getReference().child("user").child("Parents");
        mFirebaseDatabaseForCnic.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    Toast.makeText(ParentHomeActivity.this, String.valueOf(dataSnapshot), Toast.LENGTH_SHORT).show();
                    CNIC=dataSnapshot1.child("cnic").getValue(String.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if (alertDialog == null)
            alertDialog = AlertsUtils.createProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Classes").child(className).child("Students");
        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rvParentHome.setAdapter(adapter);
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    // Toast.makeText(getActivity(), String.valueOf(dataSnapshot), Toast.LENGTH_SHORT).show();
                    ParentHomeModel model=new ParentHomeModel();
                    String name = dataSnapshot1.child("name").getValue(String.class);
                    String rollNo = dataSnapshot1.child("rollNo").getValue(String.class);
                    String cnic= dataSnapshot1.child("cnic").getValue(String.class);
                    String classTitle=dataSnapshot1.child("classTitle").getValue(String.class);
                    String insti=dataSnapshot1.child("instituteName").getValue(String.class);

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
                        Toast.makeText(ParentHomeActivity.this, "No record found", Toast.LENGTH_SHORT).show();
                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
