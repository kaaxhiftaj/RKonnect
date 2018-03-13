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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Adapters.HistoryAdapter;
import com.techease.rkonnect.ui.Adapters.HistoryClassesAdapter;
import com.techease.rkonnect.ui.Adapters.SwipeStackAdapter;
import com.techease.rkonnect.ui.Models.ClassModel;
import com.techease.rkonnect.ui.Models.HistoryModel;
import com.techease.rkonnect.ui.Models.StudentModel;
import com.techease.rkonnect.utils.AlertsUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class StudentHistory extends Fragment {

    RecyclerView recyclerView;
    ArrayList<HistoryModel> list;
    HistoryAdapter adapter;
    FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabase;
    android.support.v7.app.AlertDialog alertDialog;
    String className;
    Button btnNext,btnBack;
    TextView tvDate;
    String formattedDate;
    SimpleDateFormat df;
    Calendar c;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_student_history, container, false);

        className=getArguments().getString("class");
        recyclerView=(RecyclerView)view.findViewById(R.id.rvStudentHistory);
        btnNext=(Button)view.findViewById(R.id.btnNextDate);
        btnBack=(Button)view.findViewById(R.id.btnBackDate);
        tvDate=(TextView)view.findViewById(R.id.tvDate);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new HistoryAdapter(getActivity(),list);
        if (alertDialog == null)
            alertDialog = AlertsUtils.createProgressDialog(getActivity());
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Classes").child(className).child("Students");
         c = Calendar.getInstance();
         df = new SimpleDateFormat("dd-MMM-yyyy");
         formattedDate = df.format(c.getTime());
         tvDate.setText(formattedDate);
        historyMethod(formattedDate);

         btnNext.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 c.add(Calendar.DATE, 1);
                 formattedDate = df.format(c.getTime());
                 tvDate.setText(formattedDate);
                 historyMethod(formattedDate);
             }
         });
         btnBack.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 c.add(Calendar.DATE, -1);
                 formattedDate = df.format(c.getTime());
                 tvDate.setText(formattedDate);
                 historyMethod(formattedDate);
             }
         });




        return view;
    }

    public void historyMethod(final String formattedDate)
    {
        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                recyclerView.setAdapter(adapter);
                list.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    HistoryModel model = new HistoryModel();


                    String age = dataSnapshot1.child("age").getValue(String.class);
                    String name = dataSnapshot1.child("name").getValue(String.class);
                    String rollno = dataSnapshot1.child("rollNo").getValue(String.class);
                    String fatherName = dataSnapshot1.child("fatherName").getValue(String.class);
                    String status = dataSnapshot1.child(formattedDate).child("status").getValue(String.class);
                    model.setAge(age);
                    model.setName(name);
                    model.setFatherName(fatherName);
                    model.setAge(age);
                    model.setAttendence(status);
                    if(status != null)
                    {
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
    }
}
