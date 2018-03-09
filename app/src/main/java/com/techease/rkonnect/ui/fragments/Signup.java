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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.Models.TeacherModel;
import com.techease.rkonnect.ui.activities.MainActivity;
import com.techease.rkonnect.utils.AlertsUtils;
import com.techease.rkonnect.utils.Configuration;


public class Signup extends Fragment {

    EditText etEmail,etPassword,etInstituteName;
    Button btnSignUp;
    TextView tvLogin;
    String strEmail,strInstituteName,strPassword;
    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    android.support.v7.app.AlertDialog alertDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_signup, container, false);

        sharedPreferences = getActivity().getSharedPreferences(Configuration.MY_PREF, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        etEmail=(EditText)v.findViewById(R.id.etEmail);
        etPassword=(EditText)v.findViewById(R.id.etPassword);
        etInstituteName=(EditText)v.findViewById(R.id.etInstituteName);
        btnSignUp=(Button) v.findViewById(R.id.btnSignUp);
        tvLogin=(TextView) v.findViewById(R.id.tvLogin);

        //Firebase initialization
        mAuth = FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("user");

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new Login();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack("abc").commit();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alertDialog == null)
                    alertDialog = AlertsUtils.createProgressDialog(getActivity());
                alertDialog.show();
                strEmail=etEmail.getText().toString();
                strPassword=etPassword.getText().toString();
                strInstituteName=etInstituteName.getText().toString();
                SingUpWithEmailAndPass(strEmail,strPassword);

            }
        });



        return v;
    }
    public void SingUpWithEmailAndPass(final String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            String userid   = mAuth.getCurrentUser().getUid();
                            editor.putString("user_id", userid).commit();
                            if (alertDialog != null)
                                alertDialog.dismiss();

                            TeacherModel teacherModel = new TeacherModel(strEmail,strInstituteName,userid);
                            DatabaseReference current_user_db=mDatabase.child("Teachers").child(userid);
                            current_user_db.setValue(teacherModel);
                            startActivity(new Intent(getActivity(), MainActivity.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            if (alertDialog != null)
                                alertDialog.dismiss();
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
