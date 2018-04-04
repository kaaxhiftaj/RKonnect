package com.techease.rkonnect.ui.fragments.Parent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.activities.ParentDashboard;
import com.techease.rkonnect.utils.AlertsUtils;
import com.techease.rkonnect.utils.Configuration;

public class ParentSingInFragment extends Fragment {

    EditText etEmail,etPass;
    String strEmail,strPass;
    Button btnSignIn;
    TextView tvNoAccountYet;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    android.support.v7.app.AlertDialog alertDialog;
    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_parent_sing_in, container, false);


        sharedPreferences = getActivity().getSharedPreferences(Configuration.MY_PREF, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        mAuth = FirebaseAuth.getInstance();

        etEmail=(EditText)view.findViewById(R.id.etEmailParentSignIn);
        etPass=(EditText)view.findViewById(R.id.etParentPassSignIn);
        btnSignIn=(Button)view.findViewById(R.id.btnSignInParent);
        tvNoAccountYet=(TextView)view.findViewById(R.id.tvNoAccountYetParent);

        tvNoAccountYet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new ParentSignUpFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack("abc").commit();
            }
        });

        mDatabase= FirebaseDatabase.getInstance().getReference().child("user").child("Parents");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alertDialog == null)
                    alertDialog = AlertsUtils.createProgressDialog(getActivity());
                alertDialog.show();
                strEmail=etEmail.getText().toString();
                strPass=etPass.getText().toString();

                SignInWithEmailAndPassword(strEmail,strPass);

            }
        });
        return view;
    }

    public  void SignInWithEmailAndPassword(String email, String password){

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            checkUserExist();

                        } else {

                            if (alertDialog != null)
                                alertDialog.dismiss();
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }
    private void checkUserExist() {
        final String userId=mAuth.getCurrentUser().getUid();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(userId))
                {
                    String userid = mAuth.getUid();
                    editor.putString("user_id", userid).commit();
                    editor.putString("token","parent").commit();
                    Log.d("myUserId", userId);
                    if (alertDialog != null)
                        alertDialog.dismiss();
                   startActivity(new Intent(getActivity(), ParentDashboard.class));
                   getActivity().finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), String.valueOf(databaseError), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
