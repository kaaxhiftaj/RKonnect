package com.techease.rkonnect.ui.fragments.Parent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.Fragment;
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
import com.techease.rkonnect.ui.Models.ParentModel;
import com.techease.rkonnect.ui.activities.MainActivity;
import com.techease.rkonnect.utils.AlertsUtils;
import com.techease.rkonnect.utils.Configuration;

public class ParentSignUpFragment extends Fragment {

    EditText etCnic,etEmail,etName,etPass;
     Button btnSignUpParent;
     TextView tvAlreadyAccount;
    String strCnic,strEmail,strName,strPass;
    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    android.support.v7.app.AlertDialog alertDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_parent_sign_up, container, false);

        sharedPreferences = getActivity().getSharedPreferences(Configuration.MY_PREF, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        etCnic=(EditText)view.findViewById(R.id.etCNICParent);
        etEmail=(EditText)view.findViewById(R.id.etPhoneNoParent);
        etName=(EditText)view.findViewById(R.id.etNameParent);
        etPass=(EditText)view.findViewById(R.id.etPasswordParent);
        btnSignUpParent=(Button)view.findViewById(R.id.btnParentSignUp);
        tvAlreadyAccount=(TextView)view.findViewById(R.id.tvAlreadyparent);

        tvAlreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new ParentSingInFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack("abc").commit();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("user");

        btnSignUpParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alertDialog == null)
                    alertDialog = AlertsUtils.createProgressDialog(getActivity());
                alertDialog.show();
                strName=etName.getText().toString();
                strCnic=etCnic.getText().toString();
                strPass=etPass.getText().toString();
                strEmail=etEmail.getText().toString();

                SingUpWithEmailAndPass(strEmail,strPass);

            }
        });

        return view;
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

                            ParentModel parentModel = new ParentModel(strName,strEmail,strCnic);
                            DatabaseReference current_user_db=mDatabase.child("Parents").child(userid);
                            current_user_db.setValue(parentModel);
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
