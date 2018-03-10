package com.techease.rkonnect.ui.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daprlabs.cardstack.SwipeDeck;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Adapters.SwipeStackAdapter;
import com.techease.rkonnect.ui.Models.AttendanceRecordModel;
import com.techease.rkonnect.ui.Models.AttendenceModel;
import com.techease.rkonnect.ui.Models.ClassModel;
import com.techease.rkonnect.ui.Models.StudentModel;
import com.techease.rkonnect.utils.Configuration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import link.fls.swipestack.SwipeStack;


public class StudentFragment extends Fragment {
    SwipeDeck cardStack;
    FloatingActionButton fab;
    String strName,strFatherName,strRollNo,strAge,getBundleClassName;
    DatabaseReference databaseReference,getDatabaseReference;
    ArrayList<StudentModel> modelArrayList;
    String date,attendence,rollNo;
    int status;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student, container, false);

        sharedPreferences = getActivity().getSharedPreferences(Configuration.MY_PREF, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        databaseReference=FirebaseDatabase.getInstance().getReference();
        getBundleClassName=getArguments().getString("class");
        cardStack = (SwipeDeck) view.findViewById(R.id.swipe_deck);
        fab = (FloatingActionButton)view.findViewById(R.id.fabAddStudent);
        modelArrayList=new ArrayList<>();



        getDatabaseReference=FirebaseDatabase.getInstance().getReference().child("Classes").child(getBundleClassName).child("Students");
        getDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {

                    StudentModel stdModel1=dataSnapshot1.getValue(StudentModel.class);

                   modelArrayList.add(stdModel1);


                }

                final SwipeStackAdapter adapter = new SwipeStackAdapter(modelArrayList, getActivity());
                cardStack.setAdapter(adapter);;

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {

                if (position==0)
                {
                    rollNo=sharedPreferences.getString("r1","");
                    Toast.makeText(getActivity(), rollNo, Toast.LENGTH_SHORT).show();
                }
                else
                if (position==1)
                {
                    rollNo=sharedPreferences.getString("r2","");
                    Toast.makeText(getActivity(), rollNo, Toast.LENGTH_SHORT).show();
                }
                else
                if (position==2)
                {
                    rollNo=sharedPreferences.getString("r3","");
                    Toast.makeText(getActivity(), rollNo, Toast.LENGTH_SHORT).show();
                }
                else
                if (position==3)
                {
                    rollNo=sharedPreferences.getString("r4","");
                    Toast.makeText(getActivity(), rollNo, Toast.LENGTH_SHORT).show();
                }
                else
                if (position==4)
                {
                    rollNo=sharedPreferences.getString("r5","");
                    Toast.makeText(getActivity(), rollNo, Toast.LENGTH_SHORT).show();
                }
                else
                if (position==5)
                {
                    rollNo=sharedPreferences.getString("r6","");
                    Toast.makeText(getActivity(), rollNo, Toast.LENGTH_SHORT).show();
                }
                else
                if (position==6)
                {
                    rollNo=sharedPreferences.getString("r7","");
                }
                else
                if (position==7)
                {
                    rollNo=sharedPreferences.getString("r8","");
                }
                else
                if (position==8)
                {
                    rollNo=sharedPreferences.getString("r9","");
                }
                else
                if (position==9)
                {
                    rollNo=sharedPreferences.getString("r10","");
                }
                else
                if (position==10)
                {
                    rollNo=sharedPreferences.getString("r11","");
                }
                else
                if (position==11)
                {
                    rollNo=sharedPreferences.getString("r12","");
                }
                else
                if (position==12)
                {rollNo=sharedPreferences.getString("r13","");
                }
                else
                if (position==13)
                {
                    rollNo=sharedPreferences.getString("r14","");
                }
                else
                if (position==14)
                {
                    rollNo=sharedPreferences.getString("r15","");
                }
                else
                if (position==15)
                {
                    rollNo=sharedPreferences.getString("r16","");
                }
                else
                if (position==16)
                {
                    rollNo=sharedPreferences.getString("r17","");
                }
                else
                if (position==17)
                {
                    rollNo=sharedPreferences.getString("r18","");
                }
                else
                if (position==18)
                {
                    rollNo=sharedPreferences.getString("r19","");
                }
                else
                if (position==19)
                {
                    rollNo=sharedPreferences.getString("r20","");
                }

                Date c = Calendar.getInstance().getTime();

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c);
                attendence="Absent";
                AttendanceRecordModel attendenceModel=new AttendanceRecordModel(attendence,formattedDate);
                databaseReference.child("Classes").child(getBundleClassName).
                        child("Students").child(rollNo).child("Attendence").setValue(attendenceModel);

            }

            @Override
            public void cardSwipedRight(int position) {
                if (position==0)
                {
                    rollNo=sharedPreferences.getString("r1","");
                    Toast.makeText(getActivity(), rollNo, Toast.LENGTH_SHORT).show();
                }
                else
                if (position==1)
                {
                    rollNo=sharedPreferences.getString("r2","");
                    Toast.makeText(getActivity(), rollNo, Toast.LENGTH_SHORT).show();
                }
                else
                if (position==2)
                {
                    rollNo=sharedPreferences.getString("r3","");
                    Toast.makeText(getActivity(), rollNo, Toast.LENGTH_SHORT).show();
                }
                else
                if (position==3)
                {
                    rollNo=sharedPreferences.getString("r4","");
                    Toast.makeText(getActivity(), rollNo, Toast.LENGTH_SHORT).show();
                }
                else
                if (position==4)
                {
                    rollNo=sharedPreferences.getString("r5","");
                    Toast.makeText(getActivity(), rollNo, Toast.LENGTH_SHORT).show();
                }
                else
                if (position==5)
                {
                    rollNo=sharedPreferences.getString("r6","");
                    Toast.makeText(getActivity(), rollNo, Toast.LENGTH_SHORT).show();
                }
                else
                if (position==6)
                {
                    rollNo=sharedPreferences.getString("r7","");
                }
                else
                if (position==7)
                {
                    rollNo=sharedPreferences.getString("r8","");
                }
                else
                if (position==8)
                {
                    rollNo=sharedPreferences.getString("r9","");
                }
                else
                if (position==9)
                {
                    rollNo=sharedPreferences.getString("r10","");
                }
                else
                if (position==10)
                {
                    rollNo=sharedPreferences.getString("r11","");
                }
                else
                if (position==11)
                {
                    rollNo=sharedPreferences.getString("r12","");
                }
                else
                if (position==12)
                {rollNo=sharedPreferences.getString("r13","");
                }
                else
                if (position==13)
                {
                    rollNo=sharedPreferences.getString("r14","");
                }
                else
                if (position==14)
                {
                    rollNo=sharedPreferences.getString("r15","");
                }
                else
                if (position==15)
                {
                    rollNo=sharedPreferences.getString("r16","");
                }
                else
                if (position==16)
                {
                    rollNo=sharedPreferences.getString("r17","");
                }
                else
                if (position==17)
                {
                    rollNo=sharedPreferences.getString("r18","");
                }
                else
                if (position==18)
                {
                    rollNo=sharedPreferences.getString("r19","");
                }
                else
                if (position==19)
                {
                    rollNo=sharedPreferences.getString("r20","");
                }

                Date c = Calendar.getInstance().getTime();

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c);
                attendence="Present";
                AttendanceRecordModel attendenceModel=new AttendanceRecordModel(attendence,formattedDate);
                databaseReference.child("Classes").child(getBundleClassName).
                        child("Students").child(rollNo).child("Attendence").setValue(attendenceModel);
            }

            @Override
            public void cardsDepleted() {
                Log.i("MainActivity", "no more cards");
            }

            @Override
            public void cardActionDown() {

            }

            @Override
            public void cardActionUp() {
                Toast.makeText(getActivity(), "leave", Toast.LENGTH_SHORT).show();
            }
        });


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
