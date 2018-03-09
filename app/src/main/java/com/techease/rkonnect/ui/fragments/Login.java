package com.techease.rkonnect.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.activities.MainActivity;
import com.techease.rkonnect.utils.AlertsUtils;
import com.techease.rkonnect.utils.Configuration;


public class Login extends Fragment {

    EditText etEmail,etPassword;
    Button btnSignIn;
    TextView tvSignUp;
    String strEmail,strPassword;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    android.support.v7.app.AlertDialog alertDialog;
    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        sharedPreferences = getActivity().getSharedPreferences(Configuration.MY_PREF, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        mAuth = FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("user").child("Teachers");
        etEmail=(EditText)v.findViewById(R.id.etEmailSignIn);
        etPassword=(EditText)v.findViewById(R.id.etPasswordSignIn);
        btnSignIn=(Button) v.findViewById(R.id.btnSignIn);
        tvSignUp=(TextView) v.findViewById(R.id.tvSignUp);


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alertDialog == null)
                    alertDialog = AlertsUtils.createProgressDialog(getActivity());
                alertDialog.show();
                strEmail=etEmail.getText().toString();
                strPassword=etPassword.getText().toString();

                SignInWithEmailAndPassword(strEmail,strPassword);

            }
        });

        return v ;
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
                    editor.putString("token","login").commit();
                    if (alertDialog != null)
                        alertDialog.dismiss();
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), String.valueOf(databaseError), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
